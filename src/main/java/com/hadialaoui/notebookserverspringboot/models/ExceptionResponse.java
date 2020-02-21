package com.hadialaoui.notebookserverspringboot.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * The Exception DTO 
 * @author ABDELHAKIMHADIALAOUI
 *
 */
@AllArgsConstructor
@Getter
@Setter
public class ExceptionResponse {
	private String code;
    private String message;
    @JsonInclude(Include.NON_NULL)
    private String details;
    private String timestamp;
}
