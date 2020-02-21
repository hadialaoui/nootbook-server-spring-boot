package com.hadialaoui.notebookserverspringboot.constants;

/**
 * @author ABDELHAKIMHADIALAOUI
 *
 */
public interface InterpreterConstants {
	
		interface ResourcePaths{
			/**
			 * endpoint URL of the notebook 
			 */
			String BASE_PATH = "/api/v1/execute";
		}
		interface Patterns{
			/**
			 * pattern of the code input 
			 */
			String CODE_INPUT = "%[a-zA-Z]+[ ][\\W\\w]+";
		}
}
