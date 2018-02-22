import java.io.*;
import java.util.*;

public class PropertiesCreator {

    private static String handshakeAddress, pingAddress;
    private static int option;
    public static void main(String [] args) {
        Scanner sc = new Scanner(System.in);
        Properties config = new Properties();
        OutputStream output = null;
        try {
            output = new FileOutputStream("configuration.properties");
            // set the properties value
            System.out.println("Please, input an address for the handshake.\nNOTE: If you use the service we provide press 1");
            option = sc.nextInt();
            if (option == 1) {
                 handshakeAddress = "https://wol.sht.gr/backend/handshake";
            }
            else {
                handshakeAddress = sc.nextLine();
            }
            System.out.println("Please, input an address for the pinging.\nNOTE: If you use the service we provide press 1");
            option = sc.nextInt();
            if (option == 1) {
                pingAddress = "https://wol.sht.gr/backend/ping";
            }
            else {
                pingAddress = sc.nextLine();
            }
            config.setProperty("hanshakeAddress", handshakeAddress);
            config.setProperty("pingAddress", pingAddress);
            config.setProperty("username", "");
            config.setProperty("token", "");
            config.setProperty("uuid", "");
            config.setProperty("mac", "");
            config.store(output, null);

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            sc.close();
            if (output != null) {
                try {
                    output.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
