import java.io.*;
import java.net.*;

public class public_ip {
    public public_ip() {
        try {
            URL ipAdress = new URL("https://ip.sht.gr/ip");
            BufferedReader in = new BufferedReader(new InputStreamReader(ipAdress.openStream()));
            String ip = in.readLine();
            System.out.println("Public IP: " + ip);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}