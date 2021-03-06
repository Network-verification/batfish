package org.batfish.representation.cisco;

import org.batfish.datamodel.Prefix;

public class DynamicBgpPeerGroup extends LeafBgpPeerGroup {

   /**
    *
    */
   private static final long serialVersionUID = 1L;

   private Prefix _prefix;

   public DynamicBgpPeerGroup(Prefix prefix) {
      _prefix = prefix;
   }

   @Override
   public String getName() {
      return _prefix.toString();
   }

   @Override
   public Prefix getNeighborPrefix() {
      return _prefix;
   }

   public Prefix getPrefix() {
      return _prefix;
   }

}
