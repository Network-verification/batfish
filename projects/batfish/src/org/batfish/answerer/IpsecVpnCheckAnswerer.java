package org.batfish.answerer;

import java.util.Map;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.batfish.common.BatfishException;
import org.batfish.datamodel.Configuration;
import org.batfish.datamodel.IpsecVpn;
import org.batfish.datamodel.answers.AnswerElement;
import org.batfish.datamodel.answers.IpsecVpnCheckAnswerElement;
import org.batfish.datamodel.questions.IpsecVpnCheckQuestion;
import org.batfish.datamodel.questions.Question;
import org.batfish.main.Batfish;
import org.batfish.main.Settings.TestrigSettings;

public class IpsecVpnCheckAnswerer extends Answerer {

   public IpsecVpnCheckAnswerer(Question question, Batfish batfish) {
      super(question, batfish);
   }

   @Override
   public AnswerElement answer(TestrigSettings testrigSettings) {
      IpsecVpnCheckQuestion question = (IpsecVpnCheckQuestion) _question;

      Pattern node1Regex;
      Pattern node2Regex;
      try {
         node1Regex = Pattern.compile(question.getNode1Regex());
         node2Regex = Pattern.compile(question.getNode2Regex());
      }
      catch (PatternSyntaxException e) {
         throw new BatfishException(
               String.format(
                     "One of the supplied regexes (%s  OR  %s) is not a valid java regex.",
                     question.getNode1Regex(), question.getNode2Regex()), e);
      }

      _batfish.checkConfigurations();
      Map<String, Configuration> configurations = _batfish
            .loadConfigurations(testrigSettings);
      _batfish.initRemoteIpsecVpns(configurations);

      IpsecVpnCheckAnswerElement answerElement = new IpsecVpnCheckAnswerElement();
      for (Configuration c : configurations.values()) {
         if (!node1Regex.matcher(c.getHostname()).matches()) {
            continue;
         }
         for (IpsecVpn ipsecVpn : c.getIpsecVpns().values()) {
            if (ipsecVpn.getRemoteIpsecVpn() == null) {
               answerElement.addIpsecVpn(answerElement.getMissingEndpoint(), c,
                     ipsecVpn);
            }
            else {
               if (ipsecVpn.getCandidateRemoteIpsecVpns().size() != 1) {
                  for (IpsecVpn remoteIpsecVpn : ipsecVpn
                        .getCandidateRemoteIpsecVpns()) {
                     answerElement.addIpsecVpnPair(
                           answerElement.getNonUniqueEndpoint(), c, ipsecVpn,
                           remoteIpsecVpn);
                  }
               }
               IpsecVpn remoteIpsecVpn = ipsecVpn.getRemoteIpsecVpn();
               String remoteHost = remoteIpsecVpn.getOwner().getHostname();
               if (!node2Regex.matcher(remoteHost).matches()) {
                  continue;
               }
               if (!ipsecVpn.compatibleIkeProposals(remoteIpsecVpn)) {
                  answerElement.addIpsecVpnPair(
                        answerElement.getIncompatibleIkeProposals(), c,
                        ipsecVpn, remoteIpsecVpn);
               }
               if (!ipsecVpn.compatibleIpsecProposals(remoteIpsecVpn)) {
                  answerElement.addIpsecVpnPair(
                        answerElement.getIncompatibleIpsecProposals(), c,
                        ipsecVpn, remoteIpsecVpn);
               }
               if (!ipsecVpn
                     .getGateway()
                     .getIkePolicy()
                     .getPreSharedKeyHash()
                     .equals(
                           remoteIpsecVpn.getGateway().getIkePolicy()
                                 .getPreSharedKeyHash())) {
                  answerElement.addIpsecVpnPair(
                        answerElement.getPreSharedKeyMismatch(), c, ipsecVpn,
                        remoteIpsecVpn);
               }
            }
         }
      }
      return answerElement;
   }

}
