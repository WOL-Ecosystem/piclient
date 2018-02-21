import java.io.*;
import java.util.*;

public class Client {

    private static Map<String, String> credentials = new HashMap<String, String>();

    public static void main (String[] args) {
        //read .properties
        PropertiesReader configuration = new PropertiesReader();
        credentials.put("address", "https://wol.sht.gr/backend/ping");
        credentials.put("uuid", configuration.getUuid());
        credentials.put("mac", configuration.getMac());
        credentials.put("username", configuration.getUsername());
        credentials.put("token", configuration.getToken());
        //sent POST with the .properties credenntials
        POST POST = new POST(credentials);
        System.out.println(POST.toString()); // testing
    }
}
