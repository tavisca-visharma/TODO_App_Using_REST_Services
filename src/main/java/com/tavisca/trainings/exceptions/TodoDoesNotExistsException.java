package com.tavisca.trainings.exceptions;

@SuppressWarnings("serial")
public class TodoDoesNotExistsException extends Exception {

	public TodoDoesNotExistsException() {
		super("Todo Does Not Exists. Aborting ...");
	}
}
