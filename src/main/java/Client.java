import java.io.*;
import java.util.*;
import java.net.*;

public class Client {

    private static Map<String, String> credentials = new HashMap<String, String>();

    private static APIEndpoint APIConfiguration;
    private static Registration registrationConfiguration;
    private static POSTRequest POSTConfiguration;
    private static LocalNetworkScanner scan;

    private static String postResponse, answer;
    private static String[] postResponseParts;
    private static boolean exceptionFlag;
    private static byte[] hostIP;
    private static int localIPSuffix;

    private static String errorChecking(String response) {
        try {
            if (!response.contains(",")) {
                if (response.equals("POST_REQUIRED")) {
                    throw new responseException("Error while sending request. The request must be of type POST.");
                }
                else if (response.equals("FORM_DATA_MISSING")) {
                    throw new responseException("Some required fields (ex. email, password, local_pc_names) were not sent to the server.");
                }
                else if (response.equals("FORM_DATA_EMPTY")) {
                    throw new responseException("Some required fields are not set (ex. email, password, local_pc_names).");
                }
                else if (response.equals("INVALID_EMAIL")) {
                    throw new responseException("Invalid email.");
                }
                else if (response.equals("INVALID_PASSWORD")) {
                    throw new responseException("Invalid password.");
                }
                else if (response.equals("ACCOUNT_DOES_NOT_EXIST")) {
                    throw new responseException("There is no account matching this email.");
                }
                else if (response.equals("ACCOUNT_ALREADY_EXISTS")) {
                    throw new responseException("There is already an account matching this email.");
                }
                else if (response.equals("INCORRECT_PASSWORD")) {
                    throw new responseException("There is no account matching this password.");
                }
                else {
                    answer = "validResponse";
                }
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

                //todo: check if all the fields have not been tampered

                if (applicationProperties.getProperty("postMethod") != "connection") {
                    applicationProperties.setProperty("postMethod", "connection");

                    hostIP = InetAddress.getLocalHost().getAddress();
                    for (localIPSuffix = 1; localIPSuffix <= 254; localIPSuffix++) {
                        scan = new LocalNetworkScanner(localIPSuffix, hostIP);
                        scan.start();
                        Thread.sleep(20);
                    }
                }

                in.close();
            }
            else {
                APIConfiguration = new APIEndpoint();
                applicationProperties.setProperty("registrationAddress", APIConfiguration.getRegistrationAddress());
                applicationProperties.setProperty("connectionAddress", APIConfiguration.getConnectionAddress());

                registrationConfiguration = new Registration();
                applicationProperties.setProperty("email", registrationConfiguration.getEmail());
                applicationProperties.setProperty("password", registrationConfiguration.getAuthPassword());

                applicationProperties.setProperty("postMethod", "registration");
            }

            credentials.put("postMethod", applicationProperties.getProperty("postMethod"));
            credentials.put("registrationAddress", applicationProperties.getProperty("registrationAddress").replace("\\", ""));
            credentials.put("connectionAddress", applicationProperties.getProperty("connectionAddress").replace("\\", ""));
            credentials.put("email", applicationProperties.getProperty("email"));
            credentials.put("password", applicationProperties.getProperty("password"));

            POSTConfiguration = new POSTRequest(credentials);

            postResponse = POSTConfiguration.getResponse().toString();

            if (errorChecking(postResponse).equals("validResponse")) {

                if (applicationProperties.getProperty("postMethod") == "registration") {
                    System.out.println(postResponse);
                    FileOutputStream out = new FileOutputStream("configuration");
                    applicationProperties.store(out, "DO-NOT-MAKE-ANY-CHANGES");
                    out.close();
                }
                else if (applicationProperties.getProperty("postMethod") == "connection") {


                    //MagicPacket wakeTarget = new MagicPacket(credentials);
                }
            }
        }
        catch (FileNotFoundException fnfex) {
            fnfex.printStackTrace();
        }
        catch (IOException ioex) {
            ioex.printStackTrace();
        }
        catch (InterruptedException iee) {
            iee.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
}
