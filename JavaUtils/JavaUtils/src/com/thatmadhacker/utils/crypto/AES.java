package com.thatmadhacker.utils.crypto;

import java.security.SecureRandom;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

// TODO: Auto-generated Javadoc
/**
 * The Class AES.
 */
public class AES {

	/**
	 * Gen key.
	 *
	 * @param password the password
	 * @return the secret key
	 * @throws Exception the exception
	 */
	public static SecretKey genKey(String password) throws Exception{
		byte[] salt = "thisisagoodsalt123".getBytes();
		SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
		KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
		SecretKey tmp = factory.generateSecret(spec);
		SecretKey secret = new SecretKeySpec(tmp.getEncoded(), "AES");
		return secret;
	}
	
	/**
	 * Gen key.
	 *
	 * @return the secret key
	 * @throws Exception the exception
	 */
	public static SecretKey genKey() throws Exception{
		KeyGenerator keyGen = KeyGenerator.getInstance("AES");
		SecureRandom random = new SecureRandom();
		keyGen.init(random); 
		SecretKey secretKey = keyGen.generateKey();
		return secretKey;
	}
	
	/**
	 * Encrypt.
	 *
	 * @param data the data
	 * @param key the key
	 * @return the string
	 * @throws Exception the exception
	 */
	public static String encrypt(String data, SecretKey key) throws Exception{
			Cipher c = Cipher.getInstance("AES");
			c.init(Cipher.ENCRYPT_MODE, key);
			byte[] encVal = c.doFinal(data.getBytes());
			String encryptedValue = new BASE64Encoder().encode(encVal);
			return encryptedValue;
	}
	
	/**
	 * Decrypt.
	 *
	 * @param encryptedData the encrypted data
	 * @param key the key
	 * @return the string
	 * @throws Exception the exception
	 */
	public static String decrypt(String encryptedData, SecretKey key) throws Exception{
			Cipher c = Cipher.getInstance("AES");
			c.init(Cipher.DECRYPT_MODE, key);
			byte[] decodedValue = new BASE64Decoder().decodeBuffer(encryptedData);
			byte[] decValue = c.doFinal(decodedValue);
			String decryptedValue = new String(decValue);
			return decryptedValue;
	}
}
