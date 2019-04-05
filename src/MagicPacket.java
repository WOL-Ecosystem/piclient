import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * This class is responsible for waking up a device using command line, mac address and the wakeOnLan technology.
 * We don't need a Object of this class we only need to call the method wakeOnLan.
 */
abstract class MagicPacket {

    /**
     * This method runs a command with sudo to wake a device with a specific mac address and prints the results.
     *
     * @param macAddress This is the mac address of the device we want to wake.
     */
    static void wakeOnLan(String macAddress) {
        try {
            /*
             * Execution of the command has started; command runs in the background.
             */
            Process proc = Runtime.getRuntime().exec("sudo wakeonlan " + macAddress);

            /*
             * Read console output
             */
            String Output;
            while ((Output = new BufferedReader(new InputStreamReader(proc.getInputStream())).readLine()) != null)
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
