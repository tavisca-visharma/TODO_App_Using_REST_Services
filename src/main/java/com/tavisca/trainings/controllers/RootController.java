package com.tavisca.trainings.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tavisca.trainings.services.Handler;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class RootController {

	Handler handler = new Handler();

	@GetMapping
	public String name() {
		return "Welcome to REST API of TODO APP";
	}

	@GetMapping("/suggestions")
	public ResponseEntity<?> getSuggestions() {
		return new ResponseEntity<>(handler.getAllSuggestions(), HttpStatus.OK);
	}

	@GetMapping("/todo")
	public ResponseEntity<?> getTodos() {
		return new ResponseEntity<>(handler.getAllTodo(), HttpStatus.OK);
	}

	@PostMapping(path = "/suggestions")
	public ResponseEntity<?> addSuggestions(@RequestBody String jsonString) {
		
		handler.addNewSuggestion(jsonString);
		return new ResponseEntity<>(handler.getNewlyAddedSuggestion(), HttpStatus.OK);

	}

	@PostMapping(path = "/todo")
	public ResponseEntity<?> addTodo(@RequestBody String jsonString) {

		handler.addNewTodo(jsonString);
		return new ResponseEntity<>(handler.getNewlyAddedTodo(), HttpStatus.OK);

	}

	@PutMapping("/todo/{id}")
	public ResponseEntity<?> updateTodo(@PathVariable("id") int todoId, @RequestBody String jsonTodo) {

		return new ResponseEntity<>(handler.getUpdatedTodo(todoId, jsonTodo), HttpStatus.OK);

	}

	@DeleteMapping("/todo/{id}")
	public ResponseEntity<?> deleteTodo(@PathVariable("id") int todoId) {
		return new ResponseEntity<>(handler.getDeletedTodo(todoId), HttpStatus.OK);

	}

}
