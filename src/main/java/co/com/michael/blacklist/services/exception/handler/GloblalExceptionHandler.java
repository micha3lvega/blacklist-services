package co.com.michael.blacklist.services.exception.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.webjars.NotFoundException;

import co.com.michael.blacklist.services.exception.InternalServerException;
import co.com.michael.blacklist.services.exception.response.ExceptionResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class GloblalExceptionHandler {

	@ExceptionHandler(InternalServerException.class)
	public ExceptionResponse exceptionHandler(InternalServerException exception) {

		log.error("InternalServerException: {}", exception);
		return ExceptionResponse.builder().message(exception.getMessage()).build();

	}

	@ExceptionHandler(NotFoundException.class)
	public ExceptionResponse notFoundExceptionExceptionHandler(NotFoundException exception) {

		log.error("NotFoundException: {}", exception);
		return ExceptionResponse.builder().message(exception.getMessage()).build();

	}
}
