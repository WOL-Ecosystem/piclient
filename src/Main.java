import java.io.*;
import java.util.*;

/**
 * This is our Main class.
 */
public class Main {

    /**
     * This is our main method.
     *
     * @param args Read Java Doc
     */
    public static void main(String[] args) {
        try {
            Map<String, String> credentials = new HashMap<>();
            Properties applicationProperties = new Properties();
            if (new File("configuration").exists() && !new File("configuration").isDirectory()) {
                applicationProperties.load(new FileInputStream("configuration"));

                if (applicationProperties.getProperty("postMethod").equals("registration")) {
                    applicationProperties.setProperty("postMethod", "connection");
                    System.out.println("First connection after registration.");
                } else if (applicationProperties.getProperty("postMethod").equals("connection"))
                    System.out.println("Normal operation.");

                credentials.put("token", applicationProperties.getProperty("token"));
            } else {
                APIEndpoint APIConfiguration = new APIEndpoint();
                APIConfiguration.getUrls();
                applicationProperties.setProperty("registrationAddress", APIConfiguration.getRegistrationAddress());
                applicationProperties.setProperty("connectionAddress", APIConfiguration.getConnectionAddress());

                Registration registrationConfiguration = new Registration();
                registrationConfiguration.run();
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

            String postResponse = PostRequest.makeRequest(credentials);
            int start = 0;
            if (postResponse.contains("SUCCESS")) {

                System.out.println(postResponse);
                if (postResponse.contains("API_KEY")) {

                    int found = postResponse.indexOf("API_KEY");
                    applicationProperties.setProperty("token", postResponse.substring(found + 10, found + 74));
                    System.out.println("You have been registered successfully!");

                } else if (postResponse.contains("wakeUp")) {

                    while (true) {
                        int found = postResponse.indexOf("wakeUp", start);
                        if (found != -1)
                            MagicPacket.wakeOnLan(postResponse.substring(found + 9, found + 26));
                        if (found == -1)
                            break;
                        start = found + 5;  // move start up for next iteration
                    }

                } else if (postResponse.contains("NO_ACTION_REQUIRED")) {
                    System.out.println("No action required. Stopping gracefully.");
                } else {
                    System.out.println("Unknown server response.");
                }
            } else {
                System.out.println("\n Request error! \n");
            }
            applicationProperties.store(new FileOutputStream("configuration"), "DO-NOT-MAKE-ANY-CHANGES");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
