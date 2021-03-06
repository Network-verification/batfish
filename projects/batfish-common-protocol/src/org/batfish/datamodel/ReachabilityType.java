package org.batfish.datamodel;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.batfish.common.BatfishException;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ReachabilityType {
   INCREASED("increased"),
   MULTIPATH("multipath"),
   MULTIPATH_DIFF("multipathDiff"),
   PATH_DIFF("pathDiff"),
   REDUCED_REACHABILITY("reduced"),
   STANDARD("standard");

   private final static Map<String, ReachabilityType> _map = buildMap();

   private static Map<String, ReachabilityType> buildMap() {
      Map<String, ReachabilityType> map = new HashMap<String, ReachabilityType>();
      for (ReachabilityType value : ReachabilityType.values()) {
         String name = value._name.toLowerCase();
         map.put(name, value);
      }
      return Collections.unmodifiableMap(map);
   }

   @JsonCreator
   public static ReachabilityType fromName(String name) {
      ReachabilityType instance = _map.get(name.toLowerCase());
      if (instance == null) {
         throw new BatfishException("No "
               + ReachabilityType.class.getSimpleName() + " with name: \""
               + name + "\"");
      }
      return instance;
   }

   private final String _name;

   private ReachabilityType(String name) {
      _name = name;
   }

   @JsonValue
   public String reachabilityTypeName() {
      return _name;
   }
}
