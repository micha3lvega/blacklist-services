package co.com.michael.blacklist.services.exception;

public class InternalServerException extends RuntimeException {

	private static final long serialVersionUID = -8099570107596982253L;

	public InternalServerException() {
		super("Internal Server Error");
	}

	public InternalServerException(String message) {
		super(message);
	}

}
