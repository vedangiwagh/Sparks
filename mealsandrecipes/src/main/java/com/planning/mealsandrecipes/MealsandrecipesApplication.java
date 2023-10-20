package com.planning.mealsandrecipes;

import com.planning.mealsandrecipes.entity.Client;
import com.planning.mealsandrecipes.repository.ClientRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;


@SpringBootApplication
public class MealsandrecipesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MealsandrecipesApplication.class, args);
	}

	@Bean
	public CommandLineRunner houses(ClientRepo houseRepository) {
		return (args) -> {
			houseRepository.deleteAll();

			Stream.of(new Client())
					.forEach(houseRepository::save);
		};
	}

}