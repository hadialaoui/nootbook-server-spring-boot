package com.hadialaoui.notebookserverspringboot.models;

import lombok.Getter;
import lombok.Setter;

/**
 * Contains the interpreterName, code and sessionId after parsing the {@UserRequest} Object
 * @author ABDELHAKIMHADIALAOUI
 *
 */
@Getter
@Setter
public class InterpreterInput {
	/**
	 * Field interpreterName
	 */
	private String interpreterName;
	/**
	 * Field code : code to want execute
	 */
	private String code;
	/**
	 * Field session Id (optional)
	 */
	private String sessionId;
}
