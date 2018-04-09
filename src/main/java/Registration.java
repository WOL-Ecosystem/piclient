import java.io.*;
import java.util.*;
import java.net.*;
import java.util.regex.*;

public class Registration {

    private String userInput, emailRegex, email, passwordRegex, password, repeatPassword, authPassword;
    private Pattern emailPattern, passwordPattern;
    private Matcher testInput;
    private boolean emailFlag, passwordFlag, repeatPasswordFlag, passwordsMatchFlag;
    private Scanner sc;

    public Registration() {
        this.sc = new Scanner(System.in);
        System.out.println("WOLC (aka. Wake On Lan Client) registration!");
        System.out.println("If you do not already have an account," +
        " please register at (TODO)");

        // checking if the username complies to legal characters
        this.emailRegex = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        this.emailPattern = Pattern.compile(this.emailRegex);
        this.emailFlag = true;
        do {
            System.out.print("Email: ");
            //this.userInput = sc.nextLine();
            this.userInput = "geocfu@gmail.com"; // testing
            this.testInput = emailPattern.matcher(this.userInput);
            if (testInput.matches() == true) {
                this.emailFlag = false;
                this.email = this.userInput;
            }
            else {
                System.out.println("Invalid email.");
            }
        } while (this.emailFlag);


        // checking if the password complies to legal characters
        this.passwordRegex = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[^\\w\\s]).{9,32}$";
        this.passwordPattern = Pattern.compile(this.passwordRegex);
        this.passwordsMatchFlag = true;
        this.passwordFlag = true;
        this.repeatPasswordFlag = true;
        do {
            do {
                System.out.print("Password: ");
                //this.userInput = sc.nextLine();
                this.userInput = "TestTest1@";
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
                //this.userInput = sc.nextLine();
                this.userInput = "TestTest1@";//to be filled
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


    }

    public String getEmail() {
        return this.email;
    }

    public String getAuthPassword() {
        return this.authPassword;
    }

    public static void main(String [] args) {
        new Registration();
    }
}
