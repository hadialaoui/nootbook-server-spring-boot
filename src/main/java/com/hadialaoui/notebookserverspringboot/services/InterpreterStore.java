package com.hadialaoui.notebookserverspringboot.services;

import com.hadialaoui.notebookserverspringboot.factory.Interpreter;

/**
 * InterpreterStore is an interface to manage sessions
 * @author ABDELHAKIMHADIALAOUI
 *
 */
public interface InterpreterStore {

	 /**
	  * Method to retrieve the interpreter dedicated to a session from the store
	 * @param typeInterpreter
	 * @param sessionId
	 * @return
	 */
	Interpreter getInterpreterContext(String typeInterpreter, String sessionId);
	 /**
	  * Method to rest the interpreter dedicated to a session
	 * @param typeInterpreter
	 * @param sessionId
	 * @return
	 */
	Interpreter restInterpreterContext(String typeInterpreter, String sessionId);
	 /**
	 * Method to destroy expired sessions 
	 */
	void destroyExpiredSessions();
}
