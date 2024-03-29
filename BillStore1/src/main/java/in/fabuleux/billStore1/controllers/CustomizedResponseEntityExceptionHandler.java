package in.fabuleux.billStore1.controllers;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import in.fabuleux.billStore1.models.ExceptionResponse;
import in.fabuleux.billStore1.responses.BadRequestException;
import in.fabuleux.billStore1.responses.NotFoundException;
import in.fabuleux.billStore1.responses.UnAuthorizedException;


@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler 
{
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request)
	{
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage() , request.getDescription(false));

		return new ResponseEntity(exceptionResponse,HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(NotFoundException.class)
	public final ResponseEntity<Object> handleUserNotFoundExceptions(Exception ex, WebRequest request)
	{
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage() , request.getDescription(false));

		return new ResponseEntity(exceptionResponse,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(UnAuthorizedException.class)
	public final ResponseEntity<Object> handleUnAuthorizedExceptions(Exception ex, WebRequest request)
	{
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage() , request.getDescription(false));

		return new ResponseEntity(exceptionResponse,HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(BadRequestException.class)
	public final ResponseEntity<Object> handleBadRequestExceptions(Exception ex, WebRequest request)
	{
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage() , request.getDescription(false));

		return new ResponseEntity(exceptionResponse,HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), "Validation failed" , ex.getBindingResult().toString());

		return new ResponseEntity(exceptionResponse,HttpStatus.BAD_REQUEST);
	}
}
