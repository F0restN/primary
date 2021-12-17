package Authentication;

import java.io.*;
import java.net.*;
import java.security.*;

public class ProtectedServer
{
	public boolean authenticate(InputStream inStream) throws IOException, NoSuchAlgorithmException 
	{
		DataInputStream in = new DataInputStream(inStream);

		String user = in.readUTF();
		String password = in.readUTF();
		long t1 = in.readLong();
		long t2 = in.readLong();
		double r1 = in.readDouble();
		double r2 = in.readDouble();
		String digest = in.readUTF();

		byte[] digestByte = Protection.makeDigest(user, password, t1, r1);
		byte[] digestByte2 = Protection.makeDigest(digestByte, t2, r2);
		String verify = MessageDigestImpl.generateMessageDigest(new String(digestByte2));

		if (password.equals(lookupPassword(user))){
			if (verify.equals(digest)){
				return true;
			}
		}

		return false;
	}

	protected String lookupPassword(String user) { return "abc123"; }

	public static void main(String[] args) throws Exception 
	{
		int port = 7999;
		ServerSocket s = new ServerSocket(port);
		Socket client = s.accept();

		ProtectedServer server = new ProtectedServer();

		if (server.authenticate(client.getInputStream()))
		  System.out.println("Client logged in.");
		else
		  System.out.println("Client failed to log in.");

		s.close();
	}
}