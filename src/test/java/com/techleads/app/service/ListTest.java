package com.techleads.app.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Test;

public class ListTest {

	@Test
	public final void testSize() {
		List<String> listMock = mock(List.class);
		when(listMock.size()).thenReturn(2);
		assertEquals(2, listMock.size());
	}

	@Test
	public final void testListMultipleValues() {
		List<String> listMock = mock(List.class);
		when(listMock.size()).thenReturn(2).thenReturn(3).thenReturn(4);

		assertEquals(2, listMock.size());
		assertEquals(3, listMock.size());
		assertEquals(4, listMock.size());

	}

	@Test
	public final void testArgumentMachers() {
		List<String> listMock = mock(List.class);
		when(listMock.get(anyInt())).thenReturn("madhav");
		assertEquals("madhav", listMock.get(0));
		assertEquals("madhav", listMock.get(1));

	}
	
	@Test(expected = RuntimeException.class)
	public final void testArgumentMachersThrowException() {
		List<String> listMock = mock(List.class);
		when(listMock.get(anyInt())).thenThrow(new RuntimeException("Some exception"));
		assertEquals("madhav", listMock.get(0));
		assertEquals("madhav", listMock.get(1));

	}


}
