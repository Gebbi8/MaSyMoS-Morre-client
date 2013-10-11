package de.unirostock.sems.morre.client.exception;

public class MorreError extends Exception {

	private static final long serialVersionUID = -7629276841894307941L;

	public MorreError() {
		super();
	}

	public MorreError(String message) {
		super(message);
	}

	public MorreError(Throwable cause) {
		super(cause);
	}

	public MorreError(String message, Throwable cause) {
		super(message, cause);
	}

}
