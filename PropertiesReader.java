import java.io.*;
import java.util.*;

public class PropertiesReader {

    private String uuid, mac, username, token, handshakeAddress, pingAddress, postMethod, controllerName;

    public PropertiesReader() {

    	Properties prop = new Properties();
    	InputStream input = null;

    	try {
    		input = new FileInputStream("configuration.properties");

    		// load a properties file
    		prop.load(input);
    		// get the property value
            this.handshakeAddress = prop.getProperty("handshakeAddress");
            this.controllerName = prop.getProperty("controllerName");
            this.pingAddress = prop.getProperty("pingAddress");
            this.postMethod = prop.getProperty("postMethod");//might need deletetion
            this.username = prop.getProperty("username");
            this.token = prop.getProperty("token");
            this.uuid = prop.getProperty("uuid");
            this.mac = prop.getProperty("mac");

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

    public String getHandshakeAddress() {
        return this.handshakeAddress;
    }

    public String getControllerName() {
        return this.controllerName;
    }

    public String getPingAddress() {
        return this.pingAddress;
    }


    public String getPostMethod() {
        return this.postMethod;
    }

    public String getUsername() {
        return this.username;
    }

    public String getToken() {
        return this.token;
    }

    public String getUuid() {
        return this.uuid;
    }

    public String getMac() {
        return this.mac;
    }
}
