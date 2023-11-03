package com.loja.faculdade.entidades.exception;

public class BrinquedoNotFoundException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BrinquedoNotFoundException() {
    }

    public BrinquedoNotFoundException(String message) {
        super(message);
    }

    public BrinquedoNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public BrinquedoNotFoundException(Throwable cause) {
        super(cause);
    }

}
