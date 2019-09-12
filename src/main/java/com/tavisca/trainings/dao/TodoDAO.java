package com.tavisca.trainings.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tavisca.trainings.exceptions.TodoAlreadyExistsException;
import com.tavisca.trainings.exceptions.TodoDoesNotExistsException;
import com.tavisca.trainings.models.Todo;
import com.tavisca.trainings.repositories.TodoRepository;
import com.tavisca.trainings.services.SequenceGeneratorService;

@Component
public class TodoDAO {

	List<Todo> todoList;

	public TodoDAO() {
		todoList = new ArrayList<Todo>();
	}

	@Autowired
	TodoRepository todoRepository;

	@Autowired
	SequenceGeneratorService sequenceGeneratorService;

	public List<Todo> getAll() {
		return todoRepository.findAll();
	}

	public Todo add(Todo todo) throws TodoAlreadyExistsException {
		todo.setId(sequenceGeneratorService.generateSequence(Todo.SEQUENCE_NAME));
		todo.setTimeStamp(new Date().toString());
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

	public Todo updateById(long id, Todo todo) throws TodoDoesNotExistsException {
		Todo updateTodo = todoRepository.findById(id).orElseThrow(TodoDoesNotExistsException::new);
		updateTodo.setTimeStamp(todo.getTimeStamp());
		updateTodo.setContent(todo.getContent());
		updateTodo.setChecked(todo.getChecked());
		return todoRepository.save(updateTodo);
	}

	public Todo deleteById(long id) throws TodoDoesNotExistsException {
		Todo deletedTodo = todoRepository.findById(id).orElseThrow(TodoDoesNotExistsException::new);
		todoRepository.deleteById(id);
		return deletedTodo;
	}

}
