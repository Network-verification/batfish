package org.batfish.datamodel;

import java.io.Serializable;

import org.batfish.common.BatfishException;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public class Ip implements Comparable<Ip>, Serializable {

   public static final Ip MAX = new Ip(0xFFFFFFFFl);

   private static final long serialVersionUID = 1L;

   public static final Ip ZERO = new Ip(0l);

   private static long ipStrToLong(String addr) {
      String[] addrArray = addr.split("\\.");
      if (addrArray.length != 4) {
         if (addr.startsWith("INVALID_IP")) {
            String[] tail = addr.split("\\(");
            if (tail.length == 2) {
               String[] longStrParts = tail[1].split("l");
               if (longStrParts.length == 2) {
                  String longStr = longStrParts[0];
                  return Long.parseLong(longStr);
               }
            }
         }
         throw new BatfishException("Invalid ip string: \"" + addr + "\"");
      }
      long num = 0;
      for (int i = 0; i < addrArray.length; i++) {
         int power = 3 - i;
         String segmentStr = addrArray[i];
         try {
            int segment = Integer.parseInt(segmentStr);
            num += ((segment % 256 * Math.pow(256, power)));
         }
         catch (NumberFormatException e) {
            throw new BatfishException("Invalid ip segment: \"" + segmentStr
                  + "\" in ip string: \"" + addr + "\"", e);
         }
      }
      return num;
   }

   private static long numSubnetBitsToSubnetLong(int numBits) {
      long val = 0;
      for (int i = 31; i > 31 - numBits; i--) {
         val |= ((long) 1 << i);
      }
      return val;
   }

   public static Ip numSubnetBitsToSubnetMask(int numBits) {
      long mask = numSubnetBitsToSubnetLong(numBits);
      return new Ip(mask);
   }

   private final int _hashCode;

   private final Long _ip;

   public Ip(long ipAsLong) {
      _ip = ipAsLong;
      _hashCode = _ip.hashCode();
   }

   @JsonCreator
   public Ip(String ipAsString) {
      _ip = ipStrToLong(ipAsString);
      _hashCode = _ip.hashCode();
   }

   public long asLong() {
      return _ip;
   }

   @Override
   public int compareTo(Ip rhs) {
      return Long.compare(_ip, rhs._ip);
   }

   @Override
   public boolean equals(Object o) {
      Ip rhs = (Ip) o;
      return _ip.equals(rhs._ip);
   }

   public Ip getClassMask() {
      long firstOctet = _ip >> 24;
      if (firstOctet <= 126) {
         return new Ip(0xFF000000l);
      }
      else if (firstOctet >= 128 && firstOctet <= 191) {
         return new Ip(0XFFFF0000l);
      }
      else if (firstOctet >= 192 && firstOctet <= 223) {
         return new Ip(0xFFFFFF00l);
      }
      else {
         return null;
      }
   }

   public Ip getNetworkAddress(int subnetBits) {
      long mask = numSubnetBitsToSubnetLong(subnetBits);
      return new Ip(_ip & mask);
   }

   public Ip getNetworkAddress(Ip mask) {
      return new Ip(_ip & mask.asLong());
   }

   public Ip getSubnetEnd(Ip mask) {
      return new Ip(_ip | mask.inverted().asLong());
   }

   public Ip getWildcardEndIp(Ip wildcard) {
      return new Ip(_ip | wildcard.asLong());
   }

   @Override
   public int hashCode() {
      return _hashCode;
   }

   public Ip inverted() {
      long invertedLong = (~_ip) & 0xFFFFFFFFl;
      return new Ip(invertedLong);
   }

   public String networkString(int prefixLength) {
      return toString() + "/" + prefixLength;
   }

   public String networkString(Ip mask) {
      return toString() + "/" + mask.numSubnetBits();
   }

   public int numSubnetBits() {
      int count = 0;
      int subnetInt = (int) _ip.longValue();
      while (subnetInt != 0) {
         subnetInt <<= 1;
         count++;
      }
      return count;
   }

   public int numWildcardBits() {
      int numBits = 0;
      for (long test = _ip; test != 0; test >>= 1) {
         numBits++;
      }
      return numBits;
   }

   @Override
   @JsonValue
   public String toString() {
      if (!valid()) {
         return "INVALID_IP(" + _ip + "l)";
      }
      else {
         return ((_ip >> 24) & 0xFF) + "." + ((_ip >> 16) & 0xFF) + "."
               + ((_ip >> 8) & 0xFF) + "." + (_ip & 0xFF);
      }
   }

   public boolean valid() {
      return _ip >= 0 && _ip <= 0xFFFFFFFFl;
   }

}
