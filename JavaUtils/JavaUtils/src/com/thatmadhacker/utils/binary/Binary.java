package com.thatmadhacker.utils.binary;

// TODO: Auto-generated Javadoc
/**
 * The Class Binary.
 */
public class Binary {

	/**
	 * To binary.
	 *
	 * @param message the message
	 * @return the string
	 */
	public static String toBinary(String message){
		  byte[] bytes = message.getBytes();
		  StringBuilder binary = new StringBuilder();
		  for (byte b : bytes)
		  {
		     int val = b;
		     for (int i = 0; i < 8; i++)
		     {
		        binary.append((val & 128) == 0 ? 0 : 1);
		        val <<= 1;
		     }
		     binary.append(' ');
		  }
		  return binary.toString();
	}
	
	/**
	 * To string.
	 *
	 * @param binary the binary
	 * @return the string
	 */
	public static String toString(String binary){
		String[] data = binary.split(" ");
		String message = "";
		for(String s : data){
		int charCode = Integer.parseInt(s, 2);
		message = message + new Character((char)charCode).toString();
		}
		return message;
	}
}
