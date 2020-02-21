package com.hadialaoui.notebookserverspringboot.models;

import lombok.Getter;
import lombok.Setter;

/**
 * Contains the data input sent by client 
 * @author ABDELHAKIMHADIALAOUI
 *
 */
@Getter
@Setter
public class UserRequest {
	/**
	 * Feild code
	 */
	private String code ;
	/**
	 * sessionId (optional)
	 */
	private String sessionId;
}
