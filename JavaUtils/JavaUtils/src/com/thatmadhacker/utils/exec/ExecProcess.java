package com.thatmadhacker.utils.exec;

// TODO: Auto-generated Javadoc
/**
 * The Class ExecProcess.
 */
public class ExecProcess {

	/** The command. */
	String command;
	
	/** The process. */
	Process process;
	
	/**
	 * Instantiates a new exec process.
	 *
	 * @param command the command
	 */
	public ExecProcess(String command){
		this.command = command;
		try{
		process = build();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Gets the process.
	 *
	 * @return the process
	 */
	public Process getProcess() {
		return process;
	}

	/**
	 * Builds the.
	 *
	 * @return the process
	 * @throws Exception the exception
	 */
	private Process build() throws Exception{
		ProcessBuilder builder = new ProcessBuilder(command);
		return builder.start();
	}
	
}
