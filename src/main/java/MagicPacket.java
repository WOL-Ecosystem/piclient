import java.io.*;
import java.net.*;
import java.util.*;

public class MagicPacket {

    public MagicPacket (Map<String, String> user_data, String local_IP) {
        String localIP = local_IP;
        String macAddress = user_data.get("magicmac");
        try {
            byte[] macBytes = getMacBytes(macAddress);
            byte[] bytes = new byte[6 + 16 * macBytes.length];
            for (int i = 0; i < 6; i++) {
                bytes[i] = (byte)0xff;
            }
            for (int i = 6; i < bytes.length; i += macBytes.length) {
                System.arraycopy(macBytes, 0, bytes, i, macBytes.length);
            }

            InetAddress address = InetAddress.getByName(localIP);
            DatagramPacket packet = new DatagramPacket(bytes, bytes.length, address, 9);
            DatagramSocket socket = new DatagramSocket();
            socket.send(packet);
            socket.close();
            System.out.println("Wake-on-LAN packet sent.");// testing
        }
        catch (Exception ex) {
            System.out.println("Exception: Failed to send Wake-on-LAN packet:" + ex);
        }
    }

    private static byte[] getMacBytes(String macAddress) throws IllegalArgumentException {
        byte[] bytes = new byte[6];
        String[] hex = macAddress.split("(\\:|\\-)");
        if (hex.length != 6) {
            throw new IllegalArgumentException("IllegalArgumentException: Invalid MAC address.");
        }
        try {
            for (int i = 0; i < 6; i++) {
                bytes[i] = (byte) Integer.parseInt(hex[i], 16);
            }
        }
        catch (NumberFormatException ex) {
            throw new IllegalArgumentException("NumberFormatException: Invalid hex digit in MAC address."+ ex);
        }
        return bytes;
    }
}
