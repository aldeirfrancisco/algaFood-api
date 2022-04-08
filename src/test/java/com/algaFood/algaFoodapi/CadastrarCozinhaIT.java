package com.algaFood.algaFoodapi;

import static io.restassured.RestAssured.given;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.matcher.ResponseAwareMatcher;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CadastrarCozinhaIT {
   
  @LocalServerPort
  private int port;
  
  @Test
  public void deveRetornarStatus200_QuandoConsultaCozinhas() {
	  RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
	  given()
	      .basePath("/cozinhas")
	      .port(port)
	      .accept(ContentType.JSON)
	   .when()
	      .get()
	   .then()
	      .statusCode(HttpStatus.OK.value());
  }
  
  @Test
  public void deveConter4Cozinha_QuandoConsultaCozinhas() {
	  RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
	  //Matchers: é uma biblioteca para escrever espresões com regras de corespondencia entre objeto.
	  given()
	      .basePath("/cozinhas")
	      .port(port)
	      .accept(ContentType.JSON)
	   .when()
	      .get()
	   .then()
	      .body("", Matchers.hasSize(4))
	      .body("nome", Matchers.hasItems("Indiana", "Tailandesa"));
  }
}
