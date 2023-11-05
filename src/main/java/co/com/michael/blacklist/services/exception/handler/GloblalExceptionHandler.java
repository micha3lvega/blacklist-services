package co.com.michael.blacklist.services.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.webjars.NotFoundException;

import co.com.michael.blacklist.services.exception.InternalServerException;
import co.com.michael.blacklist.services.exception.InvalidStringException;
import co.com.michael.blacklist.services.exception.response.ExceptionResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GloblalExceptionHandler {

	@ExceptionHandler(InternalServerException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ExceptionResponse exceptionHandler(InternalServerException exception) {

		log.error("InternalServerException: {}", exception);
		return ExceptionResponse.builder().message(exception.getMessage()).build();

	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(NotFoundException.class)
	public ExceptionResponse notFoundExceptionExceptionHandler(NotFoundException exception) {

		log.error("NotFoundException: {}", exception);
		return ExceptionResponse.builder().message(exception.getMessage()).build();

	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(InvalidStringException.class)
	public ExceptionResponse invalidStringException(InvalidStringException exception) {

		log.error("NotFoundException: {}", exception);
		return ExceptionResponse.builder().message(exception.getMessage()).build();

	}
}
