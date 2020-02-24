package com.hadialaoui.notebookserverspringboot.factory;

import java.io.ByteArrayOutputStream;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.PolyglotException;
import org.springframework.stereotype.Component;

import com.hadialaoui.notebookserverspringboot.exceptions.technical.PythonInterpreterException;
import com.hadialaoui.notebookserverspringboot.models.InterpreterResult;

import lombok.Getter;
import lombok.Setter;

/**
 * The GraalVM interpreter implementation
 * @author ABDELHAKIMHADIALAOUI
 *
 */
@Getter
@Setter
@Component
public class GraalVMInterpreterImpl extends Interpreter{
	
	private Context interpreter;
	
	private ByteArrayOutputStream outputStream;
	
	public GraalVMInterpreterImpl() {
		this.outputStream = new ByteArrayOutputStream();
		this.interpreter = Context.newBuilder().out(this.outputStream).err(this.outputStream).build();
	}
	
	@Override
	public InterpreterResult execute(String code) {
		InterpreterResult result = new InterpreterResult();

		 try {
			 this.interpreter.eval(this.getName(), code);
	        } catch (PolyglotException e) {
	            throw new PythonInterpreterException(e);
	        } catch (IllegalArgumentException e) {
	            throw new PythonInterpreterException(e);
	        }
	        String outString = this.getOutputStream().toString();
	        this.getOutputStream().reset();
	        result.setResult(outString);
	        return result;
	}

	@Override
	public void clean() {
		//this.interpreter.;	
	}

	@Override
	public void close() {
		this.interpreter.close();;		
	}

	

}
