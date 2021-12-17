package PublicKeySystem;

import javax.crypto.Cipher;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

public class Server {

    public static Socket serverInitialize() throws IOException {
        int port = 7999;
        ServerSocket server = new ServerSocket(port);
        Socket s = server.accept();
        System.out.println("Server: successfully connected");
        return s;
    }

    public static void server(Socket s, RSAPublicKey serverPublicKey,  RSAPrivateKey serverPrivateKey) throws Exception{
        ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
        ObjectInputStream ois = new ObjectInputStream(s.getInputStream());

        // Exchange public key
        oos.writeObject(serverPublicKey);
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
            String message = "From Server: Yes client, I received your message. Welcome Back";
            oos.writeObject(encryptCipher.doFinal(message.getBytes()));
        }
    }

    public static void main(String[] args) throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(1024, new SecureRandom());
        KeyPair keyPair = keyPairGenerator.genKeyPair();
        RSAPublicKey serverPublicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey serverPrivateKey = (RSAPrivateKey)keyPair.getPrivate();

        server(serverInitialize(), serverPublicKey, serverPrivateKey);
    }
}
