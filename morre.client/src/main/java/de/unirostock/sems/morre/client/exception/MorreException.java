package de.unirostock.sems.morre.client.exception;

/**
 * The MORRE database interface has thrown an exception, which the client interface can not handle by it self. 
 *
 */
public class MorreException extends Exception {

	private static final long serialVersionUID = -7629276841894307941L;

	public MorreException() {
		super();
	}

	public MorreException(String message) {
		super(message);
	}

	public MorreException(Throwable cause) {
		super(cause);
	}

	public MorreException(String message, Throwable cause) {
		super(message, cause);
	}

}
