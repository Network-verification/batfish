package org.batfish.datamodel;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.batfish.common.BatfishException;
import org.batfish.datamodel.collections.CommunitySet;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * Instances of this class represent hypothetical BGP advertisements used for
 * testing, or where the config of an advertising border router is unavailable
 *
 * @author arifogel
 *
 */

@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id")
public class BgpAdvertisement implements Comparable<BgpAdvertisement>,
      Serializable {

   public enum BgpAdvertisementType {
      EBGP_ORIGINATED("bgp"),
      EBGP_RECEIVED("bgp_ti"),
      EBGP_SENT("bgp_to"),
      IBGP_ORIGINATED("ibgp"),
      IBGP_RECEIVED("ibgp_ti"),
      IBGP_SENT("ibgp_to");

      private final static Map<String, BgpAdvertisementType> _map = buildMap();

      private static Map<String, BgpAdvertisementType> buildMap() {
         Map<String, BgpAdvertisementType> map = new HashMap<String, BgpAdvertisementType>();
         for (BgpAdvertisementType bgpAdvertisementType : BgpAdvertisementType
               .values()) {
            String nlsTypeName = bgpAdvertisementType._nlsTypeName;
            map.put(nlsTypeName, bgpAdvertisementType);
         }
         return Collections.unmodifiableMap(map);
      }

      public static BgpAdvertisementType fromNlsTypeName(String nlsTypeName) {
         BgpAdvertisementType bgpAdvertisementType = _map.get(nlsTypeName);
         if (bgpAdvertisementType == null) {
            throw new BatfishException("Invalid nlsTypeName: \"" + nlsTypeName
                  + "\"");
         }
         return bgpAdvertisementType;
      }

      private String _nlsTypeName;

      private BgpAdvertisementType(String nlsTypeName) {
         _nlsTypeName = nlsTypeName;
      }

      public String getNlsTypeName() {
         return _nlsTypeName;
      }

   }

   private static final String AS_PATH_VAR = "asPath";

   private static final String COMMUNITIES_VAR = "communities";
   private static final String DST_IP_VAR = "dstIp";
   private static final String DST_NODE_VAR = "dstNode";
   private static final String LOCAL_PREFERENCE_VAR = "localPreference";
   private static final String MED_VAR = "med";
   private static final String NETWORK_VAR = "network";
   private static final String NEXT_HOP_IP_VAR = "nextHopIp";
   private static final String ORIGIN_TYPE_VAR = "originType";
   private static final String ORIGINATOR_IP_VAR = "originatorIp";
   /**
    *
    */
   private static final long serialVersionUID = 1L;
   private static final String SRC_IP_VAR = "srcIp";
   private static final String SRC_NODE_VAR = "srcNode";
   private static final String SRC_PROTOCOL_VAR = "srcProtocol";
   private static final String TYPE_VAR = "type";

   private static final Ip UNSET_ORIGINATOR_IP = new Ip(-1l);

   private final AsPath _asPath;

   private final CommunitySet _communities;

   private final Ip _dstIp;

   private final String _dstNode;

   private final int _localPreference;

   private final int _med;

   private final Prefix _network;

   private final Ip _nextHopIp;

   private final Ip _originatorIp;

   private final OriginType _originType;

   private final Ip _srcIp;

   private final String _srcNode;

   private final RoutingProtocol _srcProtocol;

   private final String _type;

   @JsonCreator
   public BgpAdvertisement(@JsonProperty(TYPE_VAR) String type,
         @JsonProperty(NETWORK_VAR) Prefix network,
         @JsonProperty(NEXT_HOP_IP_VAR) Ip nextHopIp,
         @JsonProperty(SRC_NODE_VAR) String srcNode,
         @JsonProperty(SRC_IP_VAR) Ip srcIp,
         @JsonProperty(DST_NODE_VAR) String dstNode,
         @JsonProperty(DST_IP_VAR) Ip dstIp,
         @JsonProperty(SRC_PROTOCOL_VAR) RoutingProtocol srcProtocol,
         @JsonProperty(ORIGIN_TYPE_VAR) OriginType originType,
         @JsonProperty(LOCAL_PREFERENCE_VAR) int localPreference,
         @JsonProperty(MED_VAR) int med,
         @JsonProperty(ORIGINATOR_IP_VAR) Ip originatorIp,
         @JsonProperty(AS_PATH_VAR) AsPath asPath,
         @JsonProperty(COMMUNITIES_VAR) CommunitySet communities) {
      _type = type;
      _network = network;
      _nextHopIp = nextHopIp;
      _srcNode = srcNode;
      _srcIp = srcIp;
      _dstNode = dstNode;
      _dstIp = dstIp;
      _srcProtocol = srcProtocol;
      _originType = originType;
      _localPreference = localPreference;
      _med = med;
      _originatorIp = originatorIp;
      _asPath = asPath;
      _communities = communities;
   }

   @Override
   public int compareTo(BgpAdvertisement rhs) {
      int ret;
      ret = _type.compareTo(rhs._type);
      if (ret != 0) {
         return ret;
      }
      ret = _srcNode.compareTo(rhs._srcNode);
      if (ret != 0) {
         return ret;
      }
      ret = _dstNode.compareTo(rhs._dstNode);
      if (ret != 0) {
         return ret;
      }
      ret = _network.compareTo(rhs._network);
      if (ret != 0) {
         return ret;
      }
      ret = Integer.compare(_localPreference, rhs._localPreference);
      if (ret != 0) {
         return ret;
      }
      ret = Integer.compare(_med, rhs._med);
      if (ret != 0) {
         return ret;
      }
      ret = _nextHopIp.compareTo(rhs._nextHopIp);
      if (ret != 0) {
         return ret;
      }
      ret = _originatorIp.compareTo(rhs._originatorIp);
      if (ret != 0) {
         return ret;
      }
      ret = _originType.compareTo(rhs._originType);
      if (ret != 0) {
         return ret;
      }
      ret = _srcProtocol.compareTo(rhs._srcProtocol);
      if (ret != 0) {
         return ret;
      }
      ret = _asPath.toString().compareTo(rhs._asPath.toString());
      if (ret != 0) {
         return ret;
      }
      ret = _communities.toString().compareTo(rhs._communities.toString());
      if (ret != 0) {
         return ret;
      }
      return 0;
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      }
      BgpAdvertisement other = (BgpAdvertisement) obj;
      if (!_network.equals(other._network)) {
         return false;
      }
      if (!_asPath.toString().equals(other._asPath.toString())) {
         return false;
      }
      if (!_communities.toString().equals(other._communities.toString())) {
         return false;
      }
      if (!_dstIp.equals(other._dstIp)) {
         return false;
      }
      if (!_dstNode.equals(other._dstNode)) {
         return false;
      }
      if (_localPreference != other._localPreference) {
         return false;
      }
      if (_med != other._med) {
         return false;
      }
      if (!_nextHopIp.equals(other._nextHopIp)) {
         return false;
      }
      if (_originType != other._originType) {
         return false;
      }
      if (!_originatorIp.equals(other._originatorIp)) {
         return false;
      }
      if (!_srcIp.equals(other._srcIp)) {
         return false;
      }
      if (!_srcNode.equals(other._srcNode)) {
         return false;
      }
      if (_srcProtocol != other._srcProtocol) {
         return false;
      }
      if (!_type.equals(other._type)) {
         return false;
      }
      return true;
   }

   @JsonProperty(AS_PATH_VAR)
   public AsPath getAsPath() {
      return _asPath;
   }

   @JsonProperty(COMMUNITIES_VAR)
   public CommunitySet getCommunities() {
      return _communities;
   }

   @JsonProperty(DST_IP_VAR)
   public Ip getDstIp() {
      return _dstIp;
   }

   @JsonProperty(DST_NODE_VAR)
   public String getDstNode() {
      return _dstNode;
   }

   @JsonProperty(LOCAL_PREFERENCE_VAR)
   public int getLocalPreference() {
      return _localPreference;
   }

   @JsonProperty(MED_VAR)
   public int getMed() {
      return _med;
   }

   @JsonProperty(NETWORK_VAR)
   public Prefix getNetwork() {
      return _network;
   }

   @JsonProperty(NEXT_HOP_IP_VAR)
   public Ip getNextHopIp() {
      return _nextHopIp;
   }

   @JsonProperty(ORIGINATOR_IP_VAR)
   public Ip getOriginatorIp() {
      return _originatorIp;
   }

   @JsonProperty(ORIGIN_TYPE_VAR)
   public OriginType getOriginType() {
      return _originType;
   }

   @JsonProperty(SRC_IP_VAR)
   public Ip getSrcIp() {
      return _srcIp;
   }

   @JsonProperty(SRC_NODE_VAR)
   public String getSrcNode() {
      return _srcNode;
   }

   @JsonProperty(SRC_PROTOCOL_VAR)
   public RoutingProtocol getSrcProtocol() {
      return _srcProtocol;
   }

   @JsonProperty(TYPE_VAR)
   public String getType() {
      return _type;
   }

   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + _asPath.hashCode();
      result = prime * result + _communities.hashCode();
      result = prime * result + _dstIp.hashCode();
      result = prime * result + _dstNode.hashCode();
      result = prime * result + _localPreference;
      result = prime * result + _med;
      result = prime * result + _network.hashCode();
      result = prime * result + _nextHopIp.hashCode();
      result = prime * result + _originType.hashCode();
      result = prime * result + _originatorIp.hashCode();
      result = prime * result + _srcIp.hashCode();
      result = prime * result + _srcNode.hashCode();
      result = prime * result + _srcProtocol.hashCode();
      result = prime * result + _type.hashCode();
      return result;
   }

   @Override
   public String toString() {
      String originatorIp = _originatorIp.equals(UNSET_ORIGINATOR_IP) ? "N/A"
            : _originatorIp.toString();
      return "BgpAdvert<" + _type + ", " + _network + ", " + _nextHopIp + ", "
            + _srcIp + ", " + _dstIp + ", " + _srcProtocol + ", " + _srcNode
            + ", " + _dstNode + ", " + _localPreference + ", " + _med + ", "
            + originatorIp + ", " + _originType + ">";
   }

}
