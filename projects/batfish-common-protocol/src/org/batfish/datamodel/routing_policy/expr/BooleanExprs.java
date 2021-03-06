package org.batfish.datamodel.routing_policy.expr;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public enum BooleanExprs {
   CallExprContext,
   CallStatementContext,
   False,
   True;

   public static class StaticBooleanExpr extends AbstractBooleanExpr {
      /**
       *
       */
      private static final long serialVersionUID = 1L;

      private static final String TYPE_VAR = "type";

      private BooleanExprs _type;

      @JsonCreator
      public StaticBooleanExpr(@JsonProperty(TYPE_VAR) BooleanExprs type) {
         _type = type;
      }

      @JsonProperty(TYPE_VAR)
      public BooleanExprs getType() {
         return _type;
      }
   }

   public StaticBooleanExpr toStaticBooleanExpr() {
      return new StaticBooleanExpr(this);
   }
}
