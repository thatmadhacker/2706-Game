package com.thatmadhacker.utils.test;

import java.security.KeyPair;

import javax.crypto.SecretKey;

import com.thatmadhacker.utils.crypto.AES;
import com.thatmadhacker.utils.crypto.RSA;

// TODO: Auto-generated Javadoc
/**
 * The Class CryptoTest.
 */
public class CryptoTest {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws Exception the exception
	 */
	public static void main(String[] args) throws Exception{
		String unencrypted = "hello123";
		String password = "test1234";
		SecretKey key1 = AES.genKey(password);
		System.out.println("AES With Password");
		System.out.println("Password: "+password);
		System.out.println("Unencrypted: "+unencrypted);
		System.out.println("Encrypted: "+AES.encrypt(unencrypted, key1));
		System.out.println("Decrypted: "+AES.decrypt(AES.encrypt(unencrypted, key1), key1));
		System.out.println("AES Without Password");
		System.out.println("Unencrypted: "+unencrypted);
		SecretKey key = AES.genKey();
		System.out.println("Encrypted: "+AES.encrypt(unencrypted, key));
		System.out.println("Decrypted: "+AES.decrypt(AES.encrypt(unencrypted, key), key));
		System.out.println("RSA");
		KeyPair pair = RSA.genKeys();
		System.out.println("Unencrypted: "+unencrypted);
		System.out.println("Encrypted: "+RSA.encrypt(unencrypted, pair.getPublic()));
		System.out.println("Decrypted: "+RSA.decrypt(RSA.encrypt(unencrypted, pair.getPublic()),pair.getPrivate()));
	}
}
