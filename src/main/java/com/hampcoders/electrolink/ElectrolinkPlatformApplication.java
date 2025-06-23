package com.hampcoders.electrolink;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


// This is the main entry point for the Electrolink Platform application.
@SpringBootApplication
@EnableJpaAuditing
public class ElectrolinkPlatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElectrolinkPlatformApplication.class, args);
	}

}
