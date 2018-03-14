package com.thatmadhacker.utils.joystick;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JPanel;
import javax.swing.JToggleButton;

import net.java.games.input.Component;
import net.java.games.input.Controller;

/**
 * The Class Joystick.
 */
public class Joystick {

	/** The b. */
	static boolean b = false;

	/** The joystick. */
	static JInputJoystick joystick;

	/** The button. */
	static boolean button = false;

	/** The last Z axis. */
	static int lastZAxis = 0;

	static int lastX = 0, lastY = 0;

	/** The last Z rotation. */
	static int lastZRotation = 0;

	/** The listener. */
	static JoystickListener l;

	static ArrayList<Boolean> last = new ArrayList<Boolean>();

	/**
	 * Instantiates a new joystick.
	 *
	 * @param l
	 *            the l
	 * @param showWindow
	 *            the show window
	 */
	public Joystick(JoystickListener l, boolean showWindow) {
		Joystick.l = l;
		JFrameWindow window = new JFrameWindow();
		window.setVisible(showWindow);
		for (int i = 0; i < 1000; i++) {
			last.add(false);
		}
		stickOrGamepadTypeJoystick_Test_Better(window);
	}

	/**
	 * The listener interface for receiving joystick events. The class that is
	 * interested in processing a joystick event implements this interface, and
	 * the object created with that class is registered with a component using
	 * the component's <code>addJoystickListener<code> method. When the joystick
	 * event occurs, that object's appropriate method is invoked.
	 *
	 * @see JoystickEvent
	 */
	public interface JoystickListener {

		/**
		 * On button pressed.
		 *
		 * @param bt
		 *            the bt
		 */
		public abstract void onButtonPressed(int button);

		/**
		 * On button released.
		 *
		 * @param bt
		 *            the bt
		 */
		public abstract void onButtonReleased(int button);

		/**
		 * On speed change.
		 *
		 * @param value
		 *            the value
		 */
		public abstract void onSpeedChange(int value);

		/**
		 * On rotate.
		 *
		 * @param value
		 *            the value
		 */
		public abstract void onRotate(int value);

		/**
		 * On move.
		 *
		 * @param x
		 *            the x
		 * @param y
		 *            the y
		 */
		public abstract void onMove(int x, int y);
	}

	/**
	 * Stick or gamepad type joystick test better.
	 *
	 * @param window
	 *            the window
	 */
	private static void stickOrGamepadTypeJoystick_Test_Better(JFrameWindow window) {
		/* 61 */ joystick = new JInputJoystick(Controller.Type.STICK, Controller.Type.GAMEPAD);

		/* 64 */ if (!joystick.isControllerConnected()) {
			/* 65 */ window.setControllerName("No controller found!");
		}
		while (!joystick.isControllerConnected()) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		while (true) {
			while (!joystick.pollController()) {
				try {
					window.setControllerName("No controller found!");
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			b = true;

			/* 70 */ window.setControllerName(joystick.getControllerName());

			/* 73 */ if (joystick.getControllerType() == Controller.Type.GAMEPAD) {
				/* 75 */ window.setProgressBar1Name("X Rotation");
				/* 76 */ window.setProgressBar2Name("Y Rotation");

			} else {
				/* 81 */ window.setProgressBar3Name("");
				/* 82 */ window.hideProgresBar3();
			}
			for (;;) {
				try {
					/* 88 */ if (!joystick.pollController()) {
						/* 89 */ window.setControllerName("Controller disconnected!");
						/* 90 */ break;
					}

					/* 94 */ int xValuePercentageLeftJoystick = joystick.getX_LeftJoystick_Percentage();
					/* 95 */ int yValuePercentageLeftJoystick = joystick.getY_LeftJoystick_Percentage();
					if (xValuePercentageLeftJoystick != lastX || yValuePercentageLeftJoystick != lastY) {
						lastX = xValuePercentageLeftJoystick;
						lastY = yValuePercentageLeftJoystick;
						l.onMove(xValuePercentageLeftJoystick, yValuePercentageLeftJoystick);
					}
					/* 96 */ window.setXYAxis(xValuePercentageLeftJoystick, yValuePercentageLeftJoystick);

					/* 99 */ int xValuePercentageRightJoystick = joystick.getX_RightJoystick_Percentage();
					/* 100 */ int yValuePercentageRightJoystick = joystick.getY_RightJoystick_Percentage();
					/* 101 */ window.setZAxis(xValuePercentageRightJoystick);
					if (xValuePercentageRightJoystick != lastZAxis) {
						lastZAxis = xValuePercentageRightJoystick;
						l.onSpeedChange(lastZAxis);
					}
					/* 102 */ window.setZRotation(yValuePercentageRightJoystick);
					if (yValuePercentageRightJoystick != lastZRotation) {
						lastZRotation = yValuePercentageRightJoystick;
						l.onRotate(lastZRotation);
					}

					/* 105 */ if (joystick.getControllerType() == Controller.Type.GAMEPAD) {

						/* 108 */ if (joystick.componentExists(Component.Identifier.Axis.Z)) {
							/* 109 */ int zAxisValuePercentage = joystick.getZAxisPercentage();
							/* 110 */ window.setZAxisGamepad(zAxisValuePercentage);
						}
					}

					/* 115 */ JPanel buttonsPanel = new JPanel(new FlowLayout(0, 1, 1));
					/* 116 */ buttonsPanel.setBounds(6, 19, 246, 110);
					/* 117 */ ArrayList<Boolean> buttonsValues = joystick.getButtonsValues();
					for (int i = 0; i < buttonsValues.size(); i++) {
						if (buttonsValues.get(i).booleanValue() != last.get(i)) {
							last.set(i, buttonsValues.get(i).booleanValue());
							if (last.get(i).booleanValue()) {
								l.onButtonPressed(i + 1);
							} else {
								l.onButtonReleased(i + 1);
							}
						}
					}
					/* 118 */ for (int i = 0; i < buttonsValues.size(); i++) {
						/* 119 */ JToggleButton aToggleButton = new JToggleButton("" + (i + 1),
								((Boolean) buttonsValues.get(i)).booleanValue());
						/* 120 */ aToggleButton.setPreferredSize(new Dimension(48, 25));
						/* 121 */ aToggleButton.setEnabled(false);
						/* 122 */ buttonsPanel.add(aToggleButton);
					}
					/* 124 */ window.setControllerButtons(buttonsPanel);

					/* 127 */ float hatSwitchPosition = joystick.getHatSwitchPosition();
					/* 128 */ window.setHatSwitch(hatSwitchPosition);
				} catch (Exception e) {
				}
				try {
					/* 131 */ Thread.sleep(20L);
				} catch (InterruptedException ex) {
					/* 133 */ Logger.getLogger(Joystick.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		}
	}

	/**
	 * Stick or gamepad type joystick test.
	 *
	 * @param window
	 *            the window
	 */
	@SuppressWarnings("unused")
	private static void stickOrGamepadTypeJoystick_Test(JFrameWindow window) {
		/* 147 */ JInputJoystick joystick = new JInputJoystick(Controller.Type.STICK, Controller.Type.GAMEPAD);

		/* 150 */ if (!joystick.isControllerConnected()) {
			/* 151 */ window.setControllerName("No controller found!");
			/* 152 */ return;
		}

		/* 156 */ window.setControllerName(joystick.getControllerName());

		/* 159 */ if (joystick.getControllerType() == Controller.Type.GAMEPAD) {

			/* 162 */ window.setProgressBar1Name("X Rotation");
			/* 163 */ window.setProgressBar2Name("Y Rotation");
		} else {
			/* 166 */ window.setProgressBar3Name("");
			/* 167 */ window.hideProgresBar3();
		}

		for (;;) {
			/* 173 */ if (!joystick.pollController()) {
				/* 174 */ window.setControllerName("Controller disconnected!");
				/* 175 */ break;
			}

			/* 179 */ int xAxisValuePercentage = joystick.getXAxisPercentage();
			/* 180 */ int yAxisValuePercentage = joystick.getYAxisPercentage();
			/* 181 */ window.setXYAxis(xAxisValuePercentage, yAxisValuePercentage);

			/* 184 */ if (joystick.getControllerType() == Controller.Type.STICK) {

				/* 187 */ int zAxisValuePercentage = joystick.getZAxisPercentage();
				/* 188 */ window.setZAxis(zAxisValuePercentage);
				/* 189 */ int zRotationValuePercentage = joystick.getZRotationPercentage();
				/* 190 */ window.setZRotation(zRotationValuePercentage);

			} else {

				/* 196 */ int xRotationValuePercentage = joystick.getXRotationPercentage();
				/* 197 */ window.setZAxis(xRotationValuePercentage);
				/* 198 */ int yRotationValuePercentage = joystick.getYRotationPercentage();
				/* 199 */ window.setZRotation(yRotationValuePercentage);

				/* 202 */ if (joystick.componentExists(Component.Identifier.Axis.Z)) {
					/* 203 */ int zAxisValuePercentage = joystick.getZAxisPercentage();
					/* 204 */ window.setZAxisGamepad(zAxisValuePercentage);
				}
			}

			/* 209 */ JPanel buttonsPanel = new JPanel(new FlowLayout(0, 1, 1));
			/* 210 */ buttonsPanel.setBounds(6, 19, 246, 110);
			/* 211 */ ArrayList<Boolean> buttonsValues = joystick.getButtonsValues();
			for (int i = 0; i < buttonsValues.size(); i++) {
				if (buttonsValues.get(i).booleanValue() != last.get(i)) {
					last.set(i, buttonsValues.get(i).booleanValue());
					if (last.get(i).booleanValue()) {
						l.onButtonPressed(i + 1);
					} else {
						l.onButtonReleased(i + 1);
					}
				}
			}
			/* 212 */ for (int i = 0; i < buttonsValues.size(); i++) {
				/* 213 */ JToggleButton aToggleButton = new JToggleButton("" + (i + 1),
						((Boolean) buttonsValues.get(i)).booleanValue());
				/* 214 */ aToggleButton.setPreferredSize(new Dimension(48, 25));
				/* 215 */ aToggleButton.setEnabled(false);
				/* 216 */ buttonsPanel.add(aToggleButton);
			}
			/* 218 */ window.setControllerButtons(buttonsPanel);

			/* 221 */ float hatSwitchPosition = joystick.getHatSwitchPosition();
			/* 222 */ window.setHatSwitch(hatSwitchPosition);
			try {
				/* 225 */ Thread.sleep(20L);
			} catch (InterruptedException ex) {
				/* 227 */ Logger.getLogger(Joystick.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

	/**
	 * Stick type joystick test.
	 *
	 * @param window
	 *            the window
	 */
	@SuppressWarnings("unused")
	private static void stickTypeJoystick_Test(JFrameWindow window) {
		/* 241 */ JInputJoystick joystick = new JInputJoystick(Controller.Type.STICK);

		/* 244 */ if (!joystick.isControllerConnected()) {
			/* 245 */ window.setControllerName("No controller found!");
			/* 246 */ return;
		}

		/* 250 */ window.setControllerName(joystick.getControllerName());

		for (;;) {
			/* 255 */ if (!joystick.pollController()) {
				/* 256 */ window.setControllerName("Controller disconnected!");
				/* 257 */ break;
			}

			/* 261 */ int xAxisValuePercentage = joystick.getXAxisPercentage();
			/* 262 */ int yAxisValuePercentage = joystick.getYAxisPercentage();
			/* 263 */ window.setXYAxis(xAxisValuePercentage, yAxisValuePercentage);

			/* 266 */ int zAxisValuePercentage = joystick.getZAxisPercentage();
			/* 267 */ window.setZAxis(zAxisValuePercentage);
			/* 268 */ int zRotationValuePercentage = joystick.getZRotationPercentage();
			/* 269 */ window.setZRotation(zRotationValuePercentage);

			/* 272 */ JPanel buttonsPanel = new JPanel(new FlowLayout(0, 1, 1));
			/* 273 */ buttonsPanel.setBounds(6, 19, 246, 110);
			/* 274 */ ArrayList<Boolean> buttonsValues = joystick.getButtonsValues();
			/* 275 */ for (int i = 0; i < buttonsValues.size(); i++) {
				/* 276 */ JToggleButton aToggleButton = new JToggleButton("" + (i + 1),
						((Boolean) buttonsValues.get(i)).booleanValue());
				/* 277 */ aToggleButton.setPreferredSize(new Dimension(48, 25));
				/* 278 */ aToggleButton.setEnabled(false);
				/* 279 */ buttonsPanel.add(aToggleButton);
			}
			/* 281 */ window.setControllerButtons(buttonsPanel);

			/* 284 */ float hatSwitchPosition = joystick.getHatSwitchPosition();
			/* 285 */ window.setHatSwitch(hatSwitchPosition);
			try {
				/* 288 */ Thread.sleep(20L);
			} catch (InterruptedException ex) {
				/* 290 */ Logger.getLogger(Joystick.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

	/**
	 * Gets the angle.
	 *
	 * @param target
	 *            the target
	 * @return the angle
	 */
	public static float getAngle(Point target) {
		float angle = (float) Math.toDegrees(Math.atan2(target.y - 50, target.x - 50));
		if (angle < 0) {
			angle += 360;
		}
		System.out.println(angle);
		return angle;
	}
}