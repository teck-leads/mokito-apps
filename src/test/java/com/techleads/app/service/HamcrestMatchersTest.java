package com.techleads.app.service;

import static org.hamcrest.MatcherAssert.assertThat;
//import static org.junit.Assert.*;
import static org.hamcrest.Matchers.hasSize;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class HamcrestMatchersTest {

	@Test
	public final void test() {
		List<Integer> scores=Arrays.asList(99,100,101,105);
		assertThat(scores, hasSize(4));
	}

}
