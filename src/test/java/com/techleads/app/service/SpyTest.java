package com.techleads.app.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.internal.stubbing.StubbedInvocationMatcher;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class SpyTest {
	
	@Rule
	public MockitoRule mockitoRule=MockitoJUnit.rule();

//	@Spy
//	List<String> listMock;
	@Test
	public final void test() {
		List<String> spy = spy(ArrayList.class);
//		when(spy.get(0)).thenReturn("madhav");
//		Mockito.stub(listMock.size()).toReturn(5);
		spy.add("test");
		assertEquals(1, spy.size());
		verify(spy).add("test");
		verify(spy, never()).clear();
//		verify(spy, never()).size();
		
	}

}
