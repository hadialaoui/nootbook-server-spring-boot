package com.hadialaoui.notebookserverspringboot.factory;

import java.time.LocalDateTime;

import com.hadialaoui.notebookserverspringboot.models.InterpreterResult;

import lombok.Getter;
import lombok.Setter;

/**
 * This is an abstraction of different Interpreter handler
 * @author ABDELHAKIMHADIALAOUI
 * 
 */
@Getter
@Setter
public abstract class Interpreter {
	
	/**
	 * This field represent the last time when interpreter used
	 * to determine expired sessions to remove their interpreters from the store
	 */
	private LocalDateTime updateTime;

	/**
	 * Method to execute code by this interpreter
	 * @param interpreterInput 
	 * @return <b>InterpreterResult</b> : the result of the execution
	 */
	public abstract InterpreterResult execute(String code);
	
	/**
	 * Method to clean up the interpreter context 
	 */
	public abstract void clean();
	
	/**
	 * Method to close the interpreter
	 */
	public abstract void close();
}
