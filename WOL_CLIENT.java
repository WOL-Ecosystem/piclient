import java.io.*;
import java.util.*;

public class WOL_CLIENT {
    public static void main (String[] args) {
        Map<String, String> user_data = new HashMap<String, String>();
        //get status of the pc
        new config();
        user_data.put("address", "https://wol.sht.gr/api.php");
        user_data.put("user", "george");
        user_data.put("pass", "");
        user_data.put("uuid", "");
        user_data.put("token", "$2y$10$DYAg7RrAp.i9TZawYxk1tuX67QSFDDQUu8cfnd1BNqAVl0EtFcVFa");
        user_data.put("mac", "4D2A90EDBB16");
        user_data.put("magicmac", "4D:2A:90:ED:BB:16");
        user_data.put("status", "1");
        POST requestPOST = new POST(user_data);
        System.out.println(requestPOST.toString()); // testing
        // 0 do nothing
        // 1 boot target computer
        // 2 shutdown target computer
        if (Integer.parseInt(requestPOST.getResponse().toString()) == 1) {
            // wake the pc
            // default local ip; all pcs listen
            MagicPacket wakeonlan = new MagicPacket(user_data, "255.255.255.255");
        }
        else if (Integer.parseInt(requestPOST.getResponse().toString()) == 2) {
            //pc is on
            // send shutdown signal to target computer
            System.out.println("Server responed with 2, shuting down target pc");
        }
        else {
            // ping successful
        }
    }
}
