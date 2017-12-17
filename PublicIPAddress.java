import java.io.*;
import java.net.*;

public class PublicIPAddress {

    private String publicIP;

    public PublicIPAddress () {
        try {
            URL IPFromInternet = new URL("https://ip.sht.gr/ip");// The only way of getting the public ip via Java
            BufferedReader in = new BufferedReader(new InputStreamReader(IPFromInternet.openStream()));
            this.publicIP = in.readLine();
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public String getPublicIPAddress () {
        return this.publicIP;
    }

    public String toString() {
        return "Public IP: " + this.getPublicIPAddress();
    }
}
