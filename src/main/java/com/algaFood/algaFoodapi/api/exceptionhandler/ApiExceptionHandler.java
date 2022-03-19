package com.algaFood.algaFoodapi.api.exceptionhandler;

import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.algaFood.algaFoodapi.domain.exception.EntidadeEmUsoException;
import com.algaFood.algaFoodapi.domain.exception.EntidadeNaoEncontradaExecption;
import com.algaFood.algaFoodapi.domain.exception.NegocioException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
   

    @ExceptionHandler(EntidadeNaoEncontradaExecption.class)
    public ResponseEntity<?> trataEntidadeNaoEncontradaException(
    		 EntidadeNaoEncontradaExecption ex, WebRequest request){
    	
    	return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND, request);

    }
    
    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<?> trataNegocioException(
    		NegocioException ex,  WebRequest request){
    	
    	return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.CONFLICT, request);

  
    }
    

    @ExceptionHandler(EntidadeEmUsoException.class)
    public ResponseEntity<?> trataEntidadeEmUsoException(
    		EntidadeEmUsoException ex,  WebRequest request ){
    	return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.CONFLICT, request);

    }
    
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
    		HttpStatus status, WebRequest request) {
    	
    	if(body == null) {
    		
    		body = Problema.builder()
    				.dataHora(LocalDateTime.now())
    				.msg(status.getReasonPhrase()).build();
    		
    	} else if ( body instanceof String) {
    		
    		body = Problema.builder()
    				.dataHora(LocalDateTime.now())
    				.msg((String)body)
    				.build();
    	}
    		
    	return super.handleExceptionInternal(ex, body, headers, status, request);
    }
}
