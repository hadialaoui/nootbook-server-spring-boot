package com.hadialaoui.notebookserverspringboot.factory;

/**
 * Enum represent the group of the available interpreters  
 * @author ABDELHAKIMHADIALAOUI
 *
 */
public enum InterpreterName {

	PYTHON("python");
	
	private String code;
	
	private InterpreterName(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
	
}
