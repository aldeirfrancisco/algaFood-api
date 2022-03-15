package com.algaFood.algaFoodapi.api.exceptionhandler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.algaFood.algaFoodapi.domain.exception.EntidadeNaoEncontradaExecption;
import com.algaFood.algaFoodapi.domain.exception.NegocioException;

@ControllerAdvice
public class ApiExceptionHandler {
   

    @ExceptionHandler(EntidadeNaoEncontradaExecption.class)
    public ResponseEntity<?> trataEntidadeNaoEncontradaException(
    		 EntidadeNaoEncontradaExecption e){
    	Problema problema = Problema.builder()
    			.dataHora(LocalDateTime.now())
    			.msg(e.getMessage()).build();
    	
    	return ResponseEntity.status(HttpStatus.NOT_FOUND)
    			.body(problema);
    }
    
    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<?> trataNegocioException(
    		NegocioException e){
    	
    	Problema problema = Problema.builder()
    			.dataHora(LocalDateTime.now())
    			.msg(e.getMessage()).build();
    	
    	return ResponseEntity.status(HttpStatus.NOT_FOUND)
    			.body(problema);
    }
    
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<?> trataHttpMediaTypeNotSupportedException(){
    	
    	Problema problema = Problema.builder()
    			.dataHora(LocalDateTime.now())
    			.msg("O tipo de mídia não é aceita.").build();
    	
    	return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    			.body(problema);
    }
}
