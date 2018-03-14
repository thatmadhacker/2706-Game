package com.thatmadhacker.utils.sphero;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;

import se.nicklasgavelin.bluetooth.Bluetooth;
import se.nicklasgavelin.bluetooth.Bluetooth.EVENT;
import se.nicklasgavelin.bluetooth.BluetoothDevice;
import se.nicklasgavelin.bluetooth.BluetoothDiscoveryListener;
import se.nicklasgavelin.sphero.Robot;
import se.nicklasgavelin.sphero.RobotListener;
import se.nicklasgavelin.sphero.command.CommandMessage;
import se.nicklasgavelin.sphero.command.FrontLEDCommand;
import se.nicklasgavelin.sphero.exception.InvalidRobotAddressException;
import se.nicklasgavelin.sphero.exception.RobotBluetoothException;
import se.nicklasgavelin.sphero.response.InformationResponseMessage;
import se.nicklasgavelin.sphero.response.ResponseMessage;

// TODO: Auto-generated Javadoc
/**
 * Simple test class to test the Sphero API.
 *
 * @author Nicklas Gavelin, nicklas.gavelin@gmail.com, Luleå University of Technology
 */
public class SpheroTestFrame extends JFrame implements RobotListener, BluetoothDiscoveryListener
{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 6998786554264771793L;
	
	/** The l. */
	public SpheroListener l;
	
	/** The rl. */
	public RobotListener rl;
	
	/** The responses. */
	// Internal storage
	private int responses = 0;
	
	/** The ct. */
	private ConnectThread ct;
	
	/** The disconnect button. */
	private JButton connectButton, disconnectButton;

	/**
	 * Main method.
	 *
	 * @param args Will be ignored
	 */
	public static void main( String[] args )
	{
		new SpheroTestFrame();
		// new Thread( new Example_Site_API() ).start();
	}

	/**
	 * Our example application.
	 */
	@SuppressWarnings("unused")
	public SpheroTestFrame(){
		SpheroTestFrame example_Site_API = new SpheroTestFrame(new SpheroListener(){

			@Override
			public void onConnect(Robot r) {
				
			}},this);
	}
	
	/**
	 * Instantiates a new sphero test frame.
	 *
	 * @param l the l
	 * @param rl the rl
	 */
	public SpheroTestFrame(SpheroListener l, RobotListener rl)
	{
		super( "Example API usage" );
		this.l = l;
		this.rl = rl;
		this.setLayout( new GridLayout( 2, 1 ) );

		// Connect button
		connectButton = new JButton( "Connect to available devices" );
		disconnectButton = new JButton( "Disconnect from all devices" );

		// Bind action to our connect button
		connectButton.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed( ActionEvent e )
			{
				// Check if we have something previous to stop
				if( ct != null )
					ct.stopThread();

				// Create a new thread
				ct = new ConnectThread();
				ct.start();

				// Toggle our button
				connectButton.setEnabled( false );
				disconnectButton.setEnabled( true );
			}
		} );

		// Bind action to the disconnect button
		disconnectButton.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed( ActionEvent e )
			{
				// Check if we have something to stop
				if( ct != null )
					ct.stopThread();

				// Toggle our buttons
				connectButton.setEnabled( true );
				disconnectButton.setEnabled( false );
			}
		} );

		// Add buttons to our GUI
		this.add( connectButton );
		this.add( disconnectButton );

		// Set some default stuff
		this.pack();
		this.setVisible( true );
		this.setDefaultCloseOperation( EXIT_ON_CLOSE );
	}
	
	/**
	 * Instantiates a new sphero test frame.
	 *
	 * @param adress the adress
	 * @param rl the rl
	 * @param l the l
	 * @throws Exception the exception
	 */
	public SpheroTestFrame(String adress, RobotListener rl, SpheroListener l) throws Exception{
		//6886E7096619 sphero YGO adress
		final String bluetoothAddress = adress;
		Bluetooth bt = new Bluetooth(this, Bluetooth.SERIAL_COM );
		BluetoothDevice btd = new BluetoothDevice( bt, "btspp://" + bluetoothAddress + ":1;authenticate=true;encrypt=false;master=false" );
		Robot r = new Robot( btd );
		if ( r.connect() )
		{
		r.addListener( rl );
		r.rgbTransition( 255, 0, 0, 0, 255, 255, 50 );
		l.onConnect(r);
		}
	}

	/**
	 * Set connect button state (also affects the disconnect button).
	 *
	 * @param enabled True to enable, false otherwise
	 */
	private void setConnectEnabled( boolean enabled )
	{
		this.connectButton.setEnabled( enabled );
		this.disconnectButton.setEnabled( !enabled );
	}

	/**
	 * Handles the detection of new devices and listens on our robots for
	 * responses and events.
	 */
	private class ConnectThread extends Thread implements BluetoothDiscoveryListener, Runnable, RobotListener
	{
		
		/** The bt. */
		// Internal storage
		private Bluetooth bt;
		
		/** The stop. */
		private boolean stop = false;
		
		/** The robots. */
		private Collection<Robot> robots;

		/**
		 * Create a connect thread.
		 */
		public ConnectThread()
		{
			this.robots = new ArrayList<Robot>();
		}

		/**
		 * Stop everything regarding the connection and robots.
		 */
		private void stopThread()
		{
			if( bt != null )
				bt.cancelDiscovery();
			this.stop = true;

			// Disconnect from all robots and clear the connected list
			for( Robot r : robots )
				r.disconnect();
			robots.clear();
		}

		/* (non-Javadoc)
		 * @see java.lang.Thread#run()
		 */
		@Override
		public void run()
		{
			try
			{
				// Will perform a bluetooth discovery before connecting to
				// any devices
				bt = new Bluetooth( this, Bluetooth.SERIAL_COM );
				bt.discover(); // # COMMENT THIS IF UNCOMMENTING THE BELOW AREA #

				// Uncomment the code below and comment out the bt.discover() line above
				// to
				// connect directly to a given Sphero

				// // ## START UNCOMMENT ##
				// final String bluetoothAddress = "0006664438B8";
				// BluetoothDevice btd = new BluetoothDevice( bt, "btspp://" +
				// bluetoothAddress + ":1;authenticate=true;encrypt=false;master=false" );
				//
				// // Create the robot from the bluetooth device
				// Robot r = new Robot( btd );
				//
				// // Try to connect to the robot
				// if ( r.connect() )
				// {
				// // Add ourselves as listeners
				// r.addListener( this );
				//
				// // Send a rgb transition command macro
				// r.rgbTransition( 255, 0, 0, 0, 255, 255, 50 );
				//
				// // Send a direct command
				// r.sendCommand( new FrontLEDCommand( 1F ) );
				// }
				// // ## END UNCOMMENT ##

				// Run forever, euheuheuh!
				while( !stop )
				{
					try
					{
						Thread.sleep( 5000 );
					}
					catch( InterruptedException e )
					{
					}
				}
			}
			catch( Exception e )
			{
				// Failure in searching for devices for some reason.
				e.printStackTrace();
			}
		}

		/*
		 * *************************************
		 * BLUETOOTH DISCOVERY STUFF
		 */

		/**
		 * Called when the device search is completed with detected devices.
		 *
		 * @param devices The devices detected
		 */
		@Override
		public void deviceSearchCompleted( Collection<BluetoothDevice> devices )
		{
			// Device search is completed
			System.out.println( "Completed device discovery" );

			// Try and see if we can find any Spheros in the found devices
			for( BluetoothDevice d : devices )
			{
				// Check if the Bluetooth device is a Sphero device or not
				if( Robot.isValidDevice( d ) )
				{
					System.out.println( "Found robot " + d.getName() );
					@SuppressWarnings("resource")
					Scanner scan = new Scanner(System.in);
					System.out.println("Would you like to connect to: "+d.getName());
					if(!scan.nextLine().equalsIgnoreCase("Y")){
						continue;
					}
					// We got a valid device (Sphero device), connect to it and
					// have some fun with colors.
					try
					{
						// Create our robot from the Bluetooth device that we got
						Robot r = new Robot( d );

						// Add ourselves as listeners for the responses
						r.addListener(rl);

						// Check if we can connect
						if(r.connect()){
						
							// Add robots to our connected robots list
							robots.add( r );
							
							System.out.println( "Connected to " + d.getName() + " : " + d.getAddress() );
							r.rgbTransition( 255, 0, 0, 0, 255, 255, 50 );
							l.onConnect(r);
							// Send direct command
							r.sendCommand( new FrontLEDCommand( 1 ) );
						
						
						}
					}
					catch( InvalidRobotAddressException ex )
					{
						ex.printStackTrace();
					}
					catch( RobotBluetoothException ex )
					{
						ex.printStackTrace();
					}
				}
			}

			// Disable the thread and set connected button state
			if( robots.isEmpty() )
			{
				this.stopThread();
				setConnectEnabled( true );

			}
		}

		/**
		 * Called when the search is started.
		 */
		@Override
		public void deviceSearchStarted()
		{
			System.out.println( "Started device search" );
		}

		/**
		 * Called if something went wrong with the device search.
		 *
		 * @param error The error that occurred
		 */
		@Override
		public void deviceSearchFailed( EVENT error )
		{
			System.err.println( "Failed with device search: " + error.getErrorMessage() );
		}

		/**
		 * Called when a Bluetooth device is discovered.
		 *
		 * @param device The device discovered
		 */
		@Override
		public void deviceDiscovered( BluetoothDevice device )
		{
		
		}

		/*
		 * ********************************************
		 * ROBOT STUFF
		 */

		/**
		 * Called when a response is received from a robot.
		 *
		 * @param r The robot the event concerns
		 * @param response The response received
		 * @param dc The command the response is concerning
		 */
		@Override
		public void responseReceived( Robot r, ResponseMessage response, CommandMessage dc )
		{
			//System.out.println( "(" + ( ++responses ) + ") Received response: " + response.getResponseCode() + " to message " + dc.getCommand() );
		}

		/**
		 * Event that may occur for a robot.
		 *
		 * @param r The robot the event concerns
		 * @param code The event code for the event
		 */
		@Override
		public void event( Robot r, EVENT_CODE code )
		{
			
		}

		/* (non-Javadoc)
		 * @see se.nicklasgavelin.sphero.RobotListener#informationResponseReceived(se.nicklasgavelin.sphero.Robot, se.nicklasgavelin.sphero.response.InformationResponseMessage)
		 */
		@Override
		public void informationResponseReceived( Robot r, InformationResponseMessage response )
		{
			// Information response (Ex. Sensor data)
		}
	}
	
	/**
	 * The listener interface for receiving sphero events.
	 * The class that is interested in processing a sphero
	 * event implements this interface, and the object created
	 * with that class is registered with a component using the
	 * component's <code>addSpheroListener<code> method. When
	 * the sphero event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see SpheroEvent
	 */
	public interface SpheroListener{
		
		/**
		 * On connect.
		 *
		 * @param r the r
		 */
		public abstract void onConnect(Robot r);
	}
	
	/* (non-Javadoc)
	 * @see se.nicklasgavelin.sphero.RobotListener#event(se.nicklasgavelin.sphero.Robot, se.nicklasgavelin.sphero.RobotListener.EVENT_CODE)
	 */
	@Override
	public void event(Robot arg0, EVENT_CODE arg1) {
		System.out.println( "Received event: " + arg1 );
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
	public void responseReceived(Robot arg0, ResponseMessage response, CommandMessage dc) {
		System.out.println( "(" + ( ++responses ) + ") Received response: " + response.getResponseCode() + " to message " + dc.getCommand() );
	}

	/* (non-Javadoc)
	 * @see se.nicklasgavelin.bluetooth.BluetoothDiscoveryListener#deviceDiscovered(se.nicklasgavelin.bluetooth.BluetoothDevice)
	 */
	@Override
	public void deviceDiscovered(BluetoothDevice arg0) {
		
	}

	/* (non-Javadoc)
	 * @see se.nicklasgavelin.bluetooth.BluetoothDiscoveryListener#deviceSearchCompleted(java.util.Collection)
	 */
	@Override
	public void deviceSearchCompleted(Collection<BluetoothDevice> arg0) {
		
	}

	/* (non-Javadoc)
	 * @see se.nicklasgavelin.bluetooth.BluetoothDiscoveryListener#deviceSearchFailed(se.nicklasgavelin.bluetooth.Bluetooth.EVENT)
	 */
	@Override
	public void deviceSearchFailed(EVENT arg0) {
		
	}

	/* (non-Javadoc)
	 * @see se.nicklasgavelin.bluetooth.BluetoothDiscoveryListener#deviceSearchStarted()
	 */
	@Override
	public void deviceSearchStarted() {
		
	}
}