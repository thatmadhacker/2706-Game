package com.thatmadhacker.utils.test;

import javax.swing.JFrame;

import com.thatmadhacker.utils.swing.ConsoleWindow;

// TODO: Auto-generated Javadoc
/**
 * The Class ConsoleSwingWindowTest.
 */
public class ConsoleSwingWindowTest {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	@SuppressWarnings("unused")
	public static void main(String[] args){
		JFrame frame = new ConsoleWindow("Console Window Test",1000,750,400,600).create("C:/Users/finma/Desktop/test.jar");
	}
}
