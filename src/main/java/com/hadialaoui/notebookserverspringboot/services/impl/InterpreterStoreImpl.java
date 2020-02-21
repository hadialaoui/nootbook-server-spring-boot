package com.hadialaoui.notebookserverspringboot.services.impl;

import static java.time.temporal.ChronoUnit.SECONDS;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.hadialaoui.notebookserverspringboot.factory.Interpreter;
import com.hadialaoui.notebookserverspringboot.factory.InterpreterFactory;
import com.hadialaoui.notebookserverspringboot.services.InterpreterStore;

@Component
public class InterpreterStoreImpl implements InterpreterStore{
	
	@Autowired
	private InterpreterFactory interpreterFactory;
	
	@Value("${session.timeout}")
	private long sessionTimeOut;

	/**
	 * Field interpreterStoreMap is a Map of the sessions with their interpreters instances
	 */
	public Map<String, Interpreter> interpreterStoreMap = new HashMap<>();
	
	@Override
	public Interpreter getInterpreterContext(String typeInterpreter, String sessionId) {
		Interpreter interpreter = interpreterStoreMap.get(sessionId);
		if(interpreter == null) {
			interpreter = interpreterFactory.createInterpreterInstance(typeInterpreter);
			interpreterStoreMap.put(sessionId, interpreter);
		}
		interpreter.setUpdateTime(LocalDateTime.now());
		return interpreter;
	}
	
	@Override
	public Interpreter restInterpreterContext(String typeInterpreter, String sessionId) {
		return interpreterStoreMap.put(sessionId,interpreterFactory.createInterpreterInstance(typeInterpreter));
	}
	
	@Scheduled(fixedDelay = 2*60*1000, initialDelay = 60*1000)
    @Async
    @Override
	public void destroyExpiredSessions() {
		List<String> toRemove = new ArrayList<>();
		for (String sessionId : interpreterStoreMap.keySet()) {
			Interpreter interpreter = interpreterStoreMap.get(sessionId);
			LocalDateTime now = LocalDateTime.now();
		    LocalDateTime  sessionUpdateTime= interpreter.getUpdateTime();
			long diff = Math.abs(SECONDS.between(now, sessionUpdateTime));
			if(sessionTimeOut < diff) {
				toRemove.add(sessionId);
				interpreter.close();
			}
		}
		
		toRemove.forEach(key -> interpreterStoreMap.remove(key));
	}

}
