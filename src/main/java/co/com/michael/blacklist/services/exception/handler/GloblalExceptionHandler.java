package co.com.michael.blacklist.services.exception.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import co.com.michael.blacklist.services.exception.InternalServerException;
import co.com.michael.blacklist.services.exception.response.ExceptionResponse;
import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GloblalExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ExceptionResponse exceptionHandler(InternalServerException exception, HttpServletRequest request) {

		return ExceptionResponse.builder().requestURL(request.getRequestURL().toString())
				.message(exception.getMessage()).build();
	}

}
