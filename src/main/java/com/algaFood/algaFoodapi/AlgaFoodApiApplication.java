package com.algaFood.algaFoodapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.algaFood.algaFoodapi.infrastrutura.repository.CustomJpaRepositoryImpl;


@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomJpaRepositoryImpl.class)
public class AlgaFoodApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlgaFoodApiApplication.class, args);
	}

}
