import java.io.*;
import java.util.*;

public class Client {

    private static Map<String, String> credentials = new HashMap<String, String>();

    public static void main (String[] args) {
        //read .properties
        PropertiesReader configuration = new PropertiesReader();
        credentials.put("postMethod", configuration.getHandshakeAddress());
        credentials.put("pingAddress", configuration.getPingAddress());
        credentials.put("username", configuration.getUsername());
        credentials.put("token", configuration.getToken());
        credentials.put("uuid", configuration.getUuid());
        credentials.put("mac", configuration.getMac());
        //sent POST with the .properties credenntials
        POST POST = new POST(credentials);
        System.out.println(POST.toString()); // testing
    }
}
