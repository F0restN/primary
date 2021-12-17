package Signature;

import java.io.*;
import java.math.BigDecimal;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.math.BigInteger;

public class ElGamalAlice {
    private static BigInteger computeY(BigInteger p, BigInteger g, BigInteger d) {
        return g.modPow(d, p);
    }

    private static BigInteger computeK(BigInteger p) {
        // K is less than, and relatively prime to p − 1
        int range = 100 - 1 + 1;

        while (true) {
            int k = (int) (Math.random() * range) + 1;

            int n1 = k;
            int n2 = p.intValue() - 1;
            while (n2 != 0) {
                if (n1 > n2) {
                    n1 = n1 - n2;
                } else {
                    n2 = n2 - n1;
                }
            }

            if (n1 == 1) {
                return BigInteger.valueOf(k);
            }
        }
    }

    private static BigInteger computeA(BigInteger p, BigInteger g, BigInteger k) {
        //a = g^k mod p
        return g.modPow(k, p);
    }

    private static BigInteger computeB(String message, BigInteger d, BigInteger a, BigInteger k, BigInteger p) {
        /*
        * ElGamal Extended Euclidean algo
        * 1. m = ( da + kb ) mod( p - 1 )
        * 2. Convert to b  = k^-1 * (m-da) mod (p-1);
        * 3. Note: (a * b) % c = (a % c * b % c) % c
        *
        * */

        BigInteger m = new BigInteger(message.getBytes());
        BigInteger k1 = k.modInverse(p.subtract(BigInteger.valueOf(1)));
        BigInteger b = k1.multiply(m.subtract(d.multiply(a)) ).mod(p.subtract(BigInteger.valueOf(1)));
        return b;
    }

    public static void main(String[] args) throws Exception {
        String message = "The quick brown fox jumps over the lazy dog.";

        String host = "127.0.0.1";
        int port = 7999;
        Socket s = new Socket(host, port);
        ObjectOutputStream os = new ObjectOutputStream(s.getOutputStream());

        // You should consult BigInteger class in Java API documentation to find out what it is.
        BigInteger y, g, p; // public key
        BigInteger d; // private key

        int mStrength = 1024; // key bit length
        SecureRandom mSecureRandom = new SecureRandom(); // a cryptographically strong pseudo-random number

        // Create a BigInterger with mStrength bit length that is highly likely to be prime.
        // (The '16' determines the probability that p is prime. Refer to BigInteger documentation.)
        p = new BigInteger(mStrength, 16, mSecureRandom);

        // Create a randomly generated BigInteger of length mStrength-1
        g = new BigInteger(mStrength - 1, mSecureRandom);
        d = new BigInteger(mStrength - 1, mSecureRandom);

        y = computeY(p, g, d);

        // At this point, you have both the public key and the private key. Now compute the signature.

        BigInteger k = computeK(p);
        BigInteger a = computeA(p, g, k);
        BigInteger b = computeB(message, d, a, k, p);

        // send public key
        os.writeObject(y);
        os.writeObject(g);
        os.writeObject(p);

        // send message
        os.writeObject(message);

        // send signature
        os.writeObject(a);
        os.writeObject(b);

        os.flush();
        System.out.println("Alice finishes sending message");
        os.close();
        s.close();
    }
}