package X509;

import javax.crypto.Cipher;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.KeyStore;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

public class serverCA {

    public static void main(String[] args) throws Exception {
        String userName="drake";
        char[] password="abccba".toCharArray();

        int port = 7999;
        ServerSocket server = new ServerSocket(port);
        Socket s = server.accept();
        System.out.println("[Receive request from Client: CA verification]");
        ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
        ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());

        // Get private and public keys for Server
        KeyStore ks = KeyStore.getInstance("jks");
        ks.load(new FileInputStream("/Users/drakezhou/Desktop/2150 ISP/Final Project/project_skeleton/keystore.jks"), password);
        RSAPrivateKey serverPrivateKey = (RSAPrivateKey) ks.getKey(userName, password);
        RSAPublicKey serverPublicKey = (RSAPublicKey) ois.readObject();

        // Get clientPublicKey
        RSAPublicKey clientPublicKey = (RSAPublicKey) ois.readObject();

        //Decrypt message
        Cipher decryptCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        decryptCipher.init(Cipher.DECRYPT_MODE, serverPrivateKey);

        String receiveMessage = new String(decryptCipher.doFinal((byte[]) ois.readObject()));
        System.out.println("[Server Side Received] = "+receiveMessage);

        // Encrypt Message
        Cipher encryptCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        encryptCipher.init(Cipher.ENCRYPT_MODE, clientPublicKey);
        if (receiveMessage.length() != 0){
            System.out.println("[Server Side activate: response to client]");
            String message = "From Server: Yes client, I received your message. Welcome Back";
            oos.writeObject(encryptCipher.doFinal(message.getBytes()));
        }
    }
}
