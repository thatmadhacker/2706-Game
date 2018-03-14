package com.thatmadhacker.utils.crypto;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

import sun.misc.BASE64Encoder;

/**
 * The Class SHA256.
 */
public class SHA256 {
	
	/**
	 * Hash.
	 *
	 * @param string the string
	 * @return the string
	 */
	public static String hash(String string){
		try{
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		String hash = new BASE64Encoder().encode(digest.digest(string.getBytes(StandardCharsets.UTF_8)));
		return hash;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
}
