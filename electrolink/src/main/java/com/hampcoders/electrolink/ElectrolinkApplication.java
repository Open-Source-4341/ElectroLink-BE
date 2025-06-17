package com.hampcoders.electrolink;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication
@EnableJpaAuditing
public class ElectrolinkApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElectrolinkApplication.class, args);
	}

}
