package com.hadialaoui.notebookserverspringboot.exceptions.technical;

import com.hadialaoui.notebookserverspringboot.exceptions.ReturnCode;

public class PythonInterpreterException extends TechnicalException {

	

	public PythonInterpreterException(Exception exception) {
		super(ReturnCode.ERROR_PYTHON_PROCESS);
		super.setDescreption(exception.getMessage());
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -2285187744510568741L;

}
