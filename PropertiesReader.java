import java.io.*;
import java.util.*;

public class PropertiesReader {

    private String uuid, mac, username, token = "";

    public PropertiesReader() {

    	Properties prop = new Properties();
    	InputStream input = null;

    	try {
    		input = new FileInputStream("configuration.properties");

    		// load a properties file
    		prop.load(input);
    		// get the property value
            this.uuid = prop.getProperty("uuid");
            this.mac = prop.getProperty("mac");
            this.username = prop.getProperty("username");
            this.token = prop.getProperty("token");


    	} catch (IOException ex) {
    		ex.printStackTrace();
    	} finally {
    		if (input != null) {
    			try {
    				input.close();
    			}
                catch (IOException e) {
    				e.printStackTrace();
    			}
    		}
    	}
    }

    public String getUuid() {
        return this.uuid;
    }

    public String getMac() {
        return this.mac;
    }

    public String getUsername() {
        return this.username;
    }

    public String getToken() {
        return this.token;
    }
}
