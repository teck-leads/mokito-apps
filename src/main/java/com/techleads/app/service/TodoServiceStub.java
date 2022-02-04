package com.techleads.app.service;

import java.util.Arrays;
import java.util.List;

public class TodoServiceStub implements TodoService {

	@Override
	public List<String> retrieveTodos(String user) {
		return  Arrays.asList("Spring", "Spring MVC", "Springboot", "Microservices","Devops");
	}

	@Override
	public void deleteTodo(String todo) {
		
	}

}
