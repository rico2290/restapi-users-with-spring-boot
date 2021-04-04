package com.users.restapi.exceptionhandler;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.users.restapi.domain.exception.HandleBusinessException;



@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
	
	@Autowired
	private MessageSource messageSource;
	
	@ExceptionHandler(HandleBusinessException.class)
	public ResponseEntity<Object> handleBusiness(HandleBusinessException hbe, WebRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		Problem problem = new Problem();
		problem.setStauts(status.value());
		problem.setTitle(hbe.getMessage());
		problem.setDateHour(LocalDateTime.now());
		return handleExceptionInternal(hbe, problem, new HttpHeaders(), status, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		
		ArrayList<Problem.FieldProblem> fieldProblem = new ArrayList<Problem.FieldProblem>();
		
		for(ObjectError error: ex.getBindingResult().getAllErrors()) {
			String name = ((FieldError) error).getField();
			String message = messageSource.getMessage(error, LocaleContextHolder.getLocale());
			 
			fieldProblem.add(new Problem.FieldProblem(name, message));
		}
		
		Problem problem = new Problem();
		problem.setStauts(status.value());
		problem.setTitle("Um ou mais campos estão inválidos. "
				+ "Faça o preenchimento correto e tente novamente");
		problem.setDateHour(LocalDateTime.now());
		problem.setFieldProblem(fieldProblem);
		
		
		
		return super.handleExceptionInternal(ex, problem, headers, status, request);
	}
	
	
	

}
