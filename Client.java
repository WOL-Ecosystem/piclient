import java.io.*;
import java.util.*;

public class Client {

    private static Map<String, String> credentials = new HashMap<String, String>();

    public static void main (String[] args) {
        //read .properties
        PropertiesReader configuration = new PropertiesReader();
        //sent POST with the .properties credenntials
        POST POST = new POST(configuration.getProperties());
        System.out.println(POST.toString()); // testing







        // 0 do nothing
        // 1 boot target computer
        // 2 shutdown target computer
        if (Integer.parseInt(POST.getResponse().toString()) == 1) {
            // wake the pc
            // default local ip; all pcs listen
            //MagicPacket wakeonlan = new MagicPacket(user_data, "255.255.255.255");
        }
        else if (Integer.parseInt(POST.getResponse().toString()) == 2) {
            //pc is on
            // send shutdown signal to target computer
            System.out.println("Server responed with 2, shuting down target pc");// testing
        }
    }
}
