package com.thatmadhacker.utils.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Scanner;

import com.thatmadhacker.utils.crypto.RSA;

import sun.misc.BASE64Decoder;

public class CertificateSigner {
	
	@SuppressWarnings("resource")
	public static void main(String[] args){
		File f = new File("cert.cert");
		f.delete();
		try {
			f.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		PrintWriter out = null;
		try {
			out = new PrintWriter(new FileWriter(f));
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Enter path to private key!");
		Scanner scan = new Scanner(System.in);
		String path = scan.nextLine();
		Scanner in = null;
		try {
			in = new Scanner(new File(path));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String key = "";
		while(in.hasNextLine()){
			key = key + in.nextLine();
		}
		in.close();
		System.out.println("Enter a path to a public key!");
		String publicKeyPath = scan.nextLine();
		File publicKeyFile = new File(publicKeyPath);
		in = null;
		try {
			in = new Scanner(publicKeyFile);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		String publicKey = in.nextLine();
		try{
			KeyFactory kf = KeyFactory.getInstance("RSA");
			PrivateKey privateKey = kf.generatePrivate(new PKCS8EncodedKeySpec(new BASE64Decoder().decodeBuffer(key)));
			out.print(RSA.sign(publicKey, privateKey));
			out.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
