import java.io.*;
import java.util.*;
import java.util.regex.*;

public class APIEndpoint {

    private String registrationAddress, connectionAddress, urlRegex;
    private char option;
    private boolean inputFlag, urlFlag, testUrlFlag;
    private Pattern urlPattern;
    private Matcher input;
    private Scanner sc;

    private boolean testUrl(String URL) {
        this.urlRegex = "^(http:\\/\\/www\\.|https:\\/\\/www\\." +
        "|http:\\/\\/|https:\\/\\/)?[a-z0-9]+([\\-\\.]"+
        "{1}[a-z0-9]+)*\\.[a-z]{2,5}(:[0-9]{1,5})?(\\/.*)?$";
        this.urlPattern = Pattern.compile(this.urlRegex);
        this.urlFlag = false;
        this.input = urlPattern.matcher(URL);
        if (input.matches() == true) {
            this.urlFlag = true;
        }
        return this.urlFlag;
    }

    public APIEndpoint() {
        this.sc = new Scanner(System.in);
        try {
            System.out.println("Do you want to use the default registration URL?(y/n)");
            this.inputFlag = true;
            do {
                //this.option = sc.next().charAt(0);
                this.option = 'y';
                if (this.option == 'y' || this.option == 'Y') {
                    this.inputFlag = false;
                    this.registrationAddress = "https://www.geocfu.me/wols/registration.php";
                    System.out.println("Default registration URL (" +
                                                this.registrationAddress + ") set.");
                }
                else if (this.option == 'n' || this.option == 'N') {
                    this.inputFlag = false;
                    this.testUrlFlag = true;
                    this.sc.nextLine();
                    do {
                        System.out.print("Type your custom registration URL: ");
                        this.registrationAddress = this.sc.nextLine();
                        if (testUrl(this.registrationAddress)) {
                            this.testUrlFlag = false;
                            System.out.println("Custom registration URL (" +
                                                this.registrationAddress + ") set.");
                        }
                        else {
                            System.out.println("Please input a valid URL.");
                        }
                    } while(this.testUrlFlag);
                }
                else {
                    System.out.println("Please select a valid option");
                }
            } while (this.inputFlag);

            System.out.println("Do you want to use the default connection URL?(y/n)");
            this.inputFlag = true;
            do {
                //this.option = this.sc.next().charAt(0);
                this.option = 'y';
                if (this.option == 'y' || this.option == 'Y') {
                    this.inputFlag = false;
                    this.connectionAddress = "https://www.geocfu.me/wols/connection.php";
                    System.out.println("Default connection URL (" +
                                                        this.connectionAddress + ") set.");
                }
                else if (this.option == 'n' || this.option == 'N') {
                    this.inputFlag = false;
                    this.testUrlFlag = true;
                    this.sc.nextLine();
                    do {
                        System.out.print("Type your custom connection URL: ");
                        this.connectionAddress = this.sc.nextLine();
                        if(testUrl(this.connectionAddress)) {
                            this.testUrlFlag = false;
                            System.out.println("Custom ping URL (" +
                                                        this.connectionAddress + ") set.");
                        }
                        else {
                            System.out.println("Please input a valid URL.");
                        }
                    } while(this.testUrlFlag);
                }
                else {
                    System.out.println("Please select a valid option");
                }
            } while (this.inputFlag);
        }
        finally {
            this.sc.close();
        }
    }
    public String getRegistrationAddress() {
        return this.registrationAddress;
    }

    public String getConnectionAddress() {
        return this.connectionAddress;
    }
}
