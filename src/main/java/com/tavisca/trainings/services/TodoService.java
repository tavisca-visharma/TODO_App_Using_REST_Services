package com.tavisca.trainings.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.tavisca.trainings.exceptions.BooleanParseException;
import com.tavisca.trainings.models.Response;
import com.tavisca.trainings.models.Suggestion;
import com.tavisca.trainings.models.Todo;

@Service
public class TodoService {

	List<Todo> todoList;
	List<Suggestion> suggestions;
	JSONObject jsonObject = null;

	public TodoService() {
		todoList = new ArrayList<Todo>();
		suggestions = new ArrayList<Suggestion>();
		addInitialSuggestions();
		addInitialTodos();
	}

	public void addInitialTodos() {
		todoList.add(new Todo(new Date().toString(), "Go for Lunch @ 1:30 PM", false));
		todoList.add(new Todo(new Date().toString(), "Go for Dinner @ 7:30 PM", false));
		todoList.add(new Todo(new Date().toString(), "Complete TODO APP using Rest Api", true));
	}

	public void addInitialSuggestions() {
		suggestions.add(new Suggestion("First Suggestion"));
		suggestions.add(new Suggestion("Second Suggestion"));
		suggestions.add(new Suggestion("Third Suggestion"));
	}

	public void addSuggention(String content) throws NullPointerException {
		if (content.isBlank() || content.isEmpty() || content == null || content.equals("null"))
			throw new NullPointerException("Suggestion Content Cannot be Blank or Null");
		suggestions.add(new Suggestion(content));
	}

	public Response getAllSuggestions() {
		return sendResponse(HttpStatus.OK, "All Suggestions Are Returned", suggestions);
	}

	public Response getAllTodo() {
		return sendResponse(HttpStatus.OK, "All Todos Are Returned", todoList);
	}

	public void addTodo(String content, String todoCheckStatus) throws BooleanParseException {
		if (content.isBlank() || content.isEmpty() || content == null || content.equals("null"))
			throw new NullPointerException("Todo Task Content Cannot be Blank or Null");
		else if (todoCheckStatus.equalsIgnoreCase("true") || todoCheckStatus.equalsIgnoreCase("false"))
			todoList.add(new Todo(new Date().toString(), content, Boolean.parseBoolean(todoCheckStatus)));
		else
			throw new BooleanParseException("Passed argument should be \'Boolean\' but found \'String\'");
	}

	public Response getNewlyAddedTodo() {
		return sendResponse(HttpStatus.OK, "Added Todo Successfully", todoList.get(todoList.size() - 1));
	}

	public Response getUpdatedTodo(int todoId, String content, String todoCheckStatus) {
		Todo updatedTodo = updateTodoWithId(todoId, content, todoCheckStatus);
		return sendResponse(HttpStatus.CREATED, "Updated Todo Successfully", updatedTodo);
	}

	private Todo updateTodoWithId(int todoId, String content, String todoCheckStatus) {

		for (int i = 0; i < todoList.size(); i++) {
			if (todoList.get(i).getId() == todoId) {
				todoList.get(i).setContent(content);
				if (!todoCheckStatus.equals("")) {
					todoList.get(i).setChecked(Boolean.parseBoolean(todoCheckStatus));
				}
				return todoList.get(i);
			}
		}
		return null;
	}

	public Response getDeletedTodo(int todoId) {
		Todo deletedTodo = deleteTodoWithId(todoId);
		return sendResponse(HttpStatus.OK, "Deleted Todo Successfully", deletedTodo);
	}

	private Todo deleteTodoWithId(int todoId) {

		Todo deletedTodo = null;
		for (int i = 0; i < todoList.size(); i++) {
			if (todoList.get(i).getId() == todoId) {
				deletedTodo = todoList.get(i);
				todoList.remove(i);
			}
		}
		return deletedTodo;
	}

	public Response getUpdatedTodo(int todoId, String jsonTodo) {
		String todoContent = null;
		String todoCheckStatus = null;
		try {
			jsonObject = new JSONObject(jsonTodo);
			todoContent = jsonObject.getString("content");
			todoCheckStatus = jsonObject.getString("checked");

		} catch (JSONException e) {
			e.printStackTrace();
		}
		return getUpdatedTodo(todoId, todoContent, todoCheckStatus);
	}

	public Response addNewTodo(String jsonString) {
		try {
			jsonObject = new JSONObject(jsonString);

			String todoContent = jsonObject.getString("content");
			String todoCheckStatus = jsonObject.getString("checked");
			this.addTodo(todoContent, todoCheckStatus);

		} catch (JSONException e) {
			e.printStackTrace();
			return sendResponse(HttpStatus.BAD_REQUEST, "Bad Request", jsonString);
		} catch (BooleanParseException e) {
			e.printStackTrace();
			return sendResponse(HttpStatus.BAD_REQUEST, e.getMessage(), jsonString);
		} catch (NullPointerException e) {
			return sendResponse(HttpStatus.BAD_REQUEST, e.getMessage(), jsonString);
		}

		return getNewlyAddedTodo();

	}

	public Response addNewSuggestion(String jsonString) {
		try {
			System.out.println(jsonString + "------------");
			if(jsonString.equals("") || jsonString.equals("null") || jsonString == null)
				throw new NullPointerException("Passed Json Cannot be Null or Empty");
			jsonObject = new JSONObject(jsonString);

			this.addSuggention(jsonObject.getString("content"));

		} catch (JSONException e) {
			e.printStackTrace();
			return sendResponse(HttpStatus.BAD_REQUEST, "Bad Request", jsonString);
		}catch (NullPointerException e) {
			return sendResponse(HttpStatus.BAD_REQUEST, e.getMessage(), jsonString);

		}

		return this.getNewlyAddedSuggestion();

	}

	public Response getNewlyAddedSuggestion() {
		return sendResponse(HttpStatus.OK, "Suggestion Added Succesfully", suggestions.get(suggestions.size() - 1));
	}

	public Response sendResponse(HttpStatus statusCode, String message, Object data) {

		Response response = Response.builder().statusCode(statusCode.toString()).message(message).data(data).build();
		return response;
	}

	public Response getBadRequest() {
		return sendResponse(HttpStatus.BAD_REQUEST, "Bad Request", "Error");
	}

}
