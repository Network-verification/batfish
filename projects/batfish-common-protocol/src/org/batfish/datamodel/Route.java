package org.batfish.datamodel;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_NULL)
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "class")
public abstract class Route implements Serializable {

   protected static final String ADMINISTRATIVE_COST_VAR = "administrativeCost";

   protected static final String NEXT_HOP_IP_VAR = "nextHopIp";

   protected static final String PREFIX_VAR = "prefix";

   private static final long serialVersionUID = 1L;

   protected final Ip _nextHopIp;

   protected final Prefix _prefix;

   public Route(Prefix prefix, Ip nextHopIp) {
      _prefix = prefix;
      _nextHopIp = nextHopIp;
   }

   @Override
   public abstract boolean equals(Object o);

   @JsonProperty(ADMINISTRATIVE_COST_VAR)
   public abstract int getAdministrativeCost();

   @JsonProperty(NEXT_HOP_IP_VAR)
   public Ip getNextHopIp() {
      return _nextHopIp;
   }

   @JsonProperty(PREFIX_VAR)
   public Prefix getPrefix() {
      return _prefix;
   }

   @JsonIgnore
   public abstract RouteType getRouteType();

   @Override
   public abstract int hashCode();

}
