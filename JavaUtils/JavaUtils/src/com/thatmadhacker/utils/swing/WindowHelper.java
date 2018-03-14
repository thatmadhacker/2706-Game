package com.thatmadhacker.utils.swing;

import java.awt.Dimension;

import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

// TODO: Auto-generated Javadoc
/**
 * The Class WindowHelper.
 */
public class WindowHelper {

	/**
	 * Creates the scrollable text box.
	 *
	 * @param width the width
	 * @param height the height
	 * @return the j scroll box
	 */
	public static JScrollBox createScrollableTextBox(int width, int height){
		JScrollPane pane;
		JTextArea area = new JTextArea();
		area.setSize(width, height);
		area.setPreferredSize(new Dimension(width,height));
		area.setLineWrap(false);
		area.setEditable(false);
		area.setVisible(true);
		pane = new JScrollPane(area);
		pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		return new JScrollBox(pane,area);
		

	}
	
	/**
	 * Prints the to scroll pane.
	 *
	 * @param s the s
	 * @param pane the pane
	 */
	public static void printToScrollPane(String s, JScrollBox pane){
		synchronized(pane){
			pane.area.append(s+"\n");
			JScrollBar scrollBar = pane.pane.getVerticalScrollBar();
			int max = scrollBar.getMaximum();
			long timeout = System.currentTimeMillis();
			while(true){
				if(scrollBar.getMaximum() != max && pane.pane.isDisplayable()){
					scrollBar.setValue(scrollBar.getMaximum());
				}else if(System.currentTimeMillis() - timeout > 1000){
					break;
				}
				
			}
		}
	}
}
