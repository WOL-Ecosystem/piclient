import java.io.*;
import java.util.*;
import java.net.*;

public class Client {

    private static Map<String, String> credentials = new HashMap<String, String>();

    private static APIEndpoint APIConfiguration;
    private static Registration registrationConfiguration;
    private static POSTRequest POSTConfiguration;
    private static LocalNetworkScanner scan;

    private static String postResponse, postResponseApiKey, answer;
    private static boolean exceptionFlag;

    private static String errorChecking(String response) {
        try {
            if (response.equals("POST_REQUIRED")) {
                throw new responseException("Error while sending request. The request must be of type POST.");
            }
            else if (response.equals("FORM_DATA_MISSING")) {
                throw new responseException("Some required fields were not sent to the server.");
            }
            else if (response.equals("FORM_DATA_EMPTY")) {
                throw new responseException("Some required fields are not set.");
            }
            else if (response.equals("INVALID_USERNAME")) {
                throw new responseException("Invalid username.");
            }
            else if (response.equals("INVALID_AUTH_KEY")) {
                throw new responseException("Invalid authentication key.");
            }
            else if (response.equals("ACCOUNT_DOES_NOT_EXIST")) {
                throw new responseException("There is no account matching this email.");
            }
            else if (response.equals("ACCOUNT_ALREADY_EXISTS")) {
                throw new responseException("There is already an account matching this username.");
            }
            else if (response.equals("INCORRECT_AUTH_KEY")) {
                throw new responseException("There is no account matching this authentication key.");
            }
            else {
                answer = "validResponse";
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
                    System.out.println("first connection after registration");
                }
                else if (applicationProperties.getProperty("postMethod").equals("connection")) {
                    System.out.println("Normal operation");
                }
                credentials.put("token", applicationProperties.getProperty("token"));
            }
            else {
                APIConfiguration = new APIEndpoint();
                applicationProperties.setProperty("registrationAddress", APIConfiguration.getRegistrationAddress());
                applicationProperties.setProperty("connectionAddress", APIConfiguration.getConnectionAddress());

                registrationConfiguration = new Registration();
                applicationProperties.setProperty("username", registrationConfiguration.getUsername());
                applicationProperties.setProperty("targetMacAddress", registrationConfiguration.getMacAddress());

                credentials.put("password", registrationConfiguration.getAuthPassword());

                applicationProperties.setProperty("postMethod", "registration");
            }

            credentials.put("postMethod", applicationProperties.getProperty("postMethod"));
            credentials.put("registrationAddress", applicationProperties.getProperty("registrationAddress").replace("\\", ""));
            credentials.put("connectionAddress", applicationProperties.getProperty("connectionAddress").replace("\\", ""));
            credentials.put("username", applicationProperties.getProperty("username"));
            credentials.put("targetMacAddress", applicationProperties.getProperty("targetMacAddress").replace("\\", ""));


            POSTConfiguration = new POSTRequest(credentials);

            postResponse = POSTConfiguration.getResponse().toString();

            if (errorChecking(postResponse).equals("validResponse")) {

                if (applicationProperties.getProperty("postMethod").equals("registration")) {
                    if (postResponse.contains("API_KEY")) {
                        postResponseApiKey = postResponse.substring(12, 76);
                        applicationProperties.setProperty("token", postResponseApiKey);
                        System.out.println("You have been registered successfully!");
                    }
                    else {
                        System.out.println("Unknown server response.");
                    }
                }
                else if (applicationProperties.getProperty("postMethod").equals("connection")) {
                    System.out.println(postResponse);
                    //MagicPacket wakeTarget = new MagicPacket(credentials);
                }
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
