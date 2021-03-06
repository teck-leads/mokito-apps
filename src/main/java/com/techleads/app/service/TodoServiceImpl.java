package com.techleads.app.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TodoServiceImpl {

	private TodoService todoService;

	public TodoServiceImpl(TodoService todoService) {
		this.todoService = todoService;
	}

	public List<String> retrieveTodosRelatedToSpring(String user) {
		List<String> filteredTodos = new ArrayList<>();
		List<String> todos = todoService.retrieveTodos(user);

		for (String todo : todos) {
			if (todo.contains("Spring")) {
				filteredTodos.add(todo);
			}
		}
		return filteredTodos;
	}
	
	public void deleteTodosNotRelatedToSpring(String user) {
		List<String> todos = todoService.retrieveTodos(user);
		Iterator<String> itr = todos.iterator();
		while(itr.hasNext()) {
			String todo = itr.next();
			if(!todo.contains("Spring")) {
				todoService.deleteTodo(todo);
			}
		}
		
	}

}
