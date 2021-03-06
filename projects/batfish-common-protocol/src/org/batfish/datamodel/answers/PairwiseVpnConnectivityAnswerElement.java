package org.batfish.datamodel.answers;

import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

public class PairwiseVpnConnectivityAnswerElement implements AnswerElement {

   private SortedMap<String, SortedSet<String>> _connectedNeighbors;

   private SortedSet<String> _ipsecVpnNodes;

   private SortedMap<String, SortedSet<String>> _missingNeighbors;

   public PairwiseVpnConnectivityAnswerElement() {
      _connectedNeighbors = new TreeMap<String, SortedSet<String>>();
      _ipsecVpnNodes = new TreeSet<String>();
      _missingNeighbors = new TreeMap<String, SortedSet<String>>();
   }

   public SortedMap<String, SortedSet<String>> getConnectedNeighbors() {
      return _connectedNeighbors;
   }

   public SortedSet<String> getIpsecVpnNodes() {
      return _ipsecVpnNodes;
   }

   public SortedMap<String, SortedSet<String>> getMissingNeighbors() {
      return _missingNeighbors;
   }

   public void setConnectedNeighbors(
         SortedMap<String, SortedSet<String>> connectedNeighbors) {
      _connectedNeighbors = connectedNeighbors;
   }

   public void setIpsecVpnNodes(SortedSet<String> ipsecVpnNodes) {
      _ipsecVpnNodes = ipsecVpnNodes;
   }

   public void setMissingNeighbors(
         SortedMap<String, SortedSet<String>> missingNeighbors) {
      _missingNeighbors = missingNeighbors;
   }

}
