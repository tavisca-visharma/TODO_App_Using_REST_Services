package com.tavisca.trainings.dao;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tavisca.trainings.exceptions.TodoAlreadyExistsException;
import com.tavisca.trainings.exceptions.TodoDoesNotExistsException;
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

	public Todo add(Todo todo) throws TodoAlreadyExistsException {
		if (todoRepository.findById(todo.getId()).isPresent())
			throw new TodoAlreadyExistsException();
		return todoRepository.save(todo);
	}

	public Todo delete(Todo todo) throws TodoDoesNotExistsException {
		todoRepository.findById(todo.getId()).orElseThrow(TodoDoesNotExistsException::new);
		todoRepository.delete(todo);
		return todo;
	}

	public Todo update(Todo todo) throws TodoDoesNotExistsException {
		todoRepository.findById(todo.getId()).orElseThrow(TodoDoesNotExistsException::new);
		return todoRepository.save(todo);
	}

	public Todo updateById(int id, Todo todo) throws TodoDoesNotExistsException {
		Todo updateTodo = todoRepository.findById(id).orElseThrow(TodoDoesNotExistsException::new);
		updateTodo.setTimeStamp(todo.getTimeStamp());
		updateTodo.setContent(todo.getContent());
		updateTodo.setChecked(todo.getChecked());
		return todoRepository.save(updateTodo);
	}

	public Todo deleteById(int id) throws TodoDoesNotExistsException {
		Todo deletedTodo = todoRepository.findById(id).orElseThrow(TodoDoesNotExistsException::new);
		todoRepository.deleteById(id);
		return deletedTodo;
	}

}
