package com.ey.javatest.validations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.ey.javatest.data.dto.ErrorMessage;

@ControllerAdvice
class ControllerAdviceErrorHandling {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseBody
	public ErrorMessage handleValidationExceptions(MethodArgumentNotValidException ex) {
		List<String> errorlist = new ArrayList<String>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = fieldName + ": " + error.getDefaultMessage();
			errorlist.add(errorMessage);
		});
		return new ErrorMessage(errorlist);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseBody
	public ErrorMessage onConstraintValidationException(ConstraintViolationException ex) {
		List<String> errorlist = new ArrayList<String>();
		ex.getConstraintViolations().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = fieldName + ": " + error.getMessage();
			errorlist.add(errorMessage);
		});
		
		return new ErrorMessage(errorlist);
	}

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public ErrorMessage onMessageNotReadableException(HttpMessageNotReadableException ex) {
        return new ErrorMessage(ex.getCause().toString());
    }
    
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    public ErrorMessage onMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        return new ErrorMessage(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseBody
    public ErrorMessage onMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException ex) {
        return new ErrorMessage(ex.getMessage());
    }
    
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(IOException.class)
    @ResponseBody
    public ErrorMessage onIOException(IOException ex) {
        return new ErrorMessage(ex.getMessage());
    }
    
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseBody
    public ErrorMessage onNotFoundException(NoHandlerFoundException ex) {
        return new ErrorMessage(ex.getMessage());
    }
    

    

}
