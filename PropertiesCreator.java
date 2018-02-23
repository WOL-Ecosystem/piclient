//TODO: crate a method for url regex checking
import java.io.*;
import java.util.*;
import java.util.regex.*;

public class PropertiesCreator {

    private static String handshakeAddress, pingAddress, urlRegex;
    private static char option;
    private static boolean inputFlag, urlFlag, testUrlFlag;
    private static Pattern urlPattern;
    private static Matcher input;
    private static Scanner sc;

    private static boolean testUrl(String URL) {
        urlRegex = "^(http:\\/\\/www\\.|https:\\/\\/www\\." +
        "|http:\\/\\/|https:\\/\\/)?[a-z0-9]+([\\-\\.]"+
        "{1}[a-z0-9]+)*\\.[a-z]{2,5}(:[0-9]{1,5})?(\\/.*)?$";
        urlPattern = Pattern.compile(urlRegex);
        urlFlag = false;
        input = urlPattern.matcher(URL);
        if (input.matches() == true) {
            urlFlag = true;
        }
        return urlFlag;
    }

    public static void main(String [] args) {
        sc = new Scanner(System.in);
        Properties configuration = new Properties();
        OutputStream output = null;
        try {
            System.out.println("Do you want to use the default handshake URL?(y/n)");
            inputFlag = true;
            do {
                option = sc.next().charAt(0);
                if (option == 'y' || option == 'Y') {
                    inputFlag = false;
                    handshakeAddress = "https://wol.sht.gr/backend/handshake";
                    System.out.println("Default handshake URL (" +
                                                handshakeAddress + ") set.");
                }
                else if (option == 'n' || option == 'N') {
                    inputFlag = false;
                    testUrlFlag = true;
                    sc.nextLine();
                    do {
                        System.out.print("Type your custom handshake URL: ");
                        handshakeAddress = sc.nextLine();
                        if(testUrl(handshakeAddress)) {
                            testUrlFlag = false;
                            System.out.println("Custom handshake URL (" +
                                                handshakeAddress + ") set.");
                        }
                        else {
                            System.out.println("Please input a valid URL.");
                        }
                    } while(testUrlFlag);
                }
                else {
                    System.out.println("Please select a valid option");
                }
            } while (inputFlag);

            System.out.println("Do you want to use the default ping URL?(y/n)");
            inputFlag = true;
            do {
                option = sc.next().charAt(0);
                if (option == 'y' || option == 'Y') {
                    inputFlag = false;
                    pingAddress = "https://wol.sht.gr/backend/ping";
                    System.out.println("Default ping URL (" +
                                                        pingAddress + ") set.");
                }
                else if (option == 'n' || option == 'N') {
                    inputFlag = false;
                    testUrlFlag = true;
                    sc.nextLine();
                    do {
                        System.out.print("Type your custom ping URL: ");
                        pingAddress = sc.nextLine();
                        if(testUrl(pingAddress)) {
                            testUrlFlag = false;
                            System.out.println("Custom ping URL (" +
                                                        pingAddress + ") set.");
                        }
                        else {
                            System.out.println("Please input a valid URL.");
                        }
                    } while(testUrlFlag);
                }
                else {
                    System.out.println("Please select a valid option");
                }
            } while (inputFlag);
        }
        finally {
            sc.close();
        }

        try {
            output = new FileOutputStream("configuration.properties");
            //save .properties values
            configuration.setProperty("handshakeAddress", handshakeAddress);
            configuration.setProperty("pingAddress", pingAddress);
            /*
            configuration.setProperty("postMethod", "handshake");
            configuration.setProperty("username", "");
            configuration.setProperty("token", "");
            configuration.setProperty("uuid", "");
            configuration.setProperty("mac", "");
            */
            configuration.store(output, "PropertiesCreator");
            System.out.println("Success.\nconfiguration.properties has been created");

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
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
