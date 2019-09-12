package com.tavisca.trainings.exceptions;

@SuppressWarnings("serial")
public class SuggestionDoesNotExistsException extends Exception {

	public SuggestionDoesNotExistsException() {
		super("Suggestion Does Not Exists. Aborting ...");
	}
}
