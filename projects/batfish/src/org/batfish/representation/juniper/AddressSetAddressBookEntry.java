package org.batfish.representation.juniper;

import java.util.HashSet;
import java.util.Set;

import org.batfish.datamodel.Prefix;
import org.batfish.main.Warnings;

public final class AddressSetAddressBookEntry extends AddressBookEntry {

   /**
    *
    */
   private static final long serialVersionUID = 1L;

   private final Set<AddressSetEntry> _entries;

   public AddressSetAddressBookEntry(String name) {
      super(name);
      _entries = new HashSet<AddressSetEntry>();
   }

   public Set<AddressSetEntry> getEntries() {
      return _entries;
   }

   @Override
   public Set<Prefix> getPrefixes(Warnings w) {
      Set<Prefix> prefixes = new HashSet<Prefix>();
      for (AddressSetEntry entry : _entries) {
         Set<Prefix> subPrefixes = entry.getPrefixes(w);
         prefixes.addAll(subPrefixes);
      }
      return prefixes;
   }

}
