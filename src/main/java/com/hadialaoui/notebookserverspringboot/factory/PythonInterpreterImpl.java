package com.hadialaoui.notebookserverspringboot.factory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.python.core.PyException;
import org.python.util.PythonInterpreter;
import org.springframework.stereotype.Component;

import com.hadialaoui.notebookserverspringboot.exceptions.technical.PythonInterpreterException;
import com.hadialaoui.notebookserverspringboot.models.InterpreterResult;

import lombok.Getter;
import lombok.Setter;

/**
 * The Python interpreter implementation
 * @author ABDELHAKIMHADIALAOUI
 *
 */
@Getter
@Setter
@Component
public class PythonInterpreterImpl extends Interpreter{
	
	private PythonInterpreter interpreter;
	private ByteArrayOutputStream outputStream;
	
	public PythonInterpreterImpl() {
		this.interpreter = new PythonInterpreter();
	    this.outputStream = new ByteArrayOutputStream();
	}
	
	@Override
	public synchronized InterpreterResult execute(String code) {
		InterpreterResult result = new InterpreterResult();
		

		try {
			interpreter.setOut(this.getOutputStream());
			interpreter.setErr(this.getOutputStream());
			interpreter.exec(code);
			this.getOutputStream().flush();
			result.setResult(this.getOutputStream().toString());
			this.getOutputStream().reset();
			
		} catch (PyException | IOException e) {
			clean();
			throw new PythonInterpreterException(e);
		} catch (Exception e) {
			clean();
			throw new PythonInterpreterException(e);
		}
		return result;
	}

	@Override
	public void clean() {
		interpreter.cleanup();		
	}

	@Override
	public void close() {
		interpreter.close();		
	}

	

}
