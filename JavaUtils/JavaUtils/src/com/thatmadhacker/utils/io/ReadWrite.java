package com.thatmadhacker.utils.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

// TODO: Auto-generated Javadoc
/**
 * The Class ReadWrite.
 */
public class ReadWrite {

/**
 * Write.
 *
 * @param filename the filename
 * @param data the data
 */
public static void write(String filename, String data){
	try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename, true))) {
		String content = data;
		bw.append(content + System.lineSeparator());
	} catch (IOException e) {
		e.printStackTrace();
	}
    }
	
	/**
	 * Read.
	 *
	 * @param filename the filename
	 * @return the string
	 */
	public static String read(String filename){
		BufferedReader br;
		try{
		    br = new BufferedReader(new FileReader(filename));
			StringBuilder sb = new StringBuilder();
		    String line = br.readLine();

		    while (line != null) {
		        sb.append(line);
		        sb.append(System.lineSeparator());
		        line = br.readLine();
		    }
		    String everything = sb.toString();
		    br.close();
		    return everything;
		    
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
}
