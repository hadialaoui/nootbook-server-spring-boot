package com.hadialaoui.notebookserverspringboot.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.hadialaoui.notebookserverspringboot.exceptions.functional.FunctionalException;
import com.hadialaoui.notebookserverspringboot.exceptions.technical.TechnicalException;
import com.hadialaoui.notebookserverspringboot.models.ExceptionResponse;

/**
 * A class wish to provide centralized exception handling 
 * @author ABDELHAKIMHADIALAOUI
 *
 */
@ControllerAdvice
@RestController
public class NoteBookServerExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(ReturnCode.OTHER_EXCEPTIONS.getCode(), ex.getMessage(),"", LocalDateTime.now().toString());
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.valueOf(ReturnCode.OTHER_EXCEPTIONS.getHttpStatus()));
	}
	
	@ExceptionHandler(FunctionalException.class)
	public final ResponseEntity<Object> functionalExceptions(FunctionalException ex, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(ex.getCode(), ex.getMessage(),ex.getDescreption(), LocalDateTime.now().toString());
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.valueOf(ex.getHttpStatus()));
	}
	
	@ExceptionHandler(TechnicalException.class)
	public final ResponseEntity<Object> technicalExceptions(TechnicalException ex, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(ex.getCode(), ex.getMessage(),ex.getDescreption(), LocalDateTime.now().toString());
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.valueOf(ex.getHttpStatus()));
	}
	
	
	
	


}
