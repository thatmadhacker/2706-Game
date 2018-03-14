package com.thatmadhacker.utils.test;

import java.io.PrintWriter;
import java.util.Scanner;

import com.thatmadhacker.utils.exec.ExecProcess;

// TODO: Auto-generated Javadoc
/**
 * The Class TestExec.
 */
public class TestExec {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	@SuppressWarnings("resource")
	public static void main(String[] args){
		Process process = new ExecProcess("cmd").getProcess();
		final Scanner in = new Scanner(process.getInputStream());
		Scanner scan = new Scanner(System.in);
		PrintWriter out = new PrintWriter(process.getOutputStream(),true);
		System.out.println("Enter commands");
		new Thread(new Runnable(){

			@Override
			public void run() {
				while(true){
				System.out.println(in.nextLine());
				}
			}
			
		}).start();
		while(true){
		out.println(scan.nextLine());
		}
	}
}
