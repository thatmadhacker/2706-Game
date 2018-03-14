package com.thatmadhacker.utils.test;

import com.thatmadhacker.utils.joystick.Joystick;
import com.thatmadhacker.utils.joystick.Joystick.JoystickListener;

/**
 * The Class JoystickTest.
 */
public class JoystickTest implements JoystickListener{

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args){
		new JoystickTest();
	}
	
	/**
	 * Instantiates a new joystick test.
	 */
	public JoystickTest(){
		@SuppressWarnings("unused")
		Joystick joystick = new Joystick(this,true);
	}
	
	/* (non-Javadoc)
	 * @see com.thatmadhacker.utils.joystick.Joystick.JoystickListener#onButtonPressed(com.thatmadhacker.utils.joystick.ButtonType)
	 */
	@Override
	public void onButtonPressed(int button) {
		
	}
	
	/* (non-Javadoc)
	 * @see com.thatmadhacker.utils.joystick.Joystick.JoystickListener#onButtonReleased(com.thatmadhacker.utils.joystick.ButtonType)
	 */
	@Override
	public void onButtonReleased(int button) {
		
	}
	
	/* (non-Javadoc)
	 * @see com.thatmadhacker.utils.joystick.Joystick.JoystickListener#onSpeedChange(int)
	 */
	@Override
	public void onSpeedChange(int value) {
		
	}
	
	/* (non-Javadoc)
	 * @see com.thatmadhacker.utils.joystick.Joystick.JoystickListener#onRotate(int)
	 */
	@Override
	public void onRotate(int value) {
		
	}
	
	/* (non-Javadoc)
	 * @see com.thatmadhacker.utils.joystick.Joystick.JoystickListener#onMove(int, int)
	 */
	@Override
	public void onMove(int x, int y) {
		
	}
}
