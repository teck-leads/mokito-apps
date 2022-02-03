package com.techleads.app.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class TodoServiceStubTest {

	@Test
	public final void testRetrieveTodosWithStaticStub() {

		TodoService todoServiceStub = new TodoServiceStub();
		TodoServiceImpl todoServiceImpl = new TodoServiceImpl(todoServiceStub);
		
		List<String> relatedToSpring = todoServiceImpl.retrieveTodosRelatedToSpring("madhav");
		assertEquals(3, relatedToSpring.size());
		
	}
	@Test
	public final void testMockRetrieveTodos() {

		TodoService mockTodoService = mock(TodoService.class);
		when(mockTodoService.retrieveTodos("madhav")).thenReturn(Arrays.asList("Spring", "Spring MVC", "Springboot", "Microservices","Devops"));
		TodoServiceImpl todoServiceImpl = new TodoServiceImpl(mockTodoService);
		List<String> relatedToSpring = todoServiceImpl.retrieveTodosRelatedToSpring("madhav");
		assertEquals(3, relatedToSpring.size());
		
	}
	
	@Test
	public final void testMockRetrieveTodos_EmptyList() {

		TodoService mockTodoService = mock(TodoService.class);
		when(mockTodoService.retrieveTodos("madhav")).thenReturn(Arrays.asList());
		TodoServiceImpl todoServiceImpl = new TodoServiceImpl(mockTodoService);
		List<String> relatedToSpring = todoServiceImpl.retrieveTodosRelatedToSpring("madhav");
		assertEquals(0, relatedToSpring.size());
		
	}

}
