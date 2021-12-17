package Authentication;

import java.io.*;
import java.net.*;
import java.security.*;

public class ProtectedClient
{
	public void sendAuthentication(String user, String password, OutputStream outStream) throws IOException, NoSuchAlgorithmException 
	{
		DataOutputStream out = new DataOutputStream(outStream);

		long timestamp1 = System.nanoTime();
		double randomNum1 = Math.random();
		byte[] digestByte = Protection.makeDigest(user, password, timestamp1, randomNum1);

		long timestamp2 = System.nanoTime();
		double randomNum2 = Math.random();
		byte[] digestByte2 = Protection.makeDigest(digestByte, timestamp2, randomNum2);

		out.writeUTF(user);
		out.writeUTF(password);
		out.writeLong(timestamp1);
		out.writeLong(timestamp2);
		out.writeDouble(randomNum1);
		out.writeDouble(randomNum2);
		out.writeUTF(MessageDigestImpl.generateMessageDigest(new String(digestByte2)));

		out.flush();
		out.close();
	}

	public static void main(String[] args) throws Exception 
	{
		String host = "127.0.0.1";
		int port = 7999;
		String user = "George";
		String password = "abc123";
		Socket s = new Socket(host, port);

		ProtectedClient client = new ProtectedClient();
		client.sendAuthentication(user, password, s.getOutputStream());

		s.close();
	}
}