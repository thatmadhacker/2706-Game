package com.thatmadhacker.utils.crypto;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Scanner;

import javax.crypto.Cipher;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * The Class RSA.
 */
public class RSA {

	/**
	 * Gen keys.
	 *
	 * @return the key pair
	 * @throws Exception the exception
	 */
	public static KeyPair genKeys() throws Exception{
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
		keyPairGenerator.initialize(2048);
		KeyPair keyPair = keyPairGenerator.generateKeyPair();
		return keyPair;
	}
	
	/**
	 * Encrypt.
	 *
	 * @param data the data
	 * @param key the key
	 * @return the string
	 * @throws Exception the exception
	 */
	public static String encrypt(String data, PublicKey key) throws Exception{
		byte[] dataToEncrypt = data.getBytes();
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] encryptedData = cipher.doFinal(dataToEncrypt);
		return new BASE64Encoder().encode(encryptedData);
	}
	
	public static String sign(String data, PrivateKey key) throws Exception{
		byte[] dataToEncrypt = data.getBytes();
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] encryptedData = cipher.doFinal(dataToEncrypt);
		return new BASE64Encoder().encode(encryptedData);
	}
	
	/**
	 * Decrypt.
	 *
	 * @param encrypted the encrypted
	 * @param key the key
	 * @return the string
	 * @throws Exception the exception
	 */
	public static String decrypt(String encrypted, PrivateKey key) throws Exception{
		byte[] dataToDecrypt = new BASE64Decoder().decodeBuffer(encrypted);
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.DECRYPT_MODE, key);
		byte[] decryptedData = cipher.doFinal(dataToDecrypt);
		return new String(decryptedData);
	}
	
	/**
	 * Send public key.
	 *
	 * @param key the key
	 * @param s the s
	 * @throws Exception the exception
	 */
	public static void sendPublicKey(PublicKey key, Socket s) throws Exception{
		OutputStream out = s.getOutputStream();
		PrintWriter send = new PrintWriter(s.getOutputStream(),true);
		send.println(key.getEncoded().length);
		out.write(key.getEncoded());
		
	}
	
	/**
	 * Recieve public key.
	 *
	 * @param s the s
	 * @return the public key
	 * @throws Exception the exception
	 */
	public static PublicKey recievePublicKey(Socket s) throws Exception{
		InputStream in = s.getInputStream();
		Scanner scan = new Scanner(s.getInputStream());
		byte[] data = new byte[Integer.valueOf(scan.nextLine())];
		in.read(data);
		PublicKey publicKey = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(data));
		scan.close();
		return publicKey;
	}
	public static String getSigned(String message, PublicKey key) throws Exception{
		byte[] dataToDecrypt = new BASE64Decoder().decodeBuffer(message);
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.DECRYPT_MODE, key);
		byte[] decryptedData = cipher.doFinal(dataToDecrypt);
		return new String(decryptedData);
	}
	public static boolean confirmCertificate(String signature,PublicKey signerKey, PublicKey key){
		try {
			if(getSigned(signature,signerKey).equals(new BASE64Encoder().encode(key.getEncoded()))){
				return true;
			}else{
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
}
