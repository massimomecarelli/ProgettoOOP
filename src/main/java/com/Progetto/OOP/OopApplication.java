package com.Progetto.OOP;

import javax.annotation.Resource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@Resource(name="ErrorException")
@Resource(name="FileIsEmpty")
@SpringBootApplication
@ComponentScan({"Service"})
@EnableScheduling
public class OopApplication {

	public static void main(String[] args) {
		SpringApplication.run(OopApplication.class, args);
	}

}
