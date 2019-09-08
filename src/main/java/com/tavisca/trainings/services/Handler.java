package com.tavisca.trainings.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.tavisca.trainings.models.Suggestion;
import com.tavisca.trainings.models.Todo;

public class Handler {

	List<Todo> todoList;
	List<Suggestion> suggestions;

	public Handler() {
		todoList = new ArrayList<Todo>();
		suggestions = new ArrayList<Suggestion>();
		addInitialSuggestions();
		addInitialTodos();
	}

	public void addSuggention(String content) {
		suggestions.add(new Suggestion(content));
	}

	public void addInitialTodos() {
		todoList.add(new Todo(new Date().toString(), "Hello", false));
		todoList.add(new Todo(new Date().toString(), "Vishal", false));
		todoList.add(new Todo(new Date().toString(), "Sharma", true));
	}

	public void addInitialSuggestions() {
		suggestions.add(new Suggestion("First Suggestion"));
		suggestions.add(new Suggestion("Second Suggestion"));
		suggestions.add(new Suggestion("Third Suggestion"));
	}

	public String getAllTodo() {
		return new ObjectToJsonStringConverter().toJson(todoList);
	}

	public String getAllSuggestions() {
		return new ObjectToJsonStringConverter().toJson(suggestions);
	}

	public void addTodo(String content) {
		todoList.add(new Todo(new Date().toString(), content, false));
	}

	public String getNewlyAddedTodo() {
		return new ObjectToJsonStringConverter().toJson(todoList.get(todoList.size() - 1));
	}

	public String getUpdatedTodo(int todoId, String content, String todoCheckStatus) {
		return new ObjectToJsonStringConverter().toJson(updateTodoWithId(todoId, content, todoCheckStatus));
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

	public String getDeletedTodo(int todoId) {
		return new ObjectToJsonStringConverter().toJson(deleteTodoWithId(todoId));
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

}
