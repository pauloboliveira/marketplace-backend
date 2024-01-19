package com.dev.suacomanda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class AnotaaiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnotaaiApplication.class, args);
	}

}
