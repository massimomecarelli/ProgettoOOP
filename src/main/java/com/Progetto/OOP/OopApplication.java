package com.Progetto.OOP;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"Service"})
public class OopApplication {

	public static void main(String[] args) {
		SpringApplication.run(OopApplication.class, args);
	}

}
