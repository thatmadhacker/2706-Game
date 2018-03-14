package com.thatmadhacker.utils.test;

import com.thatmadhacker.utils.sphero.SpheroTestFrame;
import com.thatmadhacker.utils.sphero.SpheroTestFrame.SpheroListener;

import se.nicklasgavelin.sphero.Robot;
import se.nicklasgavelin.sphero.RobotListener;
import se.nicklasgavelin.sphero.command.CommandMessage;
import se.nicklasgavelin.sphero.response.InformationResponseMessage;
import se.nicklasgavelin.sphero.response.ResponseMessage;

// TODO: Auto-generated Javadoc
/**
 * The Class SpheroTest.
 */
public class SpheroTest implements SpheroListener, RobotListener{
	
	/** The r. */
	Robot r = null;
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args){
		new SpheroTest();
	}
	
	/**
	 * Instantiates a new sphero test.
	 */
	public SpheroTest(){
		@SuppressWarnings("unused")
		SpheroTestFrame frame = new SpheroTestFrame(this,this);
		while(r == null){
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
					e.printStackTrace();
			}
		}
		//Commands here!
	}
	
	/* (non-Javadoc)
	 * @see se.nicklasgavelin.sphero.RobotListener#event(se.nicklasgavelin.sphero.Robot, se.nicklasgavelin.sphero.RobotListener.EVENT_CODE)
	 */
	@Override
	public void event(Robot arg0, EVENT_CODE arg1) {
		
	}
	
	/* (non-Javadoc)
	 * @see se.nicklasgavelin.sphero.RobotListener#informationResponseReceived(se.nicklasgavelin.sphero.Robot, se.nicklasgavelin.sphero.response.InformationResponseMessage)
	 */
	@Override
	public void informationResponseReceived(Robot arg0, InformationResponseMessage arg1) {
		
	}
	
	/* (non-Javadoc)
	 * @see se.nicklasgavelin.sphero.RobotListener#responseReceived(se.nicklasgavelin.sphero.Robot, se.nicklasgavelin.sphero.response.ResponseMessage, se.nicklasgavelin.sphero.command.CommandMessage)
	 */
	@Override
	public void responseReceived(Robot arg0, ResponseMessage arg1, CommandMessage arg2) {
		
	}
	
	/* (non-Javadoc)
	 * @see com.thatmadhacker.utils.sphero.SpheroTestFrame.SpheroListener#onConnect(se.nicklasgavelin.sphero.Robot)
	 */
	@Override
	public void onConnect(Robot r) {
		this.r = r;
	}
}
