import java.util.*;
import java.util.regex.*;

/**
 * This class is responsible for taking the correct user input data that our service require.
 */
class Registration {

    private String username;
    private String authPassword;
    private String macAddress;
    private String computerName;

    /**
      * In this method we perform the registration.
     */
    void run(){

        System.out.println("\nWOL-Client (aka. Wake On Lan Client) registration!");
        System.out.println("Registration phase.");

        userNameRegistration();
        passwordRegistration();

        System.out.println("\nNetwork phase.\n");
        System.out.println("1. Automatic network scan (your target device must be turned on\n" +
                "and be connected to the WiFi).");
        System.out.println("2. Manually enter the mac address/es.");
        System.out.print("Type your choice : ");

        outerloop:
        while (true) {
            switch (new Scanner(System.in).nextLine()) {
                case "1":
                    LocalNetworkScanner.scan();
                    macAddressRegistration();
                    computerNameRegistration();
                    break outerloop;
                case "2":
                    //System.out.println("Automated until release phase");
                    //macAddress = "01:23:EC:67:89:AB";
                    //computerName = "Hercules";
                    macAddressRegistration();
                    computerNameRegistration();
                    break outerloop;
                default:
                    System.out.println("Please select a valid option.");
            }
        }
    }


    /**
     * Take a valid computer name.
     */
    private void computerNameRegistration() {
        String computerNameRegex = "^([a-zA-Z0-9]){5,20}$";
        Pattern computerNamePattern = Pattern.compile(computerNameRegex);
        while (true) {
            System.out.print("Type the name of the target pc: ");
            String userInput = new Scanner(System.in).nextLine();
            if (computerNamePattern.matcher(userInput).matches()) {
                computerName = userInput;
                break;
            } else {
                System.out.println("Name must be like this, Hercules or hercules");
            }
        }
    }

    /**
     * Take a valid macAddress.
     */
    private void macAddressRegistration() {
        while (true) {
            System.out.print("Type the mac address of the target pc : ");
            String userInput= new Scanner(System.in).nextLine();
            if (Pattern.compile("^([a-fA-F0-9]{2}:){5}[a-fA-F0-9]{2}$").matcher(userInput).matches()) {
                macAddress = userInput;
                break;
            } else {
                System.out.println("Invalid Mac Address format. Please type in a Mac Address.");
            }
        }
    }

    /**
     * Take a valid password.
     */
    private void passwordRegistration(){
        // checking if the password complies to legal characters
        while (true) {
            String password,repeatPassword;
            while (true) {
                System.out.print("Password: ");
                String userInput = new Scanner(System.in).nextLine();
                //this.userInput = "TestTest1@";
                if (Pattern.compile("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[^\\w\\s]).{9,32}$").matcher(userInput).matches()) {
                    password = userInput;
                    break;
                } else {
                    System.out.println("Password must be 9 to 32 characters long," +
                            " it must contain at least one lower case and one upper case character," +
                            " one number and one special character, ex. Password123@ .");
                }
            }
            while (true) {
                System.out.print("Repeat password: ");
                String userInput = new Scanner(System.in).nextLine();
                //this.userInput = "TestTest1@";//to be filled
                if (Pattern.compile("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[^\\w\\s]).{9,32}$").matcher(userInput).matches()) {
                    repeatPassword = userInput;
                    break;
                } else {
                    System.out.println("Password must be 9 to 32 characters long," +
                            " it must contain at least one lower case and one upper case character," +
                            " one number and one special character, ex. Password123@ .");
                }
            }

            if (password.equals(repeatPassword)) {
                authPassword = repeatPassword;
                System.out.println("Passwords match.");
                break;
            } else {
                System.out.println("Passwords dont match.");
            }
        }
    }

    /**
     * Take a valid user name.
     */
    private void userNameRegistration() {
        // checking if the username complies to legal characters
        while (true) {
            System.out.println("Username: ");
            String userInput = new Scanner(System.in).nextLine();
            //userInput = "geocfu"; // testing
            if (Pattern.compile("^([a-zA-Z0-9]){5,20}$").matcher(userInput).matches()) {
                username = userInput;
                break;
            } else {
                System.out.println("Invalid username.\nUsername must me at least 5 to maximum 20 characters" +
                        " and must not contain any special characters.");
            }
        }
    }

    /**
     * Getter for user name.
     */
    String getUsername() {
        return username;
    }

    /**
     * Getter for auth password.
     */
    String getAuthPassword() {
        return authPassword;
    }

    /**
     * Getter for macAddress and computerName separated by "-".
     */
    String getMacAndName() {
        return macAddress + "-" + computerName;
    }
}
