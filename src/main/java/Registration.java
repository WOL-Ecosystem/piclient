import java.io.*;
import java.util.*;
import java.net.*;
import java.util.regex.*;

public class Registration {

    private String userInput, usernameRegex, username, passwordRegex, password, repeatPassword, authPassword, macAddress, macAddressRegex, computerName, computerNameRegex, macAndName;
    private Pattern usernamePattern, passwordPattern, macAddressPattern, computerNamePattern;
    private Matcher testInput;
    private boolean usernameFlag, passwordFlag, repeatPasswordFlag, passwordsMatchFlag, inputFlag, macAddressFlag, computerNameFlag;
    private Scanner sc;
    private LocalNetworkScanner scan;

    private void inputComputerName() {
        this.computerNameRegex = "^([a-zA-Z0-9]){5,20}$";
        this.computerNamePattern = Pattern.compile(this.computerNameRegex);
        this.computerNameFlag = true;
        do {
            System.out.print("Type the name of the target pc: ");
            this.userInput = this.sc.nextLine();
            this.testInput = computerNamePattern.matcher(this.userInput);
            if (testInput.matches() == true) {
                this.computerNameFlag = false;
                this.computerName = this.userInput;
            }
            else {
                System.out.println("Name must be like this, Hercules or hercules");
            }
        } while(this.computerNameFlag);
    }

    private void inputMacAddress() {
        this.macAddressRegex = "^([a-fA-F0-9]{2}:){5}[a-fA-F0-9]{2}$";
        this.macAddressPattern = Pattern.compile(this.macAddressRegex);
        this.macAddressFlag = true;
        do {
            System.out.print("Type the mac adress of the target pc: ");
            this.userInput = this.sc.nextLine();
            this.testInput = macAddressPattern.matcher(this.userInput);
            if (testInput.matches() == true) {
                this.macAddressFlag = false;
                this.macAddress = this.userInput;
            }
            else {
                System.out.println("Mac address must be like this, ex. 01:23:EC:67:89:AB.");
            }
        } while(this.macAddressFlag);
    }

    public Registration() {
        this.sc = new Scanner(System.in);
        System.out.println("\nWOL-Client (aka. Wake On Lan Client) registration!");
        System.out.println("Registration phase.");

        // checking if the username complies to legal characters
        this.usernameRegex = "^([a-zA-Z0-9]){5,20}$";
        this.usernamePattern = Pattern.compile(this.usernameRegex);
        this.usernameFlag = true;
        do {
            System.out.println("Username: ");
            this.userInput = this.sc.nextLine();
            //this.userInput = "geocfu"; // testing
            this.testInput = usernamePattern.matcher(this.userInput);
            if (testInput.matches() == true) {
                this.usernameFlag = false;
                this.username = this.userInput;
            }
            else {
                System.out.println("Invalid username.\nUsername must me at least 5 to maximum 20 characters" +
                " and must not contain any sepcial characters.");
            }
        } while (this.usernameFlag);

        // checking if the password complies to legal characters
        this.passwordRegex = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[^\\w\\s]).{9,32}$";
        this.passwordPattern = Pattern.compile(this.passwordRegex);
        this.passwordsMatchFlag = true;
        this.passwordFlag = true;
        this.repeatPasswordFlag = true;
        do {
            do {
                System.out.print("Password: ");
                this.userInput = this.sc.nextLine();
                //this.userInput = "TestTest1@";
                this.testInput = passwordPattern.matcher(this.userInput);
                if (testInput.matches() == true) {
                    this.passwordFlag = false;
                    this.password = this.userInput;
                }
                else {
                    System.out.println("Password must be 9 to 32 characters long," +
                    " it must contain at least one lower case and one upper case character," +
                    " one number and one special character, ex. Password123@ .");
                }
            } while (this.passwordFlag);

            do {
                System.out.print("Repeat password: ");
                this.userInput = this.sc.nextLine();
                //this.userInput = "TestTest1@";//to be filled
                this.testInput = passwordPattern.matcher(this.userInput);
                if (testInput.matches() == true) {
                    this.repeatPasswordFlag = false;
                    this.repeatPassword = this.userInput;
                }
                else {
                    System.out.println("Password must be 9 to 32 characters long," +
                    " it must contain at least one lower case and one upper case character," +
                    " one number and one special character, ex. Password123@ .");
                }
            } while (this.repeatPasswordFlag);

            if (this.password.equals(this.repeatPassword)) {
                this.authPassword = this.repeatPassword;
                this.passwordsMatchFlag = false;
                System.out.println("Passwords match.");
            }
            else {
                this.passwordsMatchFlag = true;
                System.out.println("Passwords dont match.");
            }
        } while (this.passwordsMatchFlag);
        System.out.println("\nNetwork phase.\n");
        System.out.println("1. Automatic network scan (your target device must be turned on\nand be connected to the WiFi).");
        System.out.println("2. Manually enter the mac address/es.");
        System.out.print("Choose an option from above: ");

        this.inputFlag = true;
        do {
            this.userInput = this.sc.nextLine();
            //this.userInput = Integer.toString(2);

            if (this.userInput.equals("1")) {
                this.inputFlag = false;
                this.scan = new LocalNetworkScanner();
                inputMacAddress();
                inputComputerName();
            }
            else if (this.userInput.equals("2")) {
                this.inputFlag = false;

                //System.out.println("Automated untill realease phase");
                //this.macAddress = "01:23:EC:67:89:AB";
                //this.computerName = "Hercules";
                inputMacAddress();
                inputComputerName();
            }
            else {
                System.out.println("Please select a valid option");
            }
        } while (this.inputFlag);

        this.macAndName = this.macAddress + "-" + this.computerName;
    }

    public String getUsername() {
        return this.username;
    }

    public String getAuthPassword() {
        return this.authPassword;
    }

    public String getMacAndName() {
        return this.macAndName;
    }
}
