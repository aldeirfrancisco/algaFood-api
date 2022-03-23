package com.algaFood.algaFoodapi.api.exceptionhandler;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Builder;
import lombok.Getter;

//@JsonInclude(Include.NON_NULL)
@Getter
@Builder
public class Problem { // RFC 7807: https://datatracker.ietf.org/doc/html/rfc7807s
    
	private Integer status;

    private String type;

    private String title;

    private String detail;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime timestamp;

    private String userMessage;

    private List<Field> fields;
	
}
