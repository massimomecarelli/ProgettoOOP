package com.Progetto.OOP;

import javax.annotation.Resource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Classe che avvia il server Spring
 */
@Resource(name="ErrorException")
@Resource(name="FileIsEmpty")
@SpringBootApplication
@ComponentScan({"Service"})
@EnableScheduling
public class OopApplication {

	/**
	 * Main del programma.
	 * @param args : argomenti aggiuntivi
	 */
	public static void main(String[] args) {
		SpringApplication.run(OopApplication.class, args);
	}

}
