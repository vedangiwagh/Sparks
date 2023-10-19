package com.planning.mealsandrecipes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class MealsandrecipesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MealsandrecipesApplication.class, args);
	}

}
