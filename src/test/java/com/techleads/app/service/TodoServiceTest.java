package com.techleads.app.service;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

public class TodoServiceTest {
	
	

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
	public final void testMockDeleteTodos() {
		

		TodoService mockTodoService = mock(TodoService.class);
		when(mockTodoService.retrieveTodos("madhav")).thenReturn(Arrays.asList("Rest API", "Spring", "Spring MVC", "Springboot", "Microservices","Devops"));
		TodoServiceImpl todoServiceImpl = new TodoServiceImpl(mockTodoService);
		todoServiceImpl.deleteTodosNotRelatedToSpring("madhav");
		verify(mockTodoService, times(1)).deleteTodo("Microservices");
		verify(mockTodoService, atLeastOnce()).deleteTodo("Microservices");
		verify(mockTodoService, atLeast(1)).deleteTodo("Rest API");
		verify(mockTodoService,times(1)).deleteTodo("Devops");
		verify(mockTodoService, never()).deleteTodo("Spring MVC");
		verify(mockTodoService, never()).deleteTodo("Spring");
		verify(mockTodoService, never()).deleteTodo("Springboot");
		
		
	
		
	}
	
	@Test
	public final void testMockRetrieveTodos_EmptyList() {

		TodoService mockTodoService = mock(TodoService.class);
		when(mockTodoService.retrieveTodos("madhav")).thenReturn(Arrays.asList());
		TodoServiceImpl todoServiceImpl = new TodoServiceImpl(mockTodoService);
		List<String> relatedToSpring = todoServiceImpl.retrieveTodosRelatedToSpring("madhav");
		assertEquals(0, relatedToSpring.size());
		
	}
	
	
	@Test
	public final void testMockDeleteTodos_ArgumentCapture() {
		ArgumentCaptor<String> stringArgumentCaptor=ArgumentCaptor.forClass(String.class);

		TodoService mockTodoService = mock(TodoService.class);
		when(mockTodoService.retrieveTodos("madhav")).thenReturn(Arrays.asList("Spring", "Spring MVC", "Springboot", "Devops"));
		TodoServiceImpl todoServiceImpl = new TodoServiceImpl(mockTodoService);
		todoServiceImpl.deleteTodosNotRelatedToSpring("madhav");
		
		verify(mockTodoService).deleteTodo(stringArgumentCaptor.capture());
		assertEquals("Devops", stringArgumentCaptor.getValue());
	
		
	}
	
	
	@Test
	public final void testMockDeleteTodos_ArgumentCaptureMultiple() {
		ArgumentCaptor<String> stringArgumentCaptor=ArgumentCaptor.forClass(String.class);

		TodoService mockTodoService = mock(TodoService.class);
		when(mockTodoService.retrieveTodos("madhav")).thenReturn(Arrays.asList("Spring", "Spring MVC", "Springboot", "Devops", "Microservices"));
		TodoServiceImpl todoServiceImpl = new TodoServiceImpl(mockTodoService);
		todoServiceImpl.deleteTodosNotRelatedToSpring("madhav");
		
		verify(mockTodoService, times(2)).deleteTodo(stringArgumentCaptor.capture());
		assertEquals(Arrays.asList("Devops","Microservices"), stringArgumentCaptor.getAllValues());
	
		
	}

}
