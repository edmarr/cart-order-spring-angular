package com.tech.handler;

import java.util.Date;

import com.tech.exception.BusinessException;
import com.tech.exception.ExceptionApiResponse;
import com.tech.exception.NoContentException;
import com.tech.exception.RequestException;
import com.tech.exception.ResourceNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{
    
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ExceptionApiResponse> handleAllException(Exception ex, WebRequest request){
		ExceptionApiResponse exceptionResponse = new ExceptionApiResponse(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @ExceptionHandler(BusinessException.class)
	public final ResponseEntity<ExceptionApiResponse> handleBusinessException(Exception ex, WebRequest request){
		ExceptionApiResponse exceptionResponse = new ExceptionApiResponse(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public final ResponseEntity<ExceptionApiResponse> handleNotFoundException(Exception ex, WebRequest request){
		ExceptionApiResponse exceptionResponse = new ExceptionApiResponse(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(RequestException.class)
	public final ResponseEntity<ExceptionApiResponse> handleRequestExceptions(Exception ex, WebRequest request){
		ExceptionApiResponse exceptionResponse = new ExceptionApiResponse(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}


	@ExceptionHandler(NoContentException.class)
	public final ResponseEntity<ExceptionApiResponse> handleNoContentExceptions(Exception ex, WebRequest request){
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}