package com.hadialaoui.notebookserverspringboot.services;

import com.hadialaoui.notebookserverspringboot.models.InterpreterInput;
import com.hadialaoui.notebookserverspringboot.models.InterpreterResult;
import com.hadialaoui.notebookserverspringboot.models.UserRequest;

/**
 * Notebook business interface, providing main services for interpreting requests 
 * @author ABDELHAKIMHADIALAOUI
 *
 */
public interface NotebookServerService {

	/**
	 * @param input
	 * @return
	 */
	InterpreterInput parseInterpreterInput(UserRequest input);
	/**
	 * @param inputContent
	 * @return
	 */
	InterpreterResult execute(InterpreterInput interpreterInput);
	
	/**
	 * @return
	 */
	boolean sessionModeIsEnable();
}
