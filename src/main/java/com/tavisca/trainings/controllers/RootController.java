package com.tavisca.trainings.controllers;

import org.json.JSONException;
import org.json.JSONObject;
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
		return new ResponseEntity<>(handler.getAllSuggestions(),HttpStatus.OK);
	}
	
	@GetMapping("/todo")
	public ResponseEntity<?> getTodos() {
		return new ResponseEntity<>(handler.getAllTodo(),HttpStatus.OK);
	}
	
//	public List<Todo> getAllTodo(){
//		//TODO: Add get Method Implementation.
//	}
	
	@PostMapping(path = "/suggestions")
	public ResponseEntity<?> addSuggestions(@RequestBody String jsonString) {
		
		JSONObject jsonObject = null;
		try {
			jsonObject = new JSONObject(jsonString);
		
		handler.addSuggention(jsonObject.getString("content"));
		
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new ResponseEntity<>(handler.getAllSuggestions(),HttpStatus.OK);
		
		
	}
	
	@PostMapping(path = "/todo")
	public ResponseEntity<?> addTodo(@RequestBody String jsonString) {
		
		JSONObject jsonObject = null;
		try {
			jsonObject = new JSONObject(jsonString);
		
		System.out.println(jsonObject);
		handler.addTodo(jsonObject.getString("content"));
		
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new ResponseEntity<>(handler.getNewlyAddedTodo(),HttpStatus.OK);
		
		
	}

	@PutMapping("/todo/{id}")
	public ResponseEntity<?> updateTodo(@PathVariable("id") int todoId, @RequestBody String jsonTodo) {
		
		JSONObject jsonObject = null;
		String todoContent = null;
		String todoCheckStatus = null;
		try {
			jsonObject = new JSONObject(jsonTodo);
			System.out.println(jsonObject);
			todoContent = jsonObject.getString("content");
			todoCheckStatus = jsonObject.getString("checked");
		
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<>(handler.getUpdatedTodo(todoId,todoContent,todoCheckStatus),HttpStatus.OK);
		
	}
	
	
	@DeleteMapping("/todo/{id}")
	public ResponseEntity<?> deleteTodo(@PathVariable("id") int todoId) {
		return new ResponseEntity<>(handler.getDeletedTodo(todoId),HttpStatus.OK);
		
	}
	
	
}
