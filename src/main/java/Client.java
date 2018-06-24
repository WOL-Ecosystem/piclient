import java.io.*;
import java.util.*;
import java.net.*;

import java.util.regex.*;

public class Client {

    private static Map<String, String> credentials = new HashMap<String, String>();

    private static APIEndpoint APIConfiguration;
    private static Registration registrationConfiguration;
    private static POSTRequest POSTConfiguration;
    private static LocalNetworkScanner scan;

    private static String postResponse, postResponseApiKey, answer, macToWake;
    private static boolean exceptionFlag;

    private static Pattern pattern;
    private static Matcher matcher;
    private static int start;

    private static String errorChecking(String response) {
        try {
            if (response.contains("SUCCESS")) {
                answer = "validResponse";
            }
            else {
                throw new responseException(response);
            }
        }
        catch (responseException rex) {
            exceptionFlag = true;
            answer = "invalidResponse";
            System.out.println(rex.getMessage());
        }
        return answer;
    }

    public static void main (String[] args) {
        try {
            File configuration = new File("configuration");

            Properties applicationProperties = new Properties();

            if(configuration.exists() && !configuration.isDirectory()) {
                FileInputStream in = new FileInputStream("configuration");
                applicationProperties.load(in);
                in.close();

                if (applicationProperties.getProperty("postMethod").equals("registration")) {
                    applicationProperties.setProperty("postMethod", "connection");
                    System.out.println("First connection after registration.");
                }
                else if (applicationProperties.getProperty("postMethod").equals("connection")) {
                    System.out.println("Normal operation.");
                }
                credentials.put("token", applicationProperties.getProperty("token"));
            }
            else {
                APIConfiguration = new APIEndpoint();
                applicationProperties.setProperty("registrationAddress", APIConfiguration.getRegistrationAddress());
                applicationProperties.setProperty("connectionAddress", APIConfiguration.getConnectionAddress());

                registrationConfiguration = new Registration();
                applicationProperties.setProperty("username", registrationConfiguration.getUsername());
                applicationProperties.setProperty("macAndName", registrationConfiguration.getMacAndName());

                credentials.put("password", registrationConfiguration.getAuthPassword());

                applicationProperties.setProperty("postMethod", "registration");
            }

            credentials.put("postMethod", applicationProperties.getProperty("postMethod"));
            credentials.put("registrationAddress", applicationProperties.getProperty("registrationAddress").replace("\\", ""));
            credentials.put("connectionAddress", applicationProperties.getProperty("connectionAddress").replace("\\", ""));
            credentials.put("username", applicationProperties.getProperty("username"));
            credentials.put("macAndName", applicationProperties.getProperty("macAndName").replace("\\", ""));

            POSTConfiguration = new POSTRequest(credentials);

            postResponse = POSTConfiguration.getResponse().toString();

            if (errorChecking(postResponse).equals("validResponse")) {
                System.out.println(postResponse);
                if (postResponse.contains("API_KEY")) {
                    int found = postResponse.indexOf("API_KEY");
                    postResponseApiKey = postResponse.substring(found + 10 , found + 74);
                    applicationProperties.setProperty("token", postResponseApiKey);
                    System.out.println("You have been registered successfully!");
                }
                else if (postResponse.contains("wakeUp")) {
                    while (true) {
                        int found = postResponse.indexOf("wakeUp", start);
                        if (found != -1) {
                            macToWake = postResponse.substring(found + 9, found + 26);
                            MagicPacket wakeTarget = new MagicPacket(macToWake);
                        }
                        if (found == -1) break;
                        start = found + 5;  // move start up for next iteration
                    }
                }
                else if (postResponse.contains("NO_ACTION_REQUIRED")){
                    System.out.println("No action required. Stoping gracefully.");
                }
                else {
                    System.out.println("Unknown server response.");
                }
            }
            FileOutputStream out = new FileOutputStream("configuration");
            applicationProperties.store(out, "DO-NOT-MAKE-ANY-CHANGES");
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
