package PublicKeySystem;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

public class Client {

    public static void encryptSendMessage(RSAPublicKey serverPublicKey, ObjectOutputStream oos) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        String senMessage = "From Client: Hi server, do you receive my message?";
        cipher.init(Cipher.ENCRYPT_MODE, serverPublicKey);

        oos.writeObject(cipher.doFinal(senMessage.getBytes()));
    }

    public static String decryptedReceivedMessage(RSAPrivateKey clientPrivateKey, ObjectInputStream ois) throws Exception {
        Cipher decryptCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        decryptCipher.init(Cipher.DECRYPT_MODE, clientPrivateKey);
        String receiveMessage = new String(decryptCipher.doFinal((byte[]) ois.readObject()));
        return receiveMessage;
    }

    public static Socket client() throws Exception{
        String host = "127.0.0.1";
        int port = 7999;
        Socket s = new Socket(host, port);
        return s;
    }

    public static void main(String[] args) throws Exception {
        //
        Socket s = client();

        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(1024, new SecureRandom());
        KeyPair keyPair = keyPairGenerator.genKeyPair();
        RSAPublicKey clientPublicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey clientPrivateKey = (RSAPrivateKey) keyPair.getPrivate();

        ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
        ObjectInputStream ois = new ObjectInputStream(s.getInputStream());

        // Exchange key
        oos.writeObject(clientPublicKey);
        RSAPublicKey serverPublicKey = (RSAPublicKey) ois.readObject();

        // Send Message
        encryptSendMessage(serverPublicKey, oos);

        //Decrypt message
        String receiveMessage = decryptedReceivedMessage(clientPrivateKey, ois);
        System.out.println("[Client Received] = "+receiveMessage);
    }


}
