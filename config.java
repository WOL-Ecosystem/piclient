import java.io.*;
import java.util.*;

public class config {
    public config() {
        Properties config = new Properties();
        OutputStream output = null;
        UUID uuid = UUID.randomUUID();
        try {
            output = new FileOutputStream("config.properties");
            // set the properties value
            config.setProperty("address", "https://wol.sht.gr/api.php");
            config.setProperty("user", "george");
            config.setProperty("token", "RANDOM_STRING");
            config.setProperty("uuid", String.valueOf(uuid));
            config.setProperty("mac", "4D2A90EDBB16");
            config.setProperty("status", "1");
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
