import java.io.*;
import java.util.*;
import java.net.*;
import javax.net.ssl.*;

public class POSTRequest {
	private int responseCode;
	private StringBuffer response;
	private String url, urlParameters, stringDomain;
	private String[] urlParts, domainParts;
	private InetAddress domainToPOST;
	private boolean isSuccessfullState;

	public POSTRequest (Map<String, String> credentials) {
		try {
			if (credentials.get("postMethod").equals("registration")){
				this.url = credentials.get("registrationAddress");

				this.urlParameters = ("username=" + credentials.get("username") +
				"&password=" + credentials.get("password") +
				"&mac_and_name=" + credentials.get("macAndName"));
			}
			else if (credentials.get("postMethod").equals("connection")) {
				this.url = credentials.get("connectionAddress");

				this.urlParameters = ("username=" + credentials.get("username") +
				"&api_key=" + credentials.get("token"));
			}

			this.urlParts = this.url.split("(//)");
			this.stringDomain = this.urlParts[1];
			this.domainParts = this.stringDomain.split("(/)");
			this.domainToPOST = InetAddress.getByName(this.domainParts[0]);

			if (this.domainToPOST.isReachable(5000)) {
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
}
