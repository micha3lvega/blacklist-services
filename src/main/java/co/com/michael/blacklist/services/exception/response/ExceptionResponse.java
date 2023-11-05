package co.com.michael.blacklist.services.exception.response;

import java.io.Serializable;
import java.util.Date;

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

	private String message;

	@Builder.Default
	private Date date = new Date();

}
