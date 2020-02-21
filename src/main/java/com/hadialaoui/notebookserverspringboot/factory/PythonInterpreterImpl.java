package com.hadialaoui.notebookserverspringboot.factory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.python.core.PyException;
import org.python.util.PythonInterpreter;
import org.springframework.scheduling.annotation.Async;
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
	
	private PythonInterpreter interpreter = new PythonInterpreter();
	
	
	@Override
	public InterpreterResult execute(String code) {
		InterpreterResult result = new InterpreterResult();
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		try {
			interpreter.setOut(out);
			interpreter.setErr(out);
			interpreter.exec(code);
			out.flush();
			result.setResult(out.toString());
			
		} catch (PyException | IOException e) {
			e.printStackTrace();
			throw new PythonInterpreterException(e);

		} catch (Exception e) {
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
