import java.io.*;
import java.util.*;
import java.util.regex.*;

public class APIEndpoint {

    private String registrationAddress, connectionAddress, urlRegex;
    private boolean inputFlag, urlFlag;
    private Pattern urlPattern;
    private Matcher input;
    private Scanner sc;

    private boolean checkUrl(String URL) {
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
        this.inputFlag = true;
        do {
            System.out.println("Please enter your API endpoint URL for the registration.");
            this.registrationAddress = this.sc.nextLine();
            //this.registrationAddress = "https://wols.geocfu.me/api/registration.php";
            if (checkUrl(this.registrationAddress)) {
                this.inputFlag = false;
                System.out.println("Registration URL (" +
                                    this.registrationAddress + ") set.");
            }
            else {
                System.out.println("Please input a valid URL.");
            }
        } while (this.inputFlag);

        this.inputFlag = true;
        do {
            System.out.println("Please enter your API endpoint URL for the connection.");
            this.connectionAddress = this.sc.nextLine();
            //this.connectionAddress = "https://wols.geocfu.me/api/connection.php";
            if (checkUrl(this.connectionAddress)) {
                this.inputFlag = false;
                System.out.println("Connection URL (" +
                                    this.registrationAddress + ") set.");
            }
            else {
                System.out.println("Please input a valid URL.");
            }
        } while (this.inputFlag);
    }
    public String getRegistrationAddress() {
        return this.registrationAddress;
    }

    public String getConnectionAddress() {
        return this.connectionAddress;
    }
}
