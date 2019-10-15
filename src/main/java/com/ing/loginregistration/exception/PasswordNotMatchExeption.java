package com.ing.loginregistration.exception;

public class PasswordNotMatchExeption extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public PasswordNotMatchExeption(String message) {
		super(message);
	}

}
