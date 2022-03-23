package com.algaFood.algaFoodapi.api.exceptionhandler;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Field {

    private String property;

    private String message;

}