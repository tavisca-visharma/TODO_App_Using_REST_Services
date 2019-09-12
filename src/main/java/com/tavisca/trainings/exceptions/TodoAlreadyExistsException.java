package com.tavisca.trainings.exceptions;

@SuppressWarnings("serial")
public class TodoAlreadyExistsException extends Exception {

	public TodoAlreadyExistsException() {
		super("Todo Already Exists !!!");
	}
}
