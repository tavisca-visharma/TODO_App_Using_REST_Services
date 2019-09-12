package com.tavisca.trainings.dao;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tavisca.trainings.models.Todo;
import com.tavisca.trainings.repositories.TodoRepository;

@Component
public class TodoDAO {

	List<Todo> todoList;

	public TodoDAO() {
		todoList = new ArrayList<Todo>();
	}

	@Autowired
	TodoRepository todoRepository;

	public List<Todo> getAll() {
		return todoRepository.findAll();
	}

	public Todo add(Todo todo) {
		return todoRepository.save(todo);
	}

	public Todo delete(Todo todo) {
		todoRepository.delete(todo);
		return todo;
	}

	public Todo update(Todo todo) {
		return todoRepository.save(todo);
	}

	public Todo updateById(int id, Todo todo) {
		Todo updateTodo = todoRepository.findById(id).orElseThrow();
		updateTodo.setTimeStamp(todo.getTimeStamp());
		updateTodo.setContent(todo.getContent());
		updateTodo.setChecked(todo.getChecked());
		return todoRepository.save(updateTodo);
	}

	public Todo deleteById(int id) {
		Todo deletedTodo = todoRepository.findById(id).orElseThrow();
		todoRepository.deleteById(id);
		return deletedTodo;
	}

}
