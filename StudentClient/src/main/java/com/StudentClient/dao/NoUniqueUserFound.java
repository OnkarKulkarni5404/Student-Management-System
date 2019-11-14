package com.StudentClient.dao;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class NoUniqueUserFound extends RuntimeException {

	public NoUniqueUserFound(String message) {
		super(message);
	}

	
	
	
}