package com.tavisca.trainings.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tavisca.trainings.dao.SuggestionDAO;
import com.tavisca.trainings.dao.TodoDAO;
import com.tavisca.trainings.exceptions.SuggestionAlreadyExistsException;
import com.tavisca.trainings.exceptions.TodoAlreadyExistsException;
import com.tavisca.trainings.exceptions.TodoDoesNotExistsException;
import com.tavisca.trainings.models.Response;
import com.tavisca.trainings.models.Suggestion;
import com.tavisca.trainings.models.Todo;

@Service
public class TodoService {

	@Autowired
	SuggestionDAO suggestionDAO;

	@Autowired
	TodoDAO todoDAO;

	@Autowired
	Response response;

	public Response getAllTodo() {
		return response.ok("All Todos Returned Successfully", todoDAO.getAll());
	}

	public Response getAllSuggestions() {
		return response.ok("All Suggestions Returned Successfully", suggestionDAO.getAll());
	}

	public Response addSuggestion(Suggestion suggestion) throws SuggestionAlreadyExistsException {
		return response.created("Suggestion Added Successfully", suggestionDAO.add(suggestion));
	}

	public Response addTodo(Todo todo) throws TodoAlreadyExistsException {
		return response.created("Todo Added Successfully", todoDAO.add(todo));
	}

	public Response updateTodo(long todoId, Todo todo) throws TodoDoesNotExistsException {
		return response.ok("Todo Updated Successfully", todoDAO.updateById(todoId, todo));
	}

	public Response deleteTodo(long todoid) throws TodoDoesNotExistsException {
		return response.deleted("Todo Deleted Successfully", todoDAO.deleteById(todoid));
	}

}