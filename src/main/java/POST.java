import java.io.*;
import java.util.*;
import java.net.*;
//import java.net.InetAddress;
import javax.net.ssl.*;

public class POST {
	private int responseCode;
	private StringBuffer response;
	private String url, urlParameters, stringDomain;
	private String[] urlParts, domainParts;
	private InetAddress domainToPOST;
	private boolean isSuccessfullState;

	public POST (Map<String, String> credentials) {
		try {
			if (credentials.get("postMethod") == "handshake"){
				this.url = credentials.get("handshakeAddress");

				this.urlParameters = ("username=" + credentials.get("username") +
				"&password=" + credentials.get("password") +
				"&mac=" + credentials.get("mac") +
				"&name=" + credentials.get("controllerName"));
			}
			else if (credentials.get("postMethod") == "ping") {
				this.url = credentials.get("pingAddress");
				//MUST ADD AFTER TASOS CREATES PINGING
				this.urlParameters = ("username=" + credentials.get("username") +
				"&uuid=" + credentials.get("uuid") +
				"&token=" + credentials.get("token") +
				"&mac=" + credentials.get("mac") +
				"&status=" + credentials.get("status"));
			}

			this.urlParts = this.url.split("(//)");
			this.stringDomain = this.urlParts[1];
			this.domainParts = this.stringDomain.split("(/)");
			this.domainToPOST = InetAddress.getByName(this.domainParts[0]);

			if (domainToPOST.isReachable(3000)) {
				isSuccessfullState = true;
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
			else {
				throw new responseException("Server is not reachable.");
			}

		}
		catch (responseException rex) {
			this.isSuccessfullState = false;
            System.out.println(rex.getMessage());
        }
		catch (MalformedURLException ex) {
            ex.printStackTrace();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
	}
	public boolean isSuccessfull() {
		return this.isSuccessfullState;
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
