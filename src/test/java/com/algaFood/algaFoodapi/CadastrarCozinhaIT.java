package com.algaFood.algaFoodapi;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CadastrarCozinhaIT {
   
  @LocalServerPort
  private int port;
  
  @Before
  public void setUp() {
	  RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
	  RestAssured.port =  port;
	  RestAssured.baseURI = "/cozinhas";
  }
  
  @Test
  public void deveRetornarStatus200_QuandoConsultaCozinhas() {
	 
	  given()
	      .accept(ContentType.JSON)
	   .when()
	      .get()
	   .then()
	      .statusCode(HttpStatus.OK.value());
  }
  
  @Test
  public void deveConter4Cozinha_QuandoConsultaCozinhas() {
	 
	  //Matchers: é uma biblioteca para escrever espresões com regras de correspondência entre objeto.
	  given()
	      .accept(ContentType.JSON)
	   .when()
	      .get()
	   .then()
	      .body("", Matchers.hasSize(4))
	      .body("nome", Matchers.hasItems("Indiana", "Tailandesa"));
  }
  
  @Test
  public void deveRetornarStatus201_QuandoConsultaCozinhas() {
	 
	  given()
	      .body("{\"nome\": \"Chinesa\"}")
	      .contentType(ContentType.JSON)
	      .accept(ContentType.JSON)
	   .when()
	      .post()
	   .then()
	      .statusCode(HttpStatus.CREATED.value());
  }
  
  @Test
  public void deveRetornarRespostaEStatusCorretos_QuandoConsultaCozinhasExistente() {
	 
	  given()
	      .pathParam("cozinhaId", 1)
	      .accept(ContentType.JSON)
	   .when()
	      .get("/{cozinhaId}")
	   .then()
	      .statusCode(HttpStatus.OK.value())
	      .body("nome", equalTo("Indiana"));
  }
  
  @Test
  public void deveRetornarStatus404_QuandoConsultaCozinhasInexistente() {
	 
	  given()
	      .pathParam("cozinhaId", 100)
	      .accept(ContentType.JSON)
	   .when()
	      .get("/{cozinhaId}")
	   .then()
	      .statusCode(HttpStatus.NOT_FOUND.value());
	     
  }
}
