package com.thatmadhacker.utils.swing;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;

// TODO: Auto-generated Javadoc
/**
 * The Class ConsoleWindow.
 */
public class ConsoleWindow {

	/** The name. */
	String name;
	
	/** The box height. */
	int width, height,boxWidth,boxHeight;
	
	/**
	 * Instantiates a new console window.
	 *
	 * @param name the name
	 * @param width the width
	 * @param height the height
	 * @param boxWidth the box width
	 * @param boxHeight the box height
	 */
	public ConsoleWindow(String name, int width, int height,int boxWidth,int boxHeight){
		this.name = name;
		this.width = width;
		this.height = height;
		this.boxHeight = boxHeight;
		this.boxWidth = boxWidth;
	}
	
	/**
	 * Creates the.
	 *
	 * @param jarfile the jarfile
	 * @return the j frame
	 */
	public JFrame create(String jarfile){
		JFrame frame = new JFrame(name);
		frame.setSize(new Dimension(width,height));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JScrollBox scrollBox = WindowHelper.createScrollableTextBox(boxWidth,boxHeight);
		JPanel panel = new JPanel();
		frame.setLayout(new GridBagLayout());
		panel.add(scrollBox.pane, new GridBagConstraints());
		frame.add(panel);
		frame.setVisible(true);
		WindowHelper.printToScrollPane("Test", scrollBox);
		new Thread(new ConsoleThread(scrollBox,jarfile)).start();
		return frame;
	}
	
	/**
	 * The Class ConsoleThread.
	 */
	private class ConsoleThread implements Runnable{
		
		/** The box. */
		JScrollBox box;
		
		/** The file. */
		String file;
		
		/**
		 * Instantiates a new console thread.
		 *
		 * @param box the box
		 * @param file the file
		 */
		public ConsoleThread(JScrollBox box, String file){
			this.box = box;
			this.file = file;
		}
		
		/* (non-Javadoc)
		 * @see java.lang.Runnable#run()
		 */
		@SuppressWarnings("resource")
		@Override
		public void run(){
			try{
			Process p = Runtime.getRuntime().exec("java -jar "+file);
			Scanner in = new Scanner(p.getInputStream());
			while(true){
				String ne = in.nextLine();
				WindowHelper.printToScrollPane(ne, box);
				System.out.println(ne);
			}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
	}
}
