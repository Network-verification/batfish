package org.batfish.z3;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.batfish.common.BatfishException;
import org.batfish.datamodel.ForwardingAction;
import org.batfish.datamodel.IcmpCode;
import org.batfish.datamodel.IcmpType;
import org.batfish.datamodel.Prefix;
import org.batfish.datamodel.SubRange;
import org.batfish.z3.node.AcceptExpr;
import org.batfish.z3.node.AndExpr;
import org.batfish.z3.node.DebugExpr;
import org.batfish.z3.node.DropExpr;
import org.batfish.z3.node.EqExpr;
import org.batfish.z3.node.LitIntExpr;
import org.batfish.z3.node.NodeAcceptExpr;
import org.batfish.z3.node.NodeDropExpr;
import org.batfish.z3.node.OrExpr;
import org.batfish.z3.node.OriginateExpr;
import org.batfish.z3.node.PrefixMatchExpr;
import org.batfish.z3.node.QueryExpr;
import org.batfish.z3.node.QueryRelationExpr;
import org.batfish.z3.node.RangeMatchExpr;
import org.batfish.z3.node.RuleExpr;
import org.batfish.z3.node.SaneExpr;
import org.batfish.z3.node.VarIntExpr;

import com.microsoft.z3.BoolExpr;
import com.microsoft.z3.Z3Exception;

public class ReachabilityQuerySynthesizer extends BaseQuerySynthesizer {

   private Set<ForwardingAction> _actions;

   private Set<Prefix> _dstIpPrefixes;

   private Set<SubRange> _dstPortRange;

   private Set<String> _finalNodes;

   private int _icmpCode;

   private int _icmpType;

   private Set<String> _ingressNodes;

   private Set<SubRange> _ipProtocolRange;

   private Set<Prefix> _srcIpPrefixes;

   private Set<SubRange> _srcPortRange;

   // private int _tcpFlags;

   public ReachabilityQuerySynthesizer(Set<ForwardingAction> actions,
         Set<Prefix> dstIpPrefixes, Set<SubRange> dstPortRange,
         Set<String> finalNodes, Set<String> ingressNodes,
         Set<SubRange> ipProtocolRange, Set<Prefix> srcIpPrefixes,
         Set<SubRange> srcPortRange, int icmpType, int icmpCode, int tcpFlags) {
      _actions = actions;
      _dstIpPrefixes = dstIpPrefixes;
      _dstPortRange = dstPortRange;
      _finalNodes = finalNodes;
      _ingressNodes = ingressNodes;
      _ipProtocolRange = ipProtocolRange;
      _srcIpPrefixes = srcIpPrefixes;
      _srcPortRange = srcPortRange;
      _icmpType = icmpType;
      _icmpCode = icmpCode;
      // TODO: allow constraining tcpFlags
      // _tcpFlags = tcpFlags;
   }

   @Override
   public NodProgram getNodProgram(NodProgram baseProgram) throws Z3Exception {
      NodProgram program = new NodProgram(baseProgram.getContext());

      // create rules for injecting symbolic packets into ingress node(s)
      List<RuleExpr> originateRules = new ArrayList<RuleExpr>();
      for (String ingressNode : _ingressNodes) {
         OriginateExpr originate = new OriginateExpr(ingressNode);
         RuleExpr originateRule = new RuleExpr(originate);
         originateRules.add(originateRule);
      }

      AndExpr queryConditions = new AndExpr();

      // create query condition for action at final node(s)
      OrExpr finalActions = new OrExpr();
      for (ForwardingAction action : _actions) {
         switch (action) {
         case ACCEPT:
            if (_finalNodes.size() > 0) {
               for (String finalNode : _finalNodes) {
                  NodeAcceptExpr accept = new NodeAcceptExpr(finalNode);
                  finalActions.addDisjunct(accept);
               }
            }
            else {
               finalActions.addDisjunct(AcceptExpr.INSTANCE);
            }
            break;

         case DEBUG:
            finalActions.addDisjunct(DebugExpr.INSTANCE);
            break;

         case DROP:
            if (_finalNodes.size() > 0) {
               for (String finalNode : _finalNodes) {
                  NodeDropExpr drop = new NodeDropExpr(finalNode);
                  finalActions.addDisjunct(drop);
               }
            }
            else {
               finalActions.addDisjunct(DropExpr.INSTANCE);
            }
            break;

         case DROP_ACL:
         case DROP_ACL_IN:
         case DROP_ACL_OUT:
         case DROP_NO_ROUTE:
         case DROP_NULL_ROUTE:
         case FORWARD:
         default:
            throw new BatfishException("unsupported action");
         }
      }
      queryConditions.addConjunct(finalActions);
      queryConditions.addConjunct(SaneExpr.INSTANCE);

      // add src-ip constraints
      if (_srcIpPrefixes.size() > 0) {
         OrExpr matchAnyPrefix = new OrExpr();
         for (Prefix prefix : _srcIpPrefixes) {
            PrefixMatchExpr prefixMatch = new PrefixMatchExpr(
                  Synthesizer.SRC_IP_VAR, prefix);
            matchAnyPrefix.addDisjunct(prefixMatch);
         }
         queryConditions.addConjunct(matchAnyPrefix);
      }

      // add dst-ip constraints
      if (_dstIpPrefixes.size() > 0) {
         OrExpr matchAnyPrefix = new OrExpr();
         for (Prefix prefix : _dstIpPrefixes) {
            PrefixMatchExpr prefixMatch = new PrefixMatchExpr(
                  Synthesizer.DST_IP_VAR, prefix);
            matchAnyPrefix.addDisjunct(prefixMatch);
         }
         queryConditions.addConjunct(matchAnyPrefix);
      }

      // add src-port constraints
      if (_srcPortRange.size() > 0) {
         RangeMatchExpr rangeMatch = new RangeMatchExpr(
               Synthesizer.SRC_PORT_VAR, Synthesizer.PORT_BITS, _srcPortRange);
         queryConditions.addConjunct(rangeMatch);
      }

      // add dst-port constraints
      if (_dstPortRange.size() > 0) {
         RangeMatchExpr rangeMatch = new RangeMatchExpr(
               Synthesizer.DST_PORT_VAR, Synthesizer.PORT_BITS, _dstPortRange);
         queryConditions.addConjunct(rangeMatch);
      }

      // add ip-protocol constraints
      if (_ipProtocolRange.size() > 0) {
         RangeMatchExpr rangeMatch = new RangeMatchExpr(
               Synthesizer.IP_PROTOCOL_VAR, Synthesizer.PROTOCOL_BITS,
               _ipProtocolRange);
         queryConditions.addConjunct(rangeMatch);
      }

      // add icmp-type constraints
      if (_icmpType != IcmpType.UNSET) {
         EqExpr exactMatch = new EqExpr(new VarIntExpr(
               Synthesizer.ICMP_TYPE_VAR), new LitIntExpr(_icmpType,
               Synthesizer.ICMP_TYPE_BITS));
         queryConditions.addConjunct(exactMatch);
      }

      // add icmp-code constraints
      if (_icmpCode != IcmpCode.UNSET) {
         EqExpr exactMatch = new EqExpr(new VarIntExpr(
               Synthesizer.ICMP_CODE_VAR), new LitIntExpr(_icmpCode,
               Synthesizer.ICMP_CODE_BITS));
         queryConditions.addConjunct(exactMatch);
      }

      // // add tcp-flags constraints
      // if (_tcpFlags != TcpFlags.UNSET) {
      // EqExpr exactMatch = new EqExpr(new VarIntExpr(
      // Synthesizer.TCP_FLAGS_VAR), new LitIntExpr(_tcpFlags,
      // Synthesizer.TCP_FLAGS_BITS));
      // queryConditions.addConjunct(exactMatch);
      // }
      //
      RuleExpr queryRule = new RuleExpr(queryConditions,
            QueryRelationExpr.INSTANCE);
      List<BoolExpr> rules = program.getRules();
      for (RuleExpr originateRule : originateRules) {
         BoolExpr originateBoolExpr = originateRule.toBoolExpr(baseProgram);
         rules.add(originateBoolExpr);
      }
      rules.add(queryRule.toBoolExpr(baseProgram));
      QueryExpr query = new QueryExpr(QueryRelationExpr.INSTANCE);
      BoolExpr queryBoolExpr = query.toBoolExpr(baseProgram);
      program.getQueries().add(queryBoolExpr);
      return program;
   }

   @Override
   public String getQueryText() {
      throw new UnsupportedOperationException(
            "no implementation for generated method"); // TODO Auto-generated
                                                       // method stub
   }

}
