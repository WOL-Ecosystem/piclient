import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * This class is responsible for scanning the network using command line.
 * We don't need a Object of this class we only need to call the method scan.
 */
class LocalNetworkScanner {

    /**
     * This method runs nmap command to scan the network and prints the results.
     */
    void scan() {
        try {
            /*
             * Execution of the command has started; command runs in the background.
             */
            Process proc = Runtime.getRuntime().exec("nmap -sn 192.168.1.0/24");
            String Output;

            /*
             * Read console output
             */
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            while ((Output = stdInput.readLine()) != null)
                System.out.println(Output);

            /*
             * Read any errors from the attempted command
             */
            BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
            while ((Output = stdError.readLine()) != null)
                System.out.println(Output);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
