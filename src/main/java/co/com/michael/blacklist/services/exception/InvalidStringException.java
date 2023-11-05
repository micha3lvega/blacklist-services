package co.com.michael.blacklist.services.exception;

public class InvalidStringException extends RuntimeException {

	private static final long serialVersionUID = -7046047199840386398L;

	public InvalidStringException() {
		super("La palabra se encuentra en el diccionario");
	}

	public InvalidStringException(String message) {
		super(message);
	}

}
