package com.masai.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class LoginException extends RuntimeException{

	
	public LoginException(String message) {
		super(message);
	}
	
}
