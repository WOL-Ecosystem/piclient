import java.io.*;
import java.util.*;

public class Client {

    private static Map<String, String> credentials = new HashMap<String, String>();

    public static void main (String[] args) {
        try {
            File configuration = new File("configuration");

            Properties applicationProperties = new Properties();

            if(configuration.exists() && !configuration.isDirectory()) {
                FileInputStream in = new FileInputStream("configuration");
                applicationProperties.load(in);
                if (applicationProperties.getProperty("postMethod") != "ping") {
                    applicationProperties.setProperty("postMethod", "ping");
                }

                //file exists move accordingly

                in.close();
            }
            else {
                APIEndpoint APIConfiguration = new APIEndpoint();
                applicationProperties.setProperty("handshakeAddress", APIConfiguration.getHandshakeAddress());
                applicationProperties.setProperty("pingAddress", APIConfiguration.getPingAddress());

                Handshake handshakeConfiguration = new Handshake();
                applicationProperties.setProperty("username", handshakeConfiguration.getUsername());
                applicationProperties.setProperty("controllerName", handshakeConfiguration.getControllerName());
                applicationProperties.setProperty("mac", handshakeConfiguration.getMacAddress());

                applicationProperties.setProperty("postMethod", "handshake");

                credentials.put("password", handshakeConfiguration.getPassword());
            }

            credentials.put("postMethod", applicationProperties.getProperty("postMethod"));
            credentials.put("handshakeAddress", applicationProperties.getProperty("handshakeAddress").replace("\\", ""));
            credentials.put("pingAddress", applicationProperties.getProperty("pingAddress").replace("\\", ""));
            credentials.put("username", applicationProperties.getProperty("username"));
            credentials.put("controllerName", applicationProperties.getProperty("controllerName"));
            credentials.put("mac", applicationProperties.getProperty("mac"));

            POST POSTConfiguration = new POST(credentials);
            StringBuffer stringBufferResponse = POSTConfiguration.getResponse();
            String stringResponse = stringBufferResponse.toString();

            //TODO check response and act accordingly
            String[] response = stringResponse.split("\\,");
            System.out.println(stringResponse);//testing
            applicationProperties.setProperty("uuid", response[1]);
            applicationProperties.setProperty("token", response[2]);
            System.out.println(response[0]); // testing

            //wake on lan functionality

            FileOutputStream out = new FileOutputStream("configuration");
            applicationProperties.store(out, null);
            out.close();
        }
        catch (FileNotFoundException fnfex) {
            fnfex.printStackTrace();
        }
        catch (IOException ioex) {
            ioex.printStackTrace();
        }
    }
}
