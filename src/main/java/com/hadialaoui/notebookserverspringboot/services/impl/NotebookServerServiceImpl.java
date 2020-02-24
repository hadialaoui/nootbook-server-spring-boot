package com.hadialaoui.notebookserverspringboot.services.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.hadialaoui.notebookserverspringboot.constants.InterpreterConstants;
import com.hadialaoui.notebookserverspringboot.exceptions.technical.ParsingCodeException;
import com.hadialaoui.notebookserverspringboot.exceptions.technical.UnknownInterpreterException;
import com.hadialaoui.notebookserverspringboot.factory.Interpreter;
import com.hadialaoui.notebookserverspringboot.factory.InterpreterName;
import com.hadialaoui.notebookserverspringboot.models.InterpreterInput;
import com.hadialaoui.notebookserverspringboot.models.InterpreterResult;
import com.hadialaoui.notebookserverspringboot.models.UserRequest;
import com.hadialaoui.notebookserverspringboot.services.NotebookServerService;
import com.hadialaoui.notebookserverspringboot.services.InterpreterStore;

@Service
public class NotebookServerServiceImpl implements NotebookServerService{
	
	@Value("${interpreter.server.enable.sessions}")
	private boolean enableSessions;
	@Autowired
	private ApplicationContext context;
	
	@Autowired
	private InterpreterStore interpreterStore;
	@Override
	public InterpreterInput parseInterpreterInput(UserRequest input) {
		if(!NotebookServerServiceImpl.isValid(input.getCode())) {
			throw new ParsingCodeException();
		}
		InterpreterInput inputContent = new InterpreterInput();
		String content[] = input.getCode().split(" ", 2);
		inputContent.setInterpreterName(content[0].substring(1));
		inputContent.setCode(content[1]);
		inputContent.setSessionId(input.getSessionId());
		
		return inputContent;
	}

	@Override
	public InterpreterResult execute(InterpreterInput interpreterInput) {
		Interpreter interpreter;
		if(enableSessions) {
			if(StringUtils.isEmpty(interpreterInput.getSessionId())) {
				String newSessionId = createIdSession();
				interpreter = interpreterStore.getInterpreterContext(interpreterInput.getInterpreterName(), newSessionId);
				interpreterInput.setSessionId(newSessionId);
			}else {
				interpreter = interpreterStore.getInterpreterContext(interpreterInput.getInterpreterName(), interpreterInput.getSessionId());
			}
			
		}else {
			if(InterpreterName.PYTHON.getCode().equals(interpreterInput.getInterpreterName())) {
				interpreter = (Interpreter) context.getBean("pythonInterpreterImpl");
			}else {
				 throw new UnknownInterpreterException();
			}
		}
		return interpreter.execute(interpreterInput.getCode());
	}
	
	
	public InterpreterResult executeWithHttpSession(InterpreterInput interpreterInput, Interpreter interpreter) {
		return interpreter.execute(interpreterInput.getCode());
	}
	
	@Override
	public boolean sessionModeIsEnable() {
		return enableSessions;
	}
	
	private String createIdSession() {
		return UUID.randomUUID().toString();
	}
	
	public static boolean isValid(String inputContent) {
	      return inputContent.matches(InterpreterConstants.Patterns.CODE_INPUT);
	   }

}
