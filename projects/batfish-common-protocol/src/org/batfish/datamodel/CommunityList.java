package org.batfish.datamodel;

import java.util.List;

import org.batfish.common.util.ComparableStructure;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a named access-list whose matching criteria is restricted to
 * regexes on community attributes sent with a bgp advertisement
 */
public class CommunityList extends ComparableStructure<String> {

   private static final String LINES_VAR = "lines";

   /**
    *
    */
   private static final long serialVersionUID = 1L;

   /**
    * The list of lines that are checked in order against the community
    * attribute(s) of a bgp advertisement
    */
   private final List<CommunityListLine> _lines;

   /**
    * Constructs a CommunityList with the given name for {@link #_name}, and
    * lines for {@link #_lines}
    *
    * @param name
    * @param lines
    */
   @JsonCreator
   public CommunityList(@JsonProperty(NAME_VAR) String name,
         @JsonProperty(LINES_VAR) List<CommunityListLine> lines) {
      super(name);
      _lines = lines;
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      }
      CommunityList other = (CommunityList) obj;
      return other._lines.equals(_lines);
   }

   @JsonProperty(LINES_VAR)
   public List<CommunityListLine> getLines() {
      return _lines;
   }

   @Override
   @JsonProperty(NAME_VAR)
   public String getName() {
      return _key;
   }
}
