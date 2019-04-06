import java.util.*;
import java.util.regex.*;

/**
 * This class get the registration and connection urls and make sure that there are valid.
 */
class APIEndpoint {

    /**
     * We keep the registration and connection urls
     */
    private String registrationAddress, connectionAddress;

    /**
     * This method uses regex to register a valid url
     *
     * @param URL This is the url as a string.
     * @return The validation state of the url.
     */
    private boolean checkUrl(String URL) {
        String urlRegex = "^(http://www\\.|https://www\\.|http://|https://)?" +
                "[a-z0-9]+([\\-.][a-z0-9]+)*\\.[a-z]{2,5}(:[0-9]{1,5})?(/.*)?$";
        return Pattern.compile(urlRegex).matcher(URL).matches();
    }

    /**
     * This method takes the registration and connection address from the user,
     * to register and connect the user to the server.
     */
    void getUrls() {
        try {
            /*
             * Loop for taking a valid registration url.
             */
            // registrationAddress = "https://wols.geocfu.me/api/registration.php";
            System.out.println("Please enter your API endpoint URL for the registration.");
            while (true)
                if (checkUrl(registrationAddress = new Scanner(System.in).nextLine())) {
                    System.out.println("Registration URL (" + registrationAddress + ") set.");
                    break;
                } else
                    System.out.println("Please type a valid URL.");

            /*
             * Loop for taking a valid registration url.
             */
            // connectionAddress = "https://wols.geocfu.me/api/connection.php";
            System.out.println("Please enter your API endpoint URL for the connection.");
            while (true)
                if (checkUrl(connectionAddress = new Scanner(System.in).nextLine())) {
                    System.out.println("Connection URL (" + registrationAddress + ") set.");
                    break;
                } else
                    System.out.println("Please type a valid URL.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Getter for registrationAddress url field
     */
    String getRegistrationAddress() {
        return registrationAddress;
    }

    /**
     * Getter for connectionAddress url field
     */
    String getConnectionAddress() {
        return connectionAddress;
    }
}
