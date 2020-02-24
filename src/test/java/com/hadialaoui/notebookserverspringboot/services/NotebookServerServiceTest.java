package com.hadialaoui.notebookserverspringboot.services;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.hadialaoui.notebookserverspringboot.models.UserRequest;
import com.hadialaoui.notebookserverspringboot.services.impl.NotebookServerServiceImpl;

public class NotebookServerServiceTest {

	NotebookServerService service;
	
	@Before
	public void setup() {
		 service = new NotebookServerServiceImpl();
	}
	@Test
	public void testParseInterpreterInput(){
		UserRequest userRequest = new UserRequest();
		userRequest.setCode("%python print x");
		assertEquals("python", service.parseInterpreterInput(userRequest).getInterpreterName());
		assertEquals("print x", service.parseInterpreterInput(userRequest).getCode());
	}
	
}
