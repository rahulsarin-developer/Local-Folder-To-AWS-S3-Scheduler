package com.example.project.localfoldertos3scheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Localfoldertos3schedulerApplication {

	public static void main(String[] args) {
		SpringApplication.run(Localfoldertos3schedulerApplication.class, args);
	}
}