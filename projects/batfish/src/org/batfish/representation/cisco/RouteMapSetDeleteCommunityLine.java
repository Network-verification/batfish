package org.batfish.representation.cisco;

import java.util.List;

import org.batfish.datamodel.CommunityList;
import org.batfish.datamodel.Configuration;
import org.batfish.datamodel.PolicyMapSetDeleteCommunityLine;
import org.batfish.datamodel.PolicyMapSetLine;
import org.batfish.datamodel.routing_policy.statement.DeleteCommunity;
import org.batfish.datamodel.routing_policy.statement.Statement;
import org.batfish.main.Warnings;
import org.batfish.representation.VendorConfiguration;

public class RouteMapSetDeleteCommunityLine extends RouteMapSetLine {

   private static final long serialVersionUID = 1L;

   private String _listName;

   public RouteMapSetDeleteCommunityLine(String listName) {
      _listName = listName;
   }

   @Override
   public void applyTo(List<Statement> statements, CiscoConfiguration cc,
         Configuration c, Warnings w) {
      CommunityList list = c.getCommunityLists().get(_listName);
      if (list != null) {
         String msg = "match community line";
         StandardCommunityList standardCommunityList = cc
               .getStandardCommunityLists().get(_listName);
         if (standardCommunityList != null) {
            standardCommunityList.getReferers().put(this, msg);
         }
         ExpandedCommunityList expandedCommunityList = cc
               .getExpandedCommunityLists().get(_listName);
         if (expandedCommunityList != null) {
            expandedCommunityList.getReferers().put(this, msg);
         }
         statements.add(new DeleteCommunity(_listName));
      }
      else {
         w.redFlag("Reference to undefined community-list: " + _listName,
               VendorConfiguration.UNDEFINED);
      }
   }

   public String getListName() {
      return _listName;
   }

   @Override
   public RouteMapSetType getType() {
      return RouteMapSetType.DELETE_COMMUNITY;
   }

   @Override
   public PolicyMapSetLine toPolicyMapSetLine(CiscoConfiguration v,
         Configuration c, Warnings w) {
      CommunityList dcList = c.getCommunityLists().get(_listName);
      return new PolicyMapSetDeleteCommunityLine(dcList);
   }

}
