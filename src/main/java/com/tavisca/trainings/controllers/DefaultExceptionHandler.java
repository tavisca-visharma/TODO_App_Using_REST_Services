package com.tavisca.trainings.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.tavisca.trainings.models.Response;

@ControllerAdvice
@RestController
public class DefaultExceptionHandler extends ResponseEntityExceptionHandler {
	
	@Autowired
	Response response;
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> errorResponse(Exception e) {
		return new ResponseEntity<>(response.badRequest(e.getMessage(), "Error"),HttpStatus.BAD_REQUEST);
	}

}
