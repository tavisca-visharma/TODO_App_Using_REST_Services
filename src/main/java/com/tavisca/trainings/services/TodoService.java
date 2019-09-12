package com.tavisca.trainings.services;

import java.util.Date;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.tavisca.trainings.dao.SuggestionDAO;
import com.tavisca.trainings.dao.TodoDAO;
import com.tavisca.trainings.exceptions.BooleanParseException;
import com.tavisca.trainings.models.Response;
import com.tavisca.trainings.models.Suggestion;
import com.tavisca.trainings.models.Todo;
import com.tavisca.trainings.repositories.TodoRepository;

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
	
	public Response addSuggestion(Suggestion suggestion) {
		return response.ok("Suggestion Added Successfully", suggestionDAO.add(suggestion));
	}
	
	public Response addTodo(Todo todo) {
		return response.ok("Todo Added Successfully", todoDAO.add(todo));
	}
	
	public Response updateTodo(int todoId, Todo todo) {
		return response.ok("Todo Updated Successfully", todoDAO.updateById(todoId,todo));
	}
	
	public Response deleteTodo(int todoid) {
		return response.ok("Todo Deleted Successfully", todoDAO.deleteById(todoid));
	}
	
}
















	/*JSONObject jsonObject = null;


	public Response addSuggention(String content) throws NullPointerException {
		if (content.isBlank() || content.isEmpty() || content == null || content.equals("null"))
			throw new NullPointerException("Suggestion Content Cannot be Blank or Null");
		Suggestion newSuggestion = Suggestion.builder().content(content).build();
		suggestionDAO.add(newSuggestion);
		return sendResponse(HttpStatus.OK, "Added Suggestion Successfully", newSuggestion);
	}

	public Response getAllSuggestions() {
		return sendResponse(HttpStatus.OK, "All Suggestions Are Returned", suggestionDAO.getAll());
	}

	public Response getAllTodo() {
		return sendResponse(HttpStatus.OK, "All Todos Are Returned", todoDAO.getAll());
	}

	public Response addTodo(String content, String todoCheckStatus) throws BooleanParseException {
		Todo newTodo;
		if (content.isBlank() || content.isEmpty() || content == null || content.equals("null"))
			throw new NullPointerException("Todo Task Content Cannot be Blank or Null");
		else if (todoCheckStatus.equalsIgnoreCase("true") || todoCheckStatus.equalsIgnoreCase("false")) {
			newTodo = new Todo(new Date().toString(), content, Boolean.parseBoolean(todoCheckStatus));
			todoDAO.add(newTodo);
		}
		else
			throw new BooleanParseException("Passed argument should be \'Boolean\' but found \'String\'");
		return sendResponse(HttpStatus.OK, "Added Todo Successfully", newTodo);
	}

	public Response getUpdatedTodo(int todoId, String content, String todoCheckStatus) {
		Todo updatedTodo = updateTodoWithId(todoId, content, todoCheckStatus);
		return sendResponse(HttpStatus.CREATED, "Updated Todo Successfully", updatedTodo);
	}

	private Todo updateTodoWithId(int todoId, String content, String todoCheckStatus) {
		
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
			return this.addTodo(todoContent, todoCheckStatus);

		} catch (JSONException e) {
			e.printStackTrace();
			return sendResponse(HttpStatus.BAD_REQUEST, "Bad Request", jsonString);
		} catch (BooleanParseException e) {
			e.printStackTrace();
			return sendResponse(HttpStatus.BAD_REQUEST, e.getMessage(), jsonString);
		} catch (NullPointerException e) {
			return sendResponse(HttpStatus.BAD_REQUEST, e.getMessage(), jsonString);
		}
	}

	public Response addNewSuggestion(String jsonString) {
		try {
			System.out.println(jsonString + "------------");
			if(jsonString.equals("") || jsonString.equals("null") || jsonString == null)
				throw new NullPointerException("Passed Json Cannot be Null or Empty");
			jsonObject = new JSONObject(jsonString);

			return this.addSuggention(jsonObject.getString("content"));

		} catch (JSONException e) {
			e.printStackTrace();
			return sendResponse(HttpStatus.BAD_REQUEST, "Bad Request", jsonString);
		}catch (NullPointerException e) {
			return sendResponse(HttpStatus.BAD_REQUEST, e.getMessage(), jsonString);

		}

	}


	public Response sendResponse(HttpStatus statusCode, String message, Object data) {

		Response response = Response.builder().statusCode(statusCode.toString()).message(message).data(data).build();
		return response;
	}


}
*/