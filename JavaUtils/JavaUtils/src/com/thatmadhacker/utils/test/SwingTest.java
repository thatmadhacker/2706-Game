package com.thatmadhacker.utils.test;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.thatmadhacker.utils.swing.JScrollBox;
import com.thatmadhacker.utils.swing.WindowHelper;

// TODO: Auto-generated Javadoc
/**
 * The Class SwingTest.
 */
public class SwingTest {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws ClassNotFoundException the class not found exception
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 * @throws UnsupportedLookAndFeelException the unsupported look and feel exception
	 */
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException{
		UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		JFrame.setDefaultLookAndFeelDecorated(true);
		JFrame frame = new JFrame("SwingExample");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1000,750);
		JScrollBox scrollBox = WindowHelper.createScrollableTextBox(400,400);
		JPanel panel = new JPanel();
		frame.setLayout( new GridBagLayout() );
		panel.add(scrollBox.pane, new GridBagConstraints());
		frame.add(panel);
		frame.setVisible(true);
		int i = 0;
		while(true){
			WindowHelper.printToScrollPane(String.valueOf(i), scrollBox);
			i++;
		}
	}
}
