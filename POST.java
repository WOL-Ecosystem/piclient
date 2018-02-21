import java.io.*;
import java.util.*;
import java.net.*;
import javax.net.ssl.*;

public class POST {
	private int responseCode;
	private StringBuffer response;

	public POST (Map<String, String> credentials) {
		try {
			String url = credentials.get("address");
			URL obj = new URL(url);
			HttpsURLConnection connection =
									(HttpsURLConnection)obj.openConnection();
			//add request header
			connection.setRequestMethod("POST");

            String urlParameters = ("username=" + credentials.get("username") +
			"&password=" + credentials.get("password") +
			"&uuid=" + credentials.get("uuid") +
			"&token=" + credentials.get("token") +
			"&mac=" + credentials.get("mac") +
			"&status=" + credentials.get("status"));

			// Send post request
			connection.setDoOutput(true);
			DataOutputStream wr =
							new DataOutputStream(connection.getOutputStream());
			wr.writeBytes(urlParameters);
			wr.flush();
			wr.close();

			this.responseCode = connection.getResponseCode();

			BufferedReader in = new BufferedReader(
							new InputStreamReader(connection.getInputStream()));

			String inputLine;
			this.response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				this.response.append(inputLine);
			}
			in.close();
		}
		catch (MalformedURLException ex) {
            ex.printStackTrace();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
	}
	// ex 200,301,404 etc.
	public int getResponseCode() {
		return this.responseCode;
	}

	public StringBuffer getResponse() {
		return this.response;
	}

	public String toString() {
		return "\nResponse Code: " + getResponseCode() +
					"\nResponse: " + getResponse();
	}
}
