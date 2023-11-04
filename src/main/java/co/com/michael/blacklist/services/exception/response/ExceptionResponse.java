package co.com.michael.blacklist.services.exception.response;

import java.io.Serializable;
import java.util.Date;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionResponse implements Serializable {

	private static final long serialVersionUID = 1072844002741472252L;

	private Date date;
	private String message;
	private String requestURL;
	private HttpStatus responseCode;

	public ExceptionResponse(String message) {
		this.date = new Date();
		this.message = message;
	}

}
