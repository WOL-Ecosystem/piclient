import java.io.*;
import java.util.*;
import java.net.*;
import java.util.regex.*;

public class Handshake {

    private String usernameRegex, username, passwordRegex, password, controllerNameRegex, controllerName, macAddress;
    private Pattern usernamePattern, passwordPattern, controllerNamePattern;
    private Matcher testInput;
    private boolean usernameFlag, passwordFlag, controllerNameFlag;
    private Scanner sc;
    private InetAddress address;
    private NetworkInterface networkInterface;
    private byte[] mac;
    private StringBuilder stringBuilder;

    public Handshake() {
        this.sc = new Scanner(System.in);
        System.out.println("WOL (aka. Wake On Lan) handshake!");
        System.out.println("If you do not already have an account," +
        " please register at https://wol.sht.gr/backend/register");

        System.out.println("Username: ");
        // checking if the username complies to legal characters
        this.usernameRegex = "^((?=.*[a-z])|(?=.*[A-Z]))[a-zA-Z0-9]{5,16}$";
        this.usernamePattern = Pattern.compile(this.usernameRegex);
        this.usernameFlag = true;
        do {
            //String username = sc.nextLine();
            this.username = "geocfu"; // testing
            this.testInput = usernamePattern.matcher(this.username);
            if (testInput.matches() == true) {
                this.usernameFlag = false;
            }
            else {
                System.out.println("Username must be 5 to 16 characters long," +
                " and must not contain any special characters, ex.flaminglemin .");
                System.out.print("Username: ");
            }
        } while (this.usernameFlag);

        System.out.println("Password: ");
        // checking if the password complies to legal characters
        this.passwordRegex = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[^\\w\\s]).{8,32}$";
        this.passwordPattern = Pattern.compile(this.passwordRegex);
        this.passwordFlag = true;
        do {
            //String password = sc.nextLine();
            this.password = "maEonh5qS@";//to be filled
            this.testInput = passwordPattern.matcher(this.password);
            if (testInput.matches() == true) {
                this.passwordFlag = false;
            }
            else {
                System.out.println("Password must be 8 to 32 characters long," +
                " it must contain at least one lower case and one upper case character," +
                " one number and one special character, ex. Password123@ .");
                System.out.print("Password: ");
            }
        } while (this.passwordFlag);

        System.out.println("Controller device name: ");// delete ln after final
        // checking if the controllerName complies to legal characters
        this.controllerNameRegex = "^((?=.*[a-z])|(?=.*[A-Z]))[a-zA-Z0-9 ]{6,32}$";
        this.controllerNamePattern = Pattern.compile(this.controllerNameRegex);
        this.controllerNameFlag = true;
        do {
            //String controllerName = sc.nextLine();
            this.controllerName = "icarus";
            this.testInput = controllerNamePattern.matcher(this.controllerName);
            if (testInput.matches() == true) {
                this.controllerNameFlag = false;
            }
            else {
                System.out.println("Please input a valid name");
            }
        } while (this.controllerNameFlag);

        try {
            this.address = InetAddress.getLocalHost();
            this.networkInterface = NetworkInterface.getByInetAddress(this.address);
            this.mac = networkInterface.getHardwareAddress();

            this.stringBuilder = new StringBuilder();
            for (int i = 0; i < this.mac.length; i++) {
                stringBuilder.append(String.format("%02X%s", this.mac[i], (i < this.mac.length - 1) ? "":""));
            }
            this.macAddress = stringBuilder.toString();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public String getUsername() {
        return this.username;
    }

    public String getControllerName() {
        return this.controllerName;
    }

    public String getMacAddress() {
        return this.macAddress;
    }

    public String getPassword() {
        return this.password;
    }
}
