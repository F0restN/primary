package X509;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;

import static PublicKeySystem.Client.*;

public class clientCA {

    public static void main(String[] args) throws Exception{

        String host = "127.0.0.1";
        int port = 7999;
        Socket s = new Socket(host, port);

        ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
        ObjectInputStream ois = new ObjectInputStream(s.getInputStream());

        InputStream ins = new FileInputStream("/Users/drakezhou/Desktop/2150 ISP/Final Project/project_skeleton/drake.cer");
        CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
        X509Certificate cer = (X509Certificate) certificateFactory.generateCertificate(ins);

        // Print content
        System.out.println("==== Certification ====");
        System.out.println(cer.toString());

        //Expire verification
        boolean valid = false;
        Date now = new Date();
        try {
            cer.checkValidity(now);
            System.out.println("Time is valid");

            //PublicKey verification
            try {
                cer.verify(cer.getPublicKey());
                System.out.println("Certificate is valid");
                valid = true;
            } catch (Exception e) {
                System.out.println("Not valid");
            }
        } catch (Exception e){
            System.out.println("Not valid");
        }
        System.out.println();

        if (valid) {
            System.out.println("==== Start Communication====");
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(1024, new SecureRandom());
            KeyPair keyPair = keyPairGenerator.genKeyPair();
            RSAPublicKey clientPublicKey = (RSAPublicKey) keyPair.getPublic();
            RSAPrivateKey clientPrivateKey = (RSAPrivateKey) keyPair.getPrivate();

            // Send serverPublicKey
            RSAPublicKey serverPublicKey = (RSAPublicKey) cer.getPublicKey();
            oos.writeObject(serverPublicKey);

            // Send Message
            System.out.println("[Client activation: say hello to Server]");
            oos.writeObject(clientPublicKey);
            encryptSendMessage(serverPublicKey, oos);

            //Decrypt message
            String receiveMessage = decryptedReceivedMessage(clientPrivateKey, ois);
            System.out.println("[Client Received] = "+receiveMessage);

            s.close();
        }

    }
}
