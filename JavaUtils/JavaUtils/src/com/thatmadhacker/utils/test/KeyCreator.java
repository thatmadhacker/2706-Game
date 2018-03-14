package com.thatmadhacker.utils.test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.KeyPair;
import java.util.Scanner;

import com.thatmadhacker.utils.crypto.RSA;

import sun.misc.BASE64Encoder;

public class KeyCreator {
	
	@SuppressWarnings("resource")
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter a destination path for private key!");
		String path = scan.nextLine();
		System.out.println("Enter a destination path for public key!");
		String pubPath = scan.nextLine();
		File f = new File(path);
		File pubFile = new File(pubPath);
		f.delete();
		if(!f.exists()){
			try {
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		pubFile.delete();
		if(!pubFile.exists()){
			try {
				pubFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			KeyPair keys = RSA.genKeys();
			PrintWriter out = new PrintWriter(new FileWriter(path,true));
			out.println(new BASE64Encoder().encode(keys.getPrivate().getEncoded()));
			out.close();
			out = null;
			out = new PrintWriter(new FileWriter(pubFile,true));
			out.println(new BASE64Encoder().encode(keys.getPublic().getEncoded()));
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
