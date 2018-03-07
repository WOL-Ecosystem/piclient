import java.io.*;
import java.util.*;
import java.util.regex.*;

public class APIEndpoint {

    private String handshakeAddress, pingAddress, urlRegex;
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
            System.out.println("Do you want to use the default handshake URL?(y/n)");
            this.inputFlag = true;
            do {
                //this.option = sc.next().charAt(0);
                this.option = 'y';
                if (this.option == 'y' || this.option == 'Y') {
                    this.inputFlag = false;
                    this.handshakeAddress = "https://wol.sht.gr/backend/handshake";
                    System.out.println("Default handshake URL (" +
                                                this.handshakeAddress + ") set.");
                }
                else if (this.option == 'n' || this.option == 'N') {
                    this.inputFlag = false;
                    this.testUrlFlag = true;
                    this.sc.nextLine();
                    do {
                        System.out.print("Type your custom handshake URL: ");
                        this.handshakeAddress = this.sc.nextLine();
                        if (testUrl(this.handshakeAddress)) {
                            this.testUrlFlag = false;
                            System.out.println("Custom handshake URL (" +
                                                this.handshakeAddress + ") set.");
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

            System.out.println("Do you want to use the default ping URL?(y/n)");
            this.inputFlag = true;
            do {
                //this.option = this.sc.next().charAt(0);
                this.option = 'y';
                if (this.option == 'y' || this.option == 'Y') {
                    this.inputFlag = false;
                    this.pingAddress = "https://wol.sht.gr/backend/ping";
                    System.out.println("Default ping URL (" +
                                                        this.pingAddress + ") set.");
                }
                else if (this.option == 'n' || this.option == 'N') {
                    this.inputFlag = false;
                    this.testUrlFlag = true;
                    this.sc.nextLine();
                    do {
                        System.out.print("Type your custom ping URL: ");
                        this.pingAddress = this.sc.nextLine();
                        if(testUrl(this.pingAddress)) {
                            this.testUrlFlag = false;
                            System.out.println("Custom ping URL (" +
                                                        this.pingAddress + ") set.");
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
    public String getHandshakeAddress() {
        return this.handshakeAddress;
    }

    public String getPingAddress() {
        return this.pingAddress;
    }
}
