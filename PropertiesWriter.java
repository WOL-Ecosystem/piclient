import java.io.*;
import java.util.*;

public class PropertiesWriter {
    public PropertiesWriter(Map<String, String> credentials) {
        Properties config = new Properties();
        OutputStream output = null;
        try {
            output = new FileOutputStream("configuration.properties");
            // set the properties value
            config.setProperty("username", credentials.get("username"));
            config.setProperty("token", credentials.get("token"));
            config.setProperty("uuid", credentials.get("uuid"));
            config.setProperty("mac", credentials.get("mac"));
            // save properties to project root folder
            config.store(output, null);

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
