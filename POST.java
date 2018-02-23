import java.io.*;
import java.util.*;
import java.net.*;
import javax.net.ssl.*;

public class POST {
	private int responseCode;
	private StringBuffer response;
	private String url, urlParameters;

	public POST (Map<String, String> credentials) {
		try {
			if (credentials.get("postMethod") == "handshake"){
				this.url = credentials.get("handshakeAddress");
				this.urlParameters = ("username=" + credentials.get("username") +
				"&password=" + credentials.get("password") +
				"&mac=" + credentials.get("mac") +
				"&name=" + credentials.get("controllerName"));
			}
			else {//MUST ADD AFTER TASOS CREATES PINGING
				this.url = credentials.get("pingAddress");
				this.urlParameters = ("username=" + credentials.get("username") +
				"&password=" + credentials.get("password") +
				"&uuid=" + credentials.get("uuid") +
				"&token=" + credentials.get("token") +
				"&mac=" + credentials.get("mac") +
				"&status=" + credentials.get("status"));
			}
			URL obj = new URL(this.url);
			HttpsURLConnection connection =
									(HttpsURLConnection)obj.openConnection();
			//add request header
			connection.setRequestMethod("POST");
			// Send post request
			connection.setDoOutput(true);
			DataOutputStream wr =
							new DataOutputStream(connection.getOutputStream());
			wr.writeBytes(this.urlParameters);
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
