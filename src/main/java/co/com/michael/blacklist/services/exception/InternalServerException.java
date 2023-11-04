package co.com.michael.blacklist.services.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalServerException extends RuntimeException {

	private static final long serialVersionUID = -8099570107596982253L;

	public InternalServerException() {
		super("Internal Server Error");
	}

	public InternalServerException(String message) {
		super(message);
	}

}
