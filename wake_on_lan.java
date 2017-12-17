public class wake_on_lan {
    public static void main (String[] args) {
        PublicIPAddress publicIP = new PublicIPAddress();
        publicIP.getPublicIPAddress();
        System.out.println(publicIP.toString());//testing purpuses
        new local_ip();
    }
}
