package com.thatmadhacker.utils.network;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class FileIO {
	
	public static void sendFile(File f, OutputStream out) throws IOException{
		InputStream in = new FileInputStream(f);
		byte[] bytes = new byte[(int) f.length()];
		in.read(bytes);
		PrintWriter pw = new PrintWriter(out);
		pw.println(f.getName());
		pw.println(bytes.length);
		out.write(bytes);
		in.close();
	}
	public static File recieveFile(InputStream in, String path) throws IOException{
		Scanner scan = new Scanner(in);
		String name = scan.nextLine();
		byte[] bytes = new byte[Integer.valueOf(scan.nextLine())];
		in.read(bytes);
		
		File f;
		if(path.endsWith("/") || path.endsWith("\\")){
			f = new File(path+name);
		}else{
			f = new File(path+"/"+name);
		}
		OutputStream out = new FileOutputStream(f);
		out.write(bytes);
		out.close();
		scan.close();
		return f;
		}
}
