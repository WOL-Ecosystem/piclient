import java.io.*;
import java.util.*;

public class PropertiesWriter {

    public PropertiesWriter (Map<String, String> credentials) {

        Properties configuration = new Properties();
        OutputStream output = null;
        try {
            output = new FileOutputStream("configuration.properties", true);
            // set the properties value
            /*
            configuration.setProperty("handshakeAddress", credentials.get("handshakeAddress"));
            configuration.setProperty("pingAddress", credentials.get("pingAddress"));
            */
            configuration.setProperty("controllerName", credentials.get("controllerName"));
            configuration.setProperty("postMethod", credentials.get("postMethod"));
            configuration.setProperty("username", credentials.get("username"));
            configuration.setProperty("token", credentials.get("token"));
            configuration.setProperty("uuid", credentials.get("uuid"));
            configuration.setProperty("mac", credentials.get("mac"));
            // save properties to project root folder
            configuration.store(output, "PropertiesWriter");

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
