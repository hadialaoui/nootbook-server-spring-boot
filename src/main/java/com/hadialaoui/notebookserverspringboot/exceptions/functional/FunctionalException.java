package com.hadialaoui.notebookserverspringboot.exceptions.functional;

import com.hadialaoui.notebookserverspringboot.exceptions.ReturnCode;

import lombok.Getter;
import lombok.Setter;

/**
 * @author ABDELHAKIMHADIALAOUI
 *
 */
@Getter
@Setter
public class FunctionalException extends Exception {

	private static final long serialVersionUID = -9138869999944552443L;
	private String code;
	   private String message;
	   private String descreption;
	   private int httpStatus;
	
	 /**
	 * @param returnCode
	 */
	public FunctionalException(ReturnCode returnCode) {
		super();
		this.code = returnCode.getCode();
		this.message = returnCode.getMessage();
		this.descreption = super.getMessage();
		this.httpStatus = returnCode.getHttpStatus();
	}
	
	   
	   
}
