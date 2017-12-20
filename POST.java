import java.io.*;
import java.util.*;
import java.net.*;
import javax.net.ssl.*;

public class POST {
	private int responseCode;
	private StringBuffer response;

	public POST (Map<String, String> user_data) {
		try {
			String url = user_data.get("address");
			URL obj = new URL(url);
			HttpsURLConnection connection =
									(HttpsURLConnection)obj.openConnection();
			//add request header
			connection.setRequestMethod("POST");

            String urlParameters = ("user=" + user_data.get("user") +
			"&pass=" + user_data.get("pass") +
			"&uuid=" + user_data.get("uuid") +
			"&token=" + user_data.get("token") +
			"&mac=" + user_data.get("mac") +
			"&status=" + user_data.get("status"));

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
