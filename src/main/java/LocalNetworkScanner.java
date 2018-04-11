import java.io.*;
import java.net.*;
import java.util.*;

class LocalNetworkScanner extends Thread {

     private int lastIpPart;
     private byte[] localIP;
     private InetAddress hostToPing;
     private String output, hostName;

     LocalNetworkScanner (int lastIpPart, byte[] localIP) {
         this.lastIpPart = lastIpPart;
         this.localIP = localIP;
     }

     public void run() {
         try {
             this.localIP[3] = (byte)lastIpPart;
             this.hostToPing = InetAddress.getByAddress(this.localIP);
             this.output = hostToPing.toString().substring(1);
             this.hostName = hostToPing.getHostName();

             if (this.hostToPing.isReachable(2000)) {
                 System.out.println("Local IP: " + this.output + " Name: " + this.hostName);
             }

         }
         catch (UnknownHostException uhe) {
             uhe.printStackTrace();
         }
         catch (IOException ioe) {
             ioe.printStackTrace();
         }
     }
 }
