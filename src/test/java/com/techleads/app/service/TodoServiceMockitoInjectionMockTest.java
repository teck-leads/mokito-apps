package com.techleads.app.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TodoServiceMockitoInjectionMockTest {
	@Mock
	private TodoService mockTodoService;
	@InjectMocks
	TodoServiceImpl todoServiceImpl;
	@Captor
	private ArgumentCaptor<String> stringArgumentCaptor;

	@Test
	public final void testMockRetrieveTodos() {

		when(mockTodoService.retrieveTodos("madhav"))
				.thenReturn(Arrays.asList("Spring", "Spring MVC", "Springboot", "Microservices", "Devops"));
//		TodoServiceImpl todoServiceImpl = new TodoServiceImpl(mockTodoService);
		List<String> relatedToSpring = todoServiceImpl.retrieveTodosRelatedToSpring("madhav");
		assertEquals(3, relatedToSpring.size());

	}

	@Test
	public final void testMockDeleteTodos() {

		when(mockTodoService.retrieveTodos("madhav"))
				.thenReturn(Arrays.asList("Rest API", "Spring", "Spring MVC", "Springboot", "Microservices", "Devops"));
		todoServiceImpl.deleteTodosNotRelatedToSpring("madhav");
		verify(mockTodoService, times(1)).deleteTodo("Microservices");
		verify(mockTodoService, atLeastOnce()).deleteTodo("Microservices");
		verify(mockTodoService, atLeast(1)).deleteTodo("Rest API");
		verify(mockTodoService, times(1)).deleteTodo("Devops");
		verify(mockTodoService, never()).deleteTodo("Spring MVC");
		verify(mockTodoService, never()).deleteTodo("Spring");
		verify(mockTodoService, never()).deleteTodo("Springboot");

	}

	@Test
	public final void testMockRetrieveTodos_EmptyList() {

		when(mockTodoService.retrieveTodos("madhav")).thenReturn(Arrays.asList());
		List<String> relatedToSpring = todoServiceImpl.retrieveTodosRelatedToSpring("madhav");
		assertEquals(0, relatedToSpring.size());

	}

	@Test
	public final void testMockDeleteTodos_ArgumentCapture() {
		ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);

		when(mockTodoService.retrieveTodos("madhav"))
				.thenReturn(Arrays.asList("Spring", "Spring MVC", "Springboot", "Devops"));
		todoServiceImpl.deleteTodosNotRelatedToSpring("madhav");

		verify(mockTodoService).deleteTodo(stringArgumentCaptor.capture());
		assertEquals("Devops", stringArgumentCaptor.getValue());

	}

	@Test
	public final void testMockDeleteTodos_ArgumentCaptureMultiple() {
//		ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);

		when(mockTodoService.retrieveTodos("madhav"))
				.thenReturn(Arrays.asList("Spring", "Spring MVC", "Springboot", "Devops", "Microservices"));
		todoServiceImpl.deleteTodosNotRelatedToSpring("madhav");

		verify(mockTodoService, times(2)).deleteTodo(stringArgumentCaptor.capture());
		assertEquals(Arrays.asList("Devops", "Microservices"), stringArgumentCaptor.getAllValues());

	}

}
