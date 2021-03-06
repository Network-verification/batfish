package org.batfish.datamodel.answers;

import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.regex.Pattern;

import org.batfish.datamodel.Configuration;
import org.batfish.datamodel.PrecomputedRoute;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIdentityReference;

public class RoutesAnswerElement implements AnswerElement {

   private SortedSet<PrecomputedRoute> _routes;

   private SortedMap<String, SortedSet<PrecomputedRoute>> _routesByHostname;

   @JsonCreator
   public RoutesAnswerElement() {
   }

   public RoutesAnswerElement(Map<String, Configuration> configurations,
         Pattern nodeRegex) {
      _routes = new TreeSet<PrecomputedRoute>();
      _routesByHostname = new TreeMap<String, SortedSet<PrecomputedRoute>>();
      for (Entry<String, Configuration> e : configurations.entrySet()) {
         String hostname = e.getKey();
         if (!nodeRegex.matcher(hostname).matches()) {
            continue;
         }
         Configuration c = e.getValue();
         SortedSet<PrecomputedRoute> routes = c.getRoutes();
         _routes.addAll(routes);
         _routesByHostname.put(hostname, routes);
      }
   }

   public SortedSet<PrecomputedRoute> getRoutes() {
      return _routes;
   }

   @JsonIdentityReference(alwaysAsId = true)
   public SortedMap<String, SortedSet<PrecomputedRoute>> getRoutesByHostname() {
      return _routesByHostname;
   }

   public void setRoutes(SortedSet<PrecomputedRoute> routes) {
      _routes = routes;
   }

   public void setRoutesByHostname(
         SortedMap<String, SortedSet<PrecomputedRoute>> routesByHostname) {
      _routesByHostname = routesByHostname;
   }

}
