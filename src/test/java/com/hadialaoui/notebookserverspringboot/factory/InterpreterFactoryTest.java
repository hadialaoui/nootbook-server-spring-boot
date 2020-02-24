package com.hadialaoui.notebookserverspringboot.factory;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class InterpreterFactoryTest {

	private InterpreterFactory factory;
	
	@Before
	public void setup() {
		factory = new InterpreterFactory();
	}
	@Test
	public void testCreateInterpreterInstance() {
		assertNotNull(factory.createInterpreterInstance("python"));
	}

}
