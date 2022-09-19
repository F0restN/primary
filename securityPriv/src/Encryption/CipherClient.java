package Encryption;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.security.*;
import javax.crypto.*;

public class CipherClient {

	public static String jdkBase64String(byte[] encryptedMessage) {
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(encryptedMessage);
	}

	public static void main(String[] args) throws Exception {
		String message = "The quick brown fox jumps over the lazy dog.";
		String host = "127.0.0.1";
		int port = 7999;
		Socket s = new Socket(host, port);
		System.out.println("Client start connecting");

		// YOU NEED TO DO THESE STEPS:
		// -Generate a DES key.
		KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
		keyGenerator.init(56, new SecureRandom());
		Key scrtkey = keyGenerator.generateKey();

		// -Store it in a file.
		String keyFile = "/Users/drakezhou/Desktop/2150 ISP/Final Project/project_skeleton/Encryption/keyFile.txt";
		ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(keyFile));
		os.writeObject(scrtkey);
		os.close();

		// -Use the key to encrypt the message above and send it over socket s to the server.
		ObjectOutputStream socketOut = new ObjectOutputStream(s.getOutputStream());
		socketOut.writeObject(scrtkey);

		Cipher encrypt = Cipher.getInstance("DES/ECB/PKCS5Padding");
		CipherOutputStream cipherOutputStream = new CipherOutputStream(s.getOutputStream(), encrypt);
		encrypt.init(Cipher.ENCRYPT_MODE, scrtkey);
		cipherOutputStream.write(message.getBytes());

		cipherOutputStream.close();
		socketOut.close();

		s.close();

		System.out.println("Client: send message = "+message);
		System.exit(0);
	}
}