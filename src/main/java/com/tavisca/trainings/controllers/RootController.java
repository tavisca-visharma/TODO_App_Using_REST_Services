package com.tavisca.trainings.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

import com.tavisca.trainings.models.Response;
import com.tavisca.trainings.services.TodoService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(path = "todoapi/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class RootController {

	@Autowired
	TodoService todoService;

	@GetMapping
	public String name() {
		return "Welcome to REST API of TODO APP";
	}

	@GetMapping("/suggestions")
	public Response getSuggestions() {
		return todoService.getAllSuggestions();
	}

	@GetMapping("/todo")
	public Response getTodos() {
		return todoService.getAllTodo();
	}

	@PostMapping(path = "/suggestions")
	public Response addSuggestions(@Valid @RequestBody String jsonString) {
		System.out.println(jsonString + "##");
		return todoService.addNewSuggestion(jsonString);
	}

	@PostMapping(path = "/todo")
	public Response addTodo(@Valid @RequestBody String jsonString) {
		return todoService.addNewTodo(jsonString);
	}

	@PutMapping("/todo/{id}")
	public Response updateTodo(@PathVariable("id") int todoId, @Valid @RequestBody String jsonTodo) {
		return todoService.getUpdatedTodo(todoId, jsonTodo);
	}

	@DeleteMapping("/todo/{id}")
	public Response deleteTodo(@PathVariable("id") int todoId) {
		return todoService.getDeletedTodo(todoId);
	}
	
	@ExceptionHandler(Exception.class)
	public Response errorResponse(Exception e) {
		return todoService.getBadRequest();
	}

}
