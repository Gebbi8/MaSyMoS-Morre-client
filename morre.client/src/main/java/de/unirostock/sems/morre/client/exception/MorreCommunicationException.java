package de.unirostock.sems.morre.client.exception;

import java.io.IOException;

public class MorreCommunicationException extends IOException {

	private static final long serialVersionUID = 1589736586376163967L;

	public MorreCommunicationException() {
		super();
	}

	public MorreCommunicationException(String message) {
		super(message);
	}

	public MorreCommunicationException(Throwable cause) {
		super(cause);
	}

	public MorreCommunicationException(String message, Throwable cause) {
		super(message, cause);
	}

}
