import java.io.*;
import java.util.*;

public class PropertiesReader {

    private static Map<String, String> propertiesCredentials = new HashMap<String, String>();

    public static void main(String[] args) {

    	Properties prop = new Properties();
    	InputStream input = null;

    	try {
    		input = new FileInputStream("configuration.properties");

    		// load a properties file
    		prop.load(input);

    		// get the property value
            propertiesCredentials.put("uuid", prop.getProperty("uuid"));
    		propertiesCredentials.put("mac", prop.getProperty("mac"));
            propertiesCredentials.put("username", prop.getProperty("username"));
            propertiesCredentials.put("token", prop.getProperty("token"));

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

    public Map<String, String> getProperties(){
        return propertiesCredentials;
    }
}
