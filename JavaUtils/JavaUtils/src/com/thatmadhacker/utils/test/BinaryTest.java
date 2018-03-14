package com.thatmadhacker.utils.test;

import com.thatmadhacker.utils.binary.Binary;

// TODO: Auto-generated Javadoc
/**
 * The Class BinaryTest.
 */
public class BinaryTest {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args){
		String message = "test123";
		System.out.println("Message is: "+message);
		String binary = Binary.toBinary(message);
		System.out.println("Binary is: "+binary);
		System.out.println("Decoded message is: "+Binary.toString(binary));
	}
}
