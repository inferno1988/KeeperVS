package net.it_tim.security_console.VideoExeptions;

public class CantPlayException extends Exception {
	private static final long serialVersionUID = 5255902107109409759L;

	String mistake;

	public CantPlayException() {
		super(); // call superclass constructor
		mistake = "unknown";
	}

	public CantPlayException(String err) {
		super(err); // call super class constructor
		mistake = err; // save message
	}

	public String getError() {
		return mistake;
	}
}
