import java.net.*;

public class local_ip {
    public local_ip () {
        try {
            InetAddress me = InetAddress.getLocalHost();
            String dottedQuad = me.getHostAddress();
            System.out.println("Local IP: " + dottedQuad);
        } 
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}