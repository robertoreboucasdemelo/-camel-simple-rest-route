package com.moduretick.simplerestcamel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.moduretick.simplerestcamel.entity")
public class SimpleRestCamelApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleRestCamelApplication.class, args);
	}

}
