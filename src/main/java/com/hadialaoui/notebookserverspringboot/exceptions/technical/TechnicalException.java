package com.hadialaoui.notebookserverspringboot.exceptions.technical;

import com.hadialaoui.notebookserverspringboot.exceptions.ReturnCode;

import lombok.Getter;
import lombok.Setter;

/**
 * Functional exception
 * @author ABDELHAKIMHADIALAOUI
 *
 */
@Getter
@Setter
public class TechnicalException extends RuntimeException{
	 /**
	 * 
	 */
	private static final long serialVersionUID = 4468724278129364252L;
	private String code;
    private String message;
    private String descreption;
    private int httpStatus;
	   
    public TechnicalException(ReturnCode returnCode) {
		super();
		this.code = returnCode.getCode();
		this.message = returnCode.getMessage();
		this.descreption = super.getMessage();
		this.httpStatus = returnCode.getHttpStatus();
	}
}
