package com.loja.faculdade.entidades.exception;

public class UserNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserNotFoundException(String string) {
        super("usario não encontrado com o ID: " + string);
    }

}
