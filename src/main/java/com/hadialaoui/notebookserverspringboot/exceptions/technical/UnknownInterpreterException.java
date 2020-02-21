package com.hadialaoui.notebookserverspringboot.exceptions.technical;

import com.hadialaoui.notebookserverspringboot.exceptions.ReturnCode;

public class UnknownInterpreterException extends TechnicalException {

	

	public UnknownInterpreterException() {
		super(ReturnCode.UNKNOWN_INTERPRETER);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -2285187744510568741L;

}
