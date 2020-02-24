package com.hadialaoui.notebookserverspringboot.factory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.hadialaoui.notebookserverspringboot.exceptions.technical.UnknownInterpreterException;

/**
 * Interpreter factory to build different interpreters
 * @author ABDELHAKIMHADIALAOUI
 *
 */
@Component
public class InterpreterFactory {

	@Value("${graalVM.interpreter.enable}")
	public boolean isGraalVMEnable;
	
	/**
	 * Method to create instance of interpreter if it not exist throw an  
	 * {@link UnknownInterpreterException}
	 * @param nameInterpreter
	 * @return interpreter wanted
	 */
	public Interpreter createInterpreterInstance(String nameInterpreter) {
		Interpreter interpreter = null;
		if(null == nameInterpreter) {
			throw new UnknownInterpreterException();
		}
		InterpreterName interpreterName = null;
		try {
			interpreterName = InterpreterName.valueOf(nameInterpreter.toUpperCase());
			
			switch (interpreterName) {
			case PYTHON:
				if(isGraalVMEnable) {
					interpreter = new GraalVMInterpreterImpl();
					interpreter.setName("python");
				}else {
					interpreter = new PythonInterpreterImpl();
				}
				
				break;
			}
		} catch (Exception e) {
			throw new UnknownInterpreterException();
		}
		return interpreter;
	}
	
}
