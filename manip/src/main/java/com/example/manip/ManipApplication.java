package com.example.manip;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class ManipApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManipApplication.class, args);
	}

	@Bean
	CommandLineRunner init(CoordinateRepository coordinateRepository) {
		return args -> {
			Stream.of(48.8d, 48.81d, 48.85d, 48.9d, 50d, 42d).forEach(lat -> {
				Coordinates coordinates = new Coordinates(lat, 2.28d);
				coordinateRepository.save(coordinates);
			});
			coordinateRepository.findAll().forEach(System.out::println);
		};
	}
}
