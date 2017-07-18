package dev.exception;

@SuppressWarnings("serial")
public class CalculException extends RuntimeException {
	
	public CalculException(String message) {
		super(message);
	}
	
	public CalculException() {
		super("L'expression n'est pas valide");
	}
	
	public CalculException(String message, Throwable cause) {
		super(message, cause);
	}
}
