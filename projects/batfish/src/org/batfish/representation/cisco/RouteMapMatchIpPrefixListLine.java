package org.batfish.representation.cisco;

import java.util.List;
import java.util.Set;

import org.batfish.datamodel.Configuration;
import org.batfish.datamodel.routing_policy.expr.BooleanExpr;
import org.batfish.datamodel.routing_policy.expr.Disjunction;
import org.batfish.datamodel.routing_policy.expr.MatchPrefixList;
import org.batfish.main.Warnings;
import org.batfish.representation.VendorConfiguration;

public class RouteMapMatchIpPrefixListLine extends RouteMapMatchLine {

   private static final long serialVersionUID = 1L;

   private Set<String> _listNames;

   public RouteMapMatchIpPrefixListLine(Set<String> names) {
      _listNames = names;
   }

   public Set<String> getListNames() {
      return _listNames;
   }

   @Override
   public RouteMapMatchType getType() {
      return RouteMapMatchType.IP_PREFIX_LIST;
   }

   @Override
   public BooleanExpr toBooleanExpr(Configuration c, CiscoConfiguration cc,
         Warnings w) {
      Disjunction d = new Disjunction();
      List<BooleanExpr> disjuncts = d.getDisjuncts();
      for (String listName : _listNames) {
         PrefixList list = cc.getPrefixLists().get(listName);
         if (list != null) {
            list.getReferers().put(this, "route-map match prefix-list");
            disjuncts.add(new MatchPrefixList(listName));
         }
         else {
            w.redFlag("Reference to undefined prefix-list: " + listName,
                  VendorConfiguration.UNDEFINED);
         }
      }
      return d.simplify();
   }

}
