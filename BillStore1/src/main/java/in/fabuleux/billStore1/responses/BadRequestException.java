package in.fabuleux.billStore1.responses;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(reason = "Bad request",code = HttpStatus.BAD_REQUEST,value = HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException 
{
	public BadRequestException(String message) 
	{
		super(message);
	}
}
