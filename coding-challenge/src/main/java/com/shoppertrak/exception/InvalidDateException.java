package com.shoppertrak.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

////////////////////////////////////////////////////////////////
//  Code created by Tom
////////////////////////////////////////////////////////////////

@ResponseStatus(value=HttpStatus.BAD_REQUEST)
public class InvalidDateException extends RuntimeException 
{
	
	private static final long serialVersionUID = 1L;	
	private static final String MESSAGE = "Invalid date";
	
	public InvalidDateException() 
	{
        super(MESSAGE);
    }
}
