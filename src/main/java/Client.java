import java.io.*;
import java.util.*;

public class Client {

    private static Map<String, String> credentials = new HashMap<String, String>();

    private static APIEndpoint APIConfiguration;
    private static Handshake handshakeConfiguration;
    private static POST POSTConfiguration;

    private static String postResponse, answer = null;
    private static String[] postResponseParts;
    private static boolean exceptionFlag;

    private static String errorChecking(String response) {
        try {
            if (!response.contains(",")) {
                if (response.equals("POST_REQUIRED")) {
                    throw new responseException("Error while sending request. The request must be of type POST.");
                }
                else if (response.equals("FORM_DATA_MISSING")) {
                    throw new responseException("Some required fields (username, password, mac, name) were not sent to the server.");
                }
                else if (response.equals("FORM_DATA_EMPTY")) {
                    throw new responseException("Some required fields are not set.");
                }
                else if (response.equals("INVALID_USERNAME")) {
                    throw new responseException("Invalid username.");
                }
                else if (response.equals("INVALID_PASSWORD")) {
                    throw new responseException("Invalid password.");
                }
                else if (response.equals("INVALID_MAC")) {
                    throw new responseException("Invalid mac address.");
                }
                else if (response.equals("INVALID_NAME")) {
                    throw new responseException("Invalid controller name.");
                }
                else if (response.equals("ACCOUNT_DOES_NOT_EXIST")) {
                    throw new responseException("There is no account matching this username.");
                }
                else if (response.equals("INCORRECT_PASSWORD")) {
                    throw new responseException("There is no account matching this password.");
                }
                else if (response.equals("INCORRECT_PASSWORD")) {
                    throw new responseException("There is no account matching this password.");
                }
                else if (response.equals("MAC_ADDRESS_DUPLICATE")) {
                    throw new responseException("A hub with the same MAC address already exists.");
                }
                else if (response.equals("HUB_NAME_DUPLICATE")) {
                    throw new responseException("A hub with the same name already exists.");
                }
            }
        }
        catch (responseException rex) {
            exceptionFlag = true;
            answer = rex.getMessage();
            System.out.println(rex.getMessage());
        }
        return answer;
    }

    public static void main (String[] args) {
        try {
            File configuration = new File("src/main/java/configuration");

            Properties applicationProperties = new Properties();

            if(configuration.exists() && !configuration.isDirectory()) {
                FileInputStream in = new FileInputStream("configuration");
                applicationProperties.load(in);
                /*
                if (applicationProperties.contains()) {

                }
                */
                if (applicationProperties.getProperty("postMethod") != "ping") {
                    applicationProperties.setProperty("postMethod", "ping");
                }

                //wake on lan functionality

                in.close();
            }
            else {
                APIConfiguration = new APIEndpoint();
                applicationProperties.setProperty("handshakeAddress", APIConfiguration.getHandshakeAddress());
                applicationProperties.setProperty("pingAddress", APIConfiguration.getPingAddress());

                handshakeConfiguration = new Handshake();
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

            POSTConfiguration = new POST(credentials);

            if (applicationProperties.getProperty("postMethod") == "handshake") {
                postResponse = POSTConfiguration.getResponse().toString();
                if (errorChecking(postResponse) == null) {//todo only respond to know errors and messages
                    postResponseParts = postResponse.split("\\,", 3);
                    applicationProperties.setProperty("uuid", postResponseParts[1]);
                    applicationProperties.setProperty("token", postResponseParts[2]);
                }
                else {
                    System.out.println("\nconfiguration creation was not succesfull.");
                }
            }
            else if (applicationProperties.getProperty("postMethod") == "ping") {
                //magic goes here
            }

            if (!exceptionFlag) {
                FileOutputStream out = new FileOutputStream("configuration");
                applicationProperties.store(out, "DO-NOT-MAKE-ANY-CHANGES");
                out.close();
            }

        }
        catch (FileNotFoundException fnfex) {
            fnfex.printStackTrace();
        }
        catch (IOException ioex) {
            ioex.printStackTrace();
        }
    }
}
