package Authentication;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MessageDigestImpl {

    public static String generateMessageDigest(MessageDigest md, String str) throws NoSuchAlgorithmException {
        md.update(str.getBytes());
        byte[] digest = md.digest();

        StringBuffer hexString = new StringBuffer();

        for (int i = 0;i<digest.length;i++) {
            hexString.append(Integer.toHexString(0xFF & digest[i]));
        }
        System.out.println("Hash Code : " + hexString.toString());
        return hexString.toString();
    }

    public static String generateMessageDigest(String str) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA");
        md.update(str.getBytes());
        byte[] digest = md.digest();

        StringBuffer hexString = new StringBuffer();

        for (int i = 0;i<digest.length;i++) {
            hexString.append(Integer.toHexString(0xFF & digest[i]));
        }
        System.out.println("Hash Code : " + hexString.toString());
        return hexString.toString();
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        MessageDigest md1 = MessageDigest.getInstance("SHA");
        generateMessageDigest(md1, "George");
        MessageDigest md2 = MessageDigest.getInstance("MD5");
        generateMessageDigest(md2, "drake");
    }
}
