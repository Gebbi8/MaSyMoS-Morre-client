package de.unirostock.sems.morre.client.exception;

public class MorreClientException extends MorreCommunicationException {

	private static final long serialVersionUID = -1268322249656081056L;

	public MorreClientException() {
		super();
	}

	public MorreClientException(String message) {
		super(message);
	}

	public MorreClientException(Throwable cause) {
		super(cause);
	}

	public MorreClientException(String message, Throwable cause) {
		super(message, cause);
	}

}
