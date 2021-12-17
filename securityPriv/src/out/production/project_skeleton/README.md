# How to run this project

## 1. Authentication
### Manipulation
Directly run MessageDigestImpl.java file
### Output
Hash Code : 9e8c5571ed239017af494ccd8918125513234142
Hash Code : 3ac75a6d60271efab41eb1747d172619
### Discussion
Two hash code, one through SHA and another one through MD5

## 2. Encryption
### Manipulation
Run CipherServer.java first, and when you see Server side hang-out then run CipherClient.java
then you can get the output.
### Output
Server side:
Server: successfully connected
Server: Message = The quick brown fox jumps over the lazy dog.

Client side:
Client start connecting
Client: send message = The quick brown fox jumps over the lazy dog.

### Discussion
message has been successfully ciphered and sent to server. Server receives the message and decrypt it.

## 3. PublicKeySystem
### Manipulation
Run Server.java first, and when you see Server side hang-out then run Client.java
then you can get the output.

### Output
Server side:
Server: successfully connected
[Server Side Received] = From Client: Hi server, do you receive my message?

Client side:
[Client Received] = From Server: Yes client, I received your message. Welcome Back

### Discussion
what happened here is that:
1. After generate pair key and exchange public key between client and server
2. client use server public key to encrypt the message and send ciphertext to server
3. server receives the ciphertext and use his own private key to decrypt it and then use client public key to encrypt a response and send it back to client.
4. Client receives the ciphertext and use his own private key to decrypt it.

## 4. Signature
### Manipulation
Run ELGamalBob.java first and then run ElGamalAlice.java
### Output
ElGamelBob side:
Connected to Alice
The quick brown fox jumps over the lazy dog.
Signature verified.

ElGamalAlice side:
Alice finishes sending message
### Discussion
Use ElGamal algorithm according to Extended_Euclidean through canvas to implement encrypted communicating between two side.

## 5. X.509
### Manipulation
Run serverCA.java and when you see server side hang-out then run clientCA.java side.
### Output
Server side:
[Receive request from Client: CA verification]
[Server Side Received] = From Client: Hi server, do you receive my message?
[Server Side activate: response to client]

clientCA:
[CA information]
Time is valid
Certificate is valid

==== Start Communication====

[Client activation: say hello to Server]
[Client Received] = From Server: Yes client, I received your message. Welcome Back
### Discussion
First, we read CA and then verify it. When it is valid, the communication is similar to PublicKeySystem except client use public key from CA as server public key and server use private key from CA as private key.

# Question

**What are the limitations of using self-signed certificates?**

Without a verification from trustworthy third-party, a self-signed certification could have risk. Everybody can create and sign it. The Self-Signed SSL Certificates are easy to replicate. Hackers can use this technique against companies, designing a website that looks just like their's in order to steal personal information or credit card information from users. This can put companies customers’ identities at risk. 

 **What are they useful for?**

It is still a CA. We still have benefits from CA which is prevent hijack attack (Only if CA is trustworhy). Besides, it is cheap. They are suitable for internal network websites and development/testing environments. Encryption and Decryption of the data is done with the same ciphers used by paid SSL certificates
