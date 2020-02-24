package com.hadialaoui.notebookserverspringboot.factory;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PythonInterpreterTest {

	private Interpreter interpreter;
	
	@Before
	public void setup() {
		interpreter = new PythonInterpreterImpl();
	}
	@Test
	public void testExecute() {
		assertEquals("", interpreter.execute("a=4").getResult());
		assertEquals("12\r\n", interpreter.execute("print a+8").getResult());
	}

}
