package org.batfish.datamodel;

import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.TreeMap;
import java.util.TreeSet;

import org.batfish.common.BfJson;
import org.batfish.common.util.ComparableStructure;
import org.batfish.datamodel.collections.RoleSet;
import org.batfish.datamodel.routing_policy.RoutingPolicy;
import org.batfish.datamodel.ConfigurationFormat;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class Configuration extends ComparableStructure<String> {

   private static final String AS_PATH_ACCESS_LISTS_VAR = "asPathAccessLists";

   private static final String BGP_PROCESS_VAR = "bgpProcess";

   private static final String COMMUNITIES_VAR = "communities";

   private static final String COMMUNITY_LISTS_VAR = "communityLists";

   private static final String CONFIGURATION_FORMAT_VAR = "configurationFormat";

   private static final String DEFAULT_CROSS_ZONE_ACTION_VAR = "defaultCrossZoneAction";

   private static final String DEFAULT_INBOUND_ACTION_VAR = "defaultInboundAction";

   private static final String GENERATED_ROUTES_VAR = "aggregateRoutes";

   private static final String IKE_GATEWAYS_VAR = "ikeGateways";

   private static final String IKE_POLICIES_VAR = "ikePolicies";

   private static final String IKE_PROPOSALS_VAR = "ikeProposals";

   private static final String INTERFACES_VAR = "interfaces";

   private static final String IP_ACCESS_LISTS_VAR = "ipAccessLists";

   private static final String IPSEC_POLICIES_VAR = "ipsecPolicies";

   private static final String IPSEC_PROPOSALS_VAR = "ipsecProposals";

   private static final String IPSEC_VPNS_VAR = "ipsecVpns";

   private static final String ISIS_PROCESS_VAR = "isisProcess";

   public static final String NODE_NONE_NAME = "(none)";

   private static final String OSPF_PROCESS_VAR = "ospfProcess";

   private static final String POLICY_MAPS_VAR = "policyMaps";

   private static final String ROLES_VAR = "roles";

   private static final String ROUTE_FILTER_LISTS_VAR = "routeFilterLists";

   private static final String ROUTING_POLICIES_VAR = "routingPolicies";

   private static final long serialVersionUID = 1L;

   private static final String STATIC_ROUTES_VAR = "staticRoutes";

   private static final String ZONES_VAR = "zones";

   private NavigableMap<String, AsPathAccessList> _asPathAccessLists;

   private transient NavigableSet<BgpAdvertisement> _bgpAdvertisements;

   private BgpProcess _bgpProcess;

   private NavigableSet<Long> _communities;

   private NavigableMap<String, CommunityList> _communityLists;

   private ConfigurationFormat _configurationFormat;

   private LineAction _defaultCrossZoneAction;

   private LineAction _defaultInboundAction;

   private NavigableSet<GeneratedRoute> _generatedRoutes;

   private NavigableMap<String, IkeGateway> _ikeGateways;

   private NavigableMap<String, IkePolicy> _ikePolicies;

   private NavigableMap<String, IkeProposal> _ikeProposals;

   private NavigableMap<String, Interface> _interfaces;

   private NavigableMap<String, IpAccessList> _ipAccessLists;

   private NavigableMap<String, IpsecPolicy> _ipsecPolicies;

   private NavigableMap<String, IpsecProposal> _ipsecProposals;

   private NavigableMap<String, IpsecVpn> _ipsecVpns;

   private IsisProcess _isisProcess;

   private transient NavigableSet<BgpAdvertisement> _originatedAdvertisements;

   private transient NavigableSet<BgpAdvertisement> _originatedEbgpAdvertisements;

   private transient NavigableSet<BgpAdvertisement> _originatedIbgpAdvertisements;

   private OspfProcess _ospfProcess;

   private NavigableMap<String, PolicyMap> _policyMaps;

   private transient NavigableSet<BgpAdvertisement> _receivedAdvertisements;

   private transient NavigableSet<BgpAdvertisement> _receivedEbgpAdvertisements;

   private transient NavigableSet<BgpAdvertisement> _receivedIbgpAdvertisements;

   private RoleSet _roles;

   private NavigableMap<String, RouteFilterList> _routeFilterLists;

   private transient NavigableSet<PrecomputedRoute> _routes;

   private NavigableMap<String, RoutingPolicy> _routingPolicies;

   private transient NavigableSet<BgpAdvertisement> _sentAdvertisements;

   private transient NavigableSet<BgpAdvertisement> _sentEbgpAdvertisements;

   private transient NavigableSet<BgpAdvertisement> _sentIbgpAdvertisements;

   private NavigableSet<StaticRoute> _staticRoutes;

   private NavigableMap<String, Zone> _zones;

   @JsonCreator
   public Configuration(@JsonProperty(NAME_VAR) String hostname) {
      super(hostname);
      _asPathAccessLists = new TreeMap<String, AsPathAccessList>();
      _communities = new TreeSet<Long>();
      _communityLists = new TreeMap<String, CommunityList>();
      _generatedRoutes = new TreeSet<GeneratedRoute>();
      _ikeGateways = new TreeMap<String, IkeGateway>();
      _ikePolicies = new TreeMap<String, IkePolicy>();
      _ikeProposals = new TreeMap<String, IkeProposal>();
      _interfaces = new TreeMap<String, Interface>();
      _ipAccessLists = new TreeMap<String, IpAccessList>();
      _ipsecPolicies = new TreeMap<String, IpsecPolicy>();
      _ipsecProposals = new TreeMap<String, IpsecProposal>();
      _ipsecVpns = new TreeMap<String, IpsecVpn>();
      _policyMaps = new TreeMap<String, PolicyMap>();
      _roles = new RoleSet();
      _routeFilterLists = new TreeMap<String, RouteFilterList>();
      _routingPolicies = new TreeMap<String, RoutingPolicy>();
      _staticRoutes = new TreeSet<StaticRoute>();
      _zones = new TreeMap<String, Zone>();
   }

   @JsonProperty(AS_PATH_ACCESS_LISTS_VAR)
   public NavigableMap<String, AsPathAccessList> getAsPathAccessLists() {
      return _asPathAccessLists;
   }

   @JsonIgnore
   public NavigableSet<BgpAdvertisement> getBgpAdvertisements() {
      return _bgpAdvertisements;
   }

   @JsonProperty(BGP_PROCESS_VAR)
   public BgpProcess getBgpProcess() {
      return _bgpProcess;
   }

   @JsonProperty(COMMUNITIES_VAR)
   public NavigableSet<Long> getCommunities() {
      return _communities;
   }

   @JsonProperty(COMMUNITY_LISTS_VAR)
   public NavigableMap<String, CommunityList> getCommunityLists() {
      return _communityLists;
   }

   @JsonProperty(CONFIGURATION_FORMAT_VAR)
   public ConfigurationFormat getConfigurationFormat() {
      return _configurationFormat;
   }

   @JsonProperty(DEFAULT_CROSS_ZONE_ACTION_VAR)
   public LineAction getDefaultCrossZoneAction() {
      return _defaultCrossZoneAction;
   }

   @JsonProperty(DEFAULT_INBOUND_ACTION_VAR)
   public LineAction getDefaultInboundAction() {
      return _defaultInboundAction;
   }

   @JsonProperty(GENERATED_ROUTES_VAR)
   public NavigableSet<GeneratedRoute> getGeneratedRoutes() {
      return _generatedRoutes;
   }

   @JsonProperty(NAME_VAR)
   public String getHostname() {
      return _key;
   }

   @JsonProperty(IKE_GATEWAYS_VAR)
   public NavigableMap<String, IkeGateway> getIkeGateways() {
      return _ikeGateways;
   }

   @JsonProperty(IKE_POLICIES_VAR)
   public NavigableMap<String, IkePolicy> getIkePolicies() {
      return _ikePolicies;
   }

   @JsonProperty(IKE_PROPOSALS_VAR)
   public NavigableMap<String, IkeProposal> getIkeProposals() {
      return _ikeProposals;
   }

   @JsonProperty(INTERFACES_VAR)
   public NavigableMap<String, Interface> getInterfaces() {
      return _interfaces;
   }

   @JsonProperty(IP_ACCESS_LISTS_VAR)
   public NavigableMap<String, IpAccessList> getIpAccessLists() {
      return _ipAccessLists;
   }

   @JsonProperty(IPSEC_POLICIES_VAR)
   public NavigableMap<String, IpsecPolicy> getIpsecPolicies() {
      return _ipsecPolicies;
   }

   @JsonProperty(IPSEC_PROPOSALS_VAR)
   public NavigableMap<String, IpsecProposal> getIpsecProposals() {
      return _ipsecProposals;
   }

   @JsonProperty(IPSEC_VPNS_VAR)
   public NavigableMap<String, IpsecVpn> getIpsecVpns() {
      return _ipsecVpns;
   }

   @JsonProperty(ISIS_PROCESS_VAR)
   public IsisProcess getIsisProcess() {
      return _isisProcess;
   }

   @JsonIgnore
   public NavigableSet<BgpAdvertisement> getOriginatedAdvertisements() {
      return _originatedAdvertisements;
   }

   @JsonIgnore
   public NavigableSet<BgpAdvertisement> getOriginatedEbgpAdvertisements() {
      return _originatedEbgpAdvertisements;
   }

   @JsonIgnore
   public NavigableSet<BgpAdvertisement> getOriginatedIbgpAdvertisements() {
      return _originatedIbgpAdvertisements;
   }

   @JsonProperty(OSPF_PROCESS_VAR)
   public OspfProcess getOspfProcess() {
      return _ospfProcess;
   }

   @JsonProperty(POLICY_MAPS_VAR)
   public NavigableMap<String, PolicyMap> getPolicyMaps() {
      return _policyMaps;
   }

   @JsonIgnore
   public NavigableSet<BgpAdvertisement> getReceivedAdvertisements() {
      return _receivedAdvertisements;
   }

   @JsonIgnore
   public NavigableSet<BgpAdvertisement> getReceivedEbgpAdvertisements() {
      return _receivedEbgpAdvertisements;
   }

   @JsonIgnore
   public NavigableSet<BgpAdvertisement> getReceivedIbgpAdvertisements() {
      return _receivedIbgpAdvertisements;
   }

   @JsonProperty(ROLES_VAR)
   public RoleSet getRoles() {
      return _roles;
   }

   @JsonProperty(ROUTE_FILTER_LISTS_VAR)
   public NavigableMap<String, RouteFilterList> getRouteFilterLists() {
      return _routeFilterLists;
   }

   @JsonIgnore
   public NavigableSet<PrecomputedRoute> getRoutes() {
      return _routes;
   }

   @JsonProperty(ROUTING_POLICIES_VAR)
   public NavigableMap<String, RoutingPolicy> getRoutingPolicies() {
      return _routingPolicies;
   }

   @JsonIgnore
   public NavigableSet<BgpAdvertisement> getSentAdvertisements() {
      return _sentAdvertisements;
   }

   @JsonIgnore
   public NavigableSet<BgpAdvertisement> getSentEbgpAdvertisements() {
      return _sentEbgpAdvertisements;
   }

   @JsonIgnore
   public NavigableSet<BgpAdvertisement> getSentIbgpAdvertisements() {
      return _sentIbgpAdvertisements;
   }

   @JsonProperty(STATIC_ROUTES_VAR)
   public NavigableSet<StaticRoute> getStaticRoutes() {
      return _staticRoutes;
   }

   @JsonProperty(ZONES_VAR)
   public NavigableMap<String, Zone> getZones() {
      return _zones;
   }

   public void initBgpAdvertisements() {
      _bgpAdvertisements = new TreeSet<BgpAdvertisement>();
      _originatedAdvertisements = new TreeSet<BgpAdvertisement>();
      _originatedEbgpAdvertisements = new TreeSet<BgpAdvertisement>();
      _originatedIbgpAdvertisements = new TreeSet<BgpAdvertisement>();
      _receivedAdvertisements = new TreeSet<BgpAdvertisement>();
      _receivedEbgpAdvertisements = new TreeSet<BgpAdvertisement>();
      _receivedIbgpAdvertisements = new TreeSet<BgpAdvertisement>();
      _sentAdvertisements = new TreeSet<BgpAdvertisement>();
      _sentEbgpAdvertisements = new TreeSet<BgpAdvertisement>();
      _sentIbgpAdvertisements = new TreeSet<BgpAdvertisement>();
   }

   public void initRoutes() {
      _routes = new TreeSet<PrecomputedRoute>();
   }

   @JsonProperty(AS_PATH_ACCESS_LISTS_VAR)
   public void setAsPathAccessLists(
         NavigableMap<String, AsPathAccessList> asPathAccessLists) {
      _asPathAccessLists = asPathAccessLists;
   }

   @JsonProperty(BGP_PROCESS_VAR)
   public void setBgpProcess(BgpProcess process) {
      _bgpProcess = process;
   }

   @JsonProperty(COMMUNITIES_VAR)
   public void setCommunities(NavigableSet<Long> communities) {
      _communities = communities;
   }

   @JsonProperty(COMMUNITY_LISTS_VAR)
   public void setCommunityLists(
         NavigableMap<String, CommunityList> communityLists) {
      _communityLists = communityLists;
   }

   public void setConfigurationFormat(ConfigurationFormat configurationFormat) {
      _configurationFormat = configurationFormat;
   }

   public void setDefaultCrossZoneAction(LineAction defaultCrossZoneAction) {
      _defaultCrossZoneAction = defaultCrossZoneAction;
   }

   public void setDefaultInboundAction(LineAction defaultInboundAction) {
      _defaultInboundAction = defaultInboundAction;
   }

   @JsonProperty(GENERATED_ROUTES_VAR)
   public void setGeneratedRoutes(NavigableSet<GeneratedRoute> generatedRoutes) {
      _generatedRoutes = generatedRoutes;
   }

   @JsonProperty(IKE_GATEWAYS_VAR)
   public void setIkeGateways(NavigableMap<String, IkeGateway> ikeGateways) {
      _ikeGateways = ikeGateways;
   }

   @JsonProperty(IKE_POLICIES_VAR)
   public void setIkePolicies(NavigableMap<String, IkePolicy> ikePolicies) {
      _ikePolicies = ikePolicies;
   }

   @JsonProperty(IKE_PROPOSALS_VAR)
   public void setIkeProposals(NavigableMap<String, IkeProposal> ikeProposals) {
      _ikeProposals = ikeProposals;
   }

   @JsonProperty(INTERFACES_VAR)
   public void setInterfaces(NavigableMap<String, Interface> interfaces) {
      _interfaces = interfaces;
   }

   @JsonProperty(IP_ACCESS_LISTS_VAR)
   public void setIpAccessLists(NavigableMap<String, IpAccessList> ipAccessLists) {
      _ipAccessLists = ipAccessLists;
   }

   @JsonProperty(IPSEC_POLICIES_VAR)
   public void setIpsecPolicies(NavigableMap<String, IpsecPolicy> ipsecPolicies) {
      _ipsecPolicies = ipsecPolicies;
   }

   @JsonProperty(IPSEC_PROPOSALS_VAR)
   public void setIpsecProposals(
         NavigableMap<String, IpsecProposal> ipsecProposals) {
      _ipsecProposals = ipsecProposals;
   }

   @JsonProperty(IPSEC_VPNS_VAR)
   public void setIpsecVpns(NavigableMap<String, IpsecVpn> ipsecVpns) {
      _ipsecVpns = ipsecVpns;
   }

   @JsonProperty(ISIS_PROCESS_VAR)
   public void setIsisProcess(IsisProcess process) {
      _isisProcess = process;
   }

   @JsonProperty(OSPF_PROCESS_VAR)
   public void setOspfProcess(OspfProcess process) {
      _ospfProcess = process;
   }

   @JsonProperty(POLICY_MAPS_VAR)
   public void setPolicyMaps(NavigableMap<String, PolicyMap> policyMaps) {
      _policyMaps = policyMaps;
   }

   public void setRoles(RoleSet roles) {
      _roles = roles;
   }

   @JsonProperty(ROUTE_FILTER_LISTS_VAR)
   public void setRouteFilterLists(
         NavigableMap<String, RouteFilterList> routeFilterLists) {
      _routeFilterLists = routeFilterLists;
   }

   @JsonProperty(ROUTING_POLICIES_VAR)
   public void setRoutingPolicies(
         NavigableMap<String, RoutingPolicy> routingPolicies) {
      _routingPolicies = routingPolicies;
   }

   @JsonProperty(STATIC_ROUTES_VAR)
   public void setStaticRoutes(NavigableSet<StaticRoute> staticRoutes) {
      _staticRoutes = staticRoutes;
   }

   @JsonProperty(ZONES_VAR)
   public void setZones(NavigableMap<String, Zone> zones) {
      _zones = zones;
   }

   public JSONObject toJson() throws JSONException {
      JSONObject jObj = new JSONObject();
      jObj.put(BfJson.KEY_NODE_NAME, getHostname());
      return jObj;
   }
}
