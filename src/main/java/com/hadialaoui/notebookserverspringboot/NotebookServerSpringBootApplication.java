package com.hadialaoui.notebookserverspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.hadialaoui.notebookserverspringboot.factory.Interpreter;
import com.hadialaoui.notebookserverspringboot.factory.PythonInterpreterImpl;

@EnableAsync
@EnableScheduling
@SpringBootApplication
public class NotebookServerSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotebookServerSpringBootApplication.class, args);
	}

	@Bean
	public Interpreter getPythonIntrepreter() {
		return new PythonInterpreterImpl();
	}
}
