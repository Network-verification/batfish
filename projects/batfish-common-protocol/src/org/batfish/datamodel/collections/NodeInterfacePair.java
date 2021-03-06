package org.batfish.datamodel.collections;

import org.batfish.common.Pair;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class NodeInterfacePair extends Pair<String, String> {

   /**
    *
    */
   private static final long serialVersionUID = 1L;

   private static final String HOSTNAME_VAR = "hostname";
   private static final String INTERFACE_VAR = "interface";

   @JsonCreator
   public NodeInterfacePair(@JsonProperty(HOSTNAME_VAR) String node,
         @JsonProperty(INTERFACE_VAR) String iface) {
      super(node, iface);
   }

   @JsonProperty(HOSTNAME_VAR)
   public String getHostname() {
      return _first;
   }

   @JsonProperty(INTERFACE_VAR)
   public String getInterface() {
      return _second;
   }

   @Override
   public String toString() {
      return _first + ":" + _second;
   }

}
