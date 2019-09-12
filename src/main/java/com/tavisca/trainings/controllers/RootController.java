package com.tavisca.trainings.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tavisca.trainings.exceptions.SuggestionAlreadyExistsException;
import com.tavisca.trainings.exceptions.TodoAlreadyExistsException;
import com.tavisca.trainings.exceptions.TodoDoesNotExistsException;
import com.tavisca.trainings.models.Response;
import com.tavisca.trainings.models.Suggestion;
import com.tavisca.trainings.models.Todo;
import com.tavisca.trainings.services.TodoService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(path = "todoapi/v1")
public class RootController {

	@Autowired
	TodoService todoService;

	@GetMapping
	public String name() {
		return "Welcome to REST API of TODO APP";
	}

	@GetMapping("/suggestions")
	public ResponseEntity<?> getSuggestions() {
		return new ResponseEntity<>(todoService.getAllSuggestions(), HttpStatus.OK);
	}

	@GetMapping("/todo")
	public ResponseEntity<?> getTodos() {
		return new ResponseEntity<>(todoService.getAllTodo(), HttpStatus.OK);
	}

	@PostMapping(path = "/suggestions")
	public ResponseEntity<?> addSuggestion(@Valid @RequestBody Suggestion suggestion)
			throws SuggestionAlreadyExistsException {
		return new ResponseEntity<>(todoService.addSuggestion(suggestion), HttpStatus.CREATED);
	}

	@PostMapping(path = "/todo")
	public ResponseEntity<?> addTodo(@Valid @RequestBody Todo todo) throws TodoAlreadyExistsException {
		return new ResponseEntity<>(todoService.addTodo(todo), HttpStatus.CREATED);
	}

	@PutMapping("/todo/{id}")
	public ResponseEntity<?> updateTodo(@PathVariable("id") long todoId, @Valid @RequestBody Todo updatedTodo)
			throws TodoDoesNotExistsException {
		return new ResponseEntity<>(todoService.updateTodo(todoId, updatedTodo), HttpStatus.OK);
	}

	@DeleteMapping("/todo/{id}")
	public ResponseEntity<?> deleteTodo(@PathVariable("id") long todoId) throws TodoDoesNotExistsException {
		return new ResponseEntity<>(todoService.deleteTodo(todoId), HttpStatus.NO_CONTENT);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleOtherException(Exception e) {
		Response response = Response.builder().statusCode(HttpStatus.BAD_REQUEST.toString()).message(e.getMessage())
				.data("Error").build();
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

}
