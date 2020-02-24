package com.hadialaoui.notebookserverspringboot.controllers;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hadialaoui.notebookserverspringboot.constants.InterpreterConstants;
import com.hadialaoui.notebookserverspringboot.models.InterpreterInput;
import com.hadialaoui.notebookserverspringboot.models.InterpreterResult;
import com.hadialaoui.notebookserverspringboot.models.UserRequest;
import com.hadialaoui.notebookserverspringboot.services.NotebookServerService;

/**
 * @author ABDELHAKIMHADIALAOUI
 *
 */
@RestController
public class IntrepreterServerController {
	
	@Autowired
	private NotebookServerService notebookServerService;
	

	/**
	 * Check service availability
	 * @return
	 */
	@GetMapping(value = InterpreterConstants.ResourcePaths.BASE_PATH)
	public String disponible(){
		return "Notebook server is disponible";
	}
	
	/**
	 * End point to execute the code by the chosen interpreter, user given interpreter name, code and session id
	 * (sessionId is optional to take advantage of session functionality)
	 * @param userRequest (code : %[interpreter-name][whitespace][code], sessionID)
	 * @param response
	 * @return <b>InterpreterResult</b> : the result of the execution
	 */
	@PostMapping(value = InterpreterConstants.ResourcePaths.BASE_PATH)
	public InterpreterResult execute(@RequestBody UserRequest userRequest, HttpServletResponse response){
		
		InterpreterInput interpreterInput =notebookServerService.parseInterpreterInput(userRequest) ;
		InterpreterResult result  = notebookServerService.execute(interpreterInput);
		if(!StringUtils.isEmpty(interpreterInput.getSessionId())) {
			response.addHeader("sessionId", interpreterInput.getSessionId());
		}
		return result;
	}
}
