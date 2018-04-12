import java.io.*;
import java.util.*;
import java.net.*;

public class LocalNetworkScanner {

    private String correctExecutionOutput, errorExecutionOutput;

    public LocalNetworkScanner() {
        try {
            //Execution of the command has started; command runs in the background.
            Process proc = Runtime.getRuntime().exec("nmap -sn 192.168.1.0/24");

            //Read console output
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));

            while ((this.correctExecutionOutput = stdInput.readLine()) != null) {
                System.err.println(this.correctExecutionOutput);
            }

            BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));

            // read any errors from the attempted command
            while ((this.errorExecutionOutput = stdError.readLine()) != null) {
                System.err.println(this.errorExecutionOutput);
            }
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
}
