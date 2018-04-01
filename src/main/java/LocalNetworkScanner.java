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
             //this.localIP = InetAddress.getLocalHost().getAddress();
             this.localIP[3] = (byte)lastIpPart;
             hostToPing = InetAddress.getByAddress(this.localIP);
             output = hostToPing.toString().substring(1);
             hostName = hostToPing.getHostName();

             if (hostToPing.isReachable(2000)) {
                 System.out.println("Local IP: " + output + " Name: " + hostName);
             }

         }
         catch (UnknownHostException uhe) {
             uhe.printStackTrace();
         }
         catch (IOException ioe) {
             ioe.printStackTrace();
         }
     }

     public static void main(String [] args) {
         /*
         try {
             for (int IPSuffix = 1; IPSuffix <= 254; IPSuffix++) {
                 LocalNetworkScanner scan = new LocalNetworkScanner(IPSuffix);
                 scan.start();
                 Thread.sleep(20);
             }
         }
         catch (InterruptedException iee) {
             iee.printStackTrace();
             Thread.currentThread().interrupt();  // set interrupt flag
             System.out.println("Failed");
         }
         */
     }
 }
