package org.batfish.representation.aws_vpcs;

import java.io.Serializable;

import org.batfish.common.BatfishLogger;
import org.batfish.representation.Ip;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class VgwTelemetry implements Serializable {

   private static final long serialVersionUID = 1L;

   private int _acceptedRouteCount;
   private Ip _outsideIpAddress;
   private String _status;
   private String _statusMessage;

   public VgwTelemetry(JSONObject jObj, BatfishLogger logger)
         throws JSONException {
      _status = jObj.getString(AwsVpcEntity.JSON_KEY_STATUS);
      _statusMessage = jObj.getString(AwsVpcEntity.JSON_KEY_STATUS_MESSAGE);
      _acceptedRouteCount = jObj
            .getInt(AwsVpcEntity.JSON_KEY_ACCEPTED_ROUTE_COUNT);
      _outsideIpAddress = new Ip(
            jObj.getString(AwsVpcEntity.JSON_KEY_OUTSIDE_IP_ADDRESS));

   }
}
