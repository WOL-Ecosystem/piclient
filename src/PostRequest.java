import java.io.*;
import java.util.*;
import java.net.*;
import javax.net.ssl.*;

/**
 * This class is responsible to make the PostRequest to the server and return the results.
 */
class PostRequest {

    /**
     * This method makes the request to the server.
     *
     * @param credentials In this we store names and values of our request data.
     * @return The response of the server as a string or and empty string in case of error
     */
    static String makeRequest(Map<String, String> credentials) {
        try {
            String url = null;
            String urlParameters = null;
            if (credentials.get("postMethod").equals("registration")) {
                url = credentials.get("registrationAddress");
                urlParameters = ("username=" + credentials.get("username") + "&password=" + credentials.get("password") +
                        "&mac_and_name=" + credentials.get("macAndName"));
            } else if (credentials.get("postMethod").equals("connection")) {
                url = credentials.get("connectionAddress");
                urlParameters = ("username=" + credentials.get("username") +
                        "&api_key=" + credentials.get("token"));
            }

            assert url != null;
            String stringDomain = url.split("(//)")[1];
            System.out.println(InetAddress.getByName(stringDomain.split("(/)")[0]));

            HttpsURLConnection connection = (HttpsURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();
            wr.close();

            StringBuffer response = new StringBuffer();
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null)
                response.append(inputLine);
            in.close();

            return String.valueOf(response);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
