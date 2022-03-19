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
    public ResponseEntity<?> handleEntidadeNaoEncontradaException(
    		 EntidadeNaoEncontradaExecption ex, WebRequest request){
    	
    	HttpStatus status = HttpStatus.NOT_FOUND;
    	ProblemType problemType = ProblemType.ENTIDADE_NAO_ENCONTRADA;
    	String detail = ex.getMessage();
    	
    	Problem problem = createProblemBuilder(status, problemType, detail).build();
//    	Problem problem = Problem.builder()
//    			                 .status(status.value())
//    			                 .type("https://algafood.com.br/entidade-nao-encontrada")
//    			                 .title("Entidade não encontrada")
//    			                 .detail(ex.getMessage())
//    			                 .build();
    	
    	return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);

    }
    
    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<?> handleNegocioException(
    		NegocioException ex,  WebRequest request){
    	
    	return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.CONFLICT, request);

  
    }
    

    @ExceptionHandler(EntidadeEmUsoException.class)
    public ResponseEntity<?>  handleEntidadeEmUsoException(
    		EntidadeEmUsoException ex,  WebRequest request ){
    	return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.CONFLICT, request);

    }
    
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
    		HttpStatus status, WebRequest request) {
    	
    	if(body == null) {
    		
    		body = Problem.builder()
                    .status(status.value())
    				.title(status.getReasonPhrase())
    				.build();
    		       
    	} else if ( body instanceof String) {
    		
    		body = Problem.builder()
    				.status(status.value())
    				.title((String)body)
    				.build();
    	}
    		
    	return super.handleExceptionInternal(ex, body, headers, status, request);
    }
    
    private Problem.ProblemBuilder createProblemBuilder(HttpStatus status,
    		ProblemType problemType, String detail) {
    	
    	return Problem.builder()
				.status(status.value())
				.type(problemType.getUri())
				.title(problemType.getTitle())
			    .detail(detail);	
    }
}
