package com.thatmadhacker.utils.mdns;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class MDNS {

	@SuppressWarnings("resource")
	public static void start(String ip) throws Exception {

		final ServerSocket ss = new ServerSocket(6712);
		new Thread(new Runnable() {
			public void run() {
				while (true) {
					try {
						Socket s = ss.accept();
						PrintWriter out = new PrintWriter(s.getOutputStream(),true);
						out.println(ip);
						out.close();
						s.close();
					} catch (IOException e) {}
				}
			}
		}).start();
	}
	
	public static ArrayList<InetAddress> search(String ip){
		ArrayList<InetAddress> list = new ArrayList<InetAddress>();
		for(int i = 0; i < 1000; i++){
			for(int i1 = 0; i1 < 1000; i1++){
				try {
					Socket s = new Socket("192.168."+i+"."+i1, 6712);
					Scanner in = new Scanner(s.getInputStream());
					if(ip.equalsIgnoreCase(in.nextLine())){
						list.add(s.getLocalAddress());
					}
					in.close();
					s.close();
				} catch (IOException e) {}
				
			}
		}
		return list;
	}
	public static ArrayList<InetAddress> search(String ip, String prefix){
		ArrayList<InetAddress> list = new ArrayList<InetAddress>();
		for(int i = 0; i < 1000; i++){
			for(int i1 = 0; i1 < 1000; i1++){
				try {
					Socket s = new Socket(prefix+"."+i+"."+i1, 6712);
					Scanner in = new Scanner(s.getInputStream());
					if(ip.equalsIgnoreCase(in.nextLine())){
						list.add(s.getLocalAddress());
					}
					in.close();
					s.close();
				} catch (IOException e) {}
				
			}
		}
		return list;
	}
}