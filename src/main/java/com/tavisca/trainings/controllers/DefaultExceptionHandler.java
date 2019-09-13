package com.tavisca.trainings.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import com.tavisca.trainings.exceptions.SuggestionAlreadyExistsException;
import com.tavisca.trainings.exceptions.SuggestionDoesNotExistsException;
import com.tavisca.trainings.exceptions.TodoAlreadyExistsException;
import com.tavisca.trainings.exceptions.TodoDoesNotExistsException;
import com.tavisca.trainings.models.Response;

@ControllerAdvice
@RestController
public class DefaultExceptionHandler extends SimpleMappingExceptionResolver {

	@ExceptionHandler(SuggestionAlreadyExistsException.class)
	public ResponseEntity<?> handleSuggestionAlreadyExistsException(SuggestionAlreadyExistsException e) {
		Response response = Response.builder().statusCode(HttpStatus.CONFLICT.toString()).message(e.getMessage())
				.data("Error").build();
		return new ResponseEntity<>(response, HttpStatus.CONFLICT);
	}

	@ExceptionHandler(SuggestionDoesNotExistsException.class)
	public ResponseEntity<?> handleSuggestionDoesNotExistsException(SuggestionDoesNotExistsException e) {
		Response response = Response.builder().statusCode(HttpStatus.NOT_FOUND.toString()).message(e.getMessage())
				.data("Error").build();
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(TodoAlreadyExistsException.class)
	public ResponseEntity<?> handleTodoAlreadyExistsException(TodoAlreadyExistsException e) {
		Response response = Response.builder().statusCode(HttpStatus.CONFLICT.toString()).message(e.getMessage())
				.data("Error").build();
		return new ResponseEntity<>(response, HttpStatus.CONFLICT);
	}

	@ExceptionHandler(TodoDoesNotExistsException.class)
	public ResponseEntity<?> handleTodoDoesNotExistsException(TodoDoesNotExistsException e) {
		Response response = Response.builder().statusCode(HttpStatus.NOT_FOUND.toString()).message(e.getMessage())
				.data("Error").build();
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleOtherException(Exception e) {
		Response response = Response.builder().statusCode(HttpStatus.BAD_REQUEST.toString()).message(e.getMessage())
				.data("Error").build();
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	/*
	 * { HttpRequestMethodNotSupportedException.class,
	 * HttpMediaTypeNotSupportedException.class,
	 * HttpMediaTypeNotAcceptableException.class,
	 * MissingPathVariableException.class,
	 * MissingServletRequestParameterException.class,
	 * ServletRequestBindingException.class, ConversionNotSupportedException.class,
	 * TypeMismatchException.class, HttpMessageNotReadableException.class,
	 * HttpMessageNotWritableException.class, MethodArgumentNotValidException.class,
	 * MissingServletRequestPartException.class, BindException.class,
	 * NoHandlerFoundException.class, AsyncRequestTimeoutException.class })
	 */

}
