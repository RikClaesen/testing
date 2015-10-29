package com.example.exception;

public class ResourceRefusedException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ResourceRefusedException() {
	}

	public ResourceRefusedException(String message, Throwable cause) {
		super(message, cause);

	}

	public ResourceRefusedException(String message) {
		super(message);
		
	}


	
	
}
