package co.com.michael.blacklist.services.exception;

public class NotFoundException extends RuntimeException {

	private static final long serialVersionUID = 5268386485458493380L;

	public NotFoundException() {
		super("Not Found!");
	}

	public NotFoundException(String message) {
		super(message);
	}

}
