package com.hadialaoui.notebookserverspringboot.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Enum represent the group of the different exception  
 * @author ABDELHAKIMHADIALAOUI
 *
 */
@Getter
@AllArgsConstructor
public enum ReturnCode {
   ERROR_PARSING_CODE("01","Error parsing code", 400),
   UNKNOWN_INTERPRETER("02","Interpreter not known", 400),
   INTERPRETER_TIMEOUT ("03","Interpreter takes a long time ", 404),
   ERROR_PYTHON_PROCESS ("04","python process encounters some kind of error", 400),
   OTHER_EXCEPTIONS ("05","Internal server error", 500);
	
   private String code;
   private String message;
   private int httpStatus;
   
}
