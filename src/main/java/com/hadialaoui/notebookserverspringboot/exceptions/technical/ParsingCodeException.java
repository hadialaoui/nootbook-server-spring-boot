package com.hadialaoui.notebookserverspringboot.exceptions.technical;

import com.hadialaoui.notebookserverspringboot.exceptions.ReturnCode;

public class ParsingCodeException extends TechnicalException {

	

	public ParsingCodeException() {
		super(ReturnCode.ERROR_PARSING_CODE);
		super.setDescreption("The code should formatted like this: %<interpreter-name><whitespace><code> ");
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -2285187744510568741L;

}
