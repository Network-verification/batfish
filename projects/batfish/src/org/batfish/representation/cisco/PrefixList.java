package org.batfish.representation.cisco;

import java.util.ArrayList;
import java.util.List;

import org.batfish.common.util.ComparableStructure;

public class PrefixList extends ComparableStructure<String> {

   private static final long serialVersionUID = 1L;

   private List<PrefixListLine> _lines;

   public PrefixList(String n) {
      super(n);
      _lines = new ArrayList<PrefixListLine>();
   }

   public void addLine(PrefixListLine r) {
      _lines.add(r);
   }

   public void addLines(List<PrefixListLine> r) {
      _lines.addAll(r);
   }

   public List<PrefixListLine> getLines() {
      return _lines;
   }

}
