package com.tracker;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TrackerApplication{
	
	@Bean
	CommandLineRunner runApplication(TrackerRepository trackerRepository){
		return args ->{
			System.out.println("======================================Table A======================================");
			trackerRepository.insertTableA();
			System.out.println("======================================Table B======================================");
			trackerRepository.insertTableB();
			System.out.println("======================================Table C======================================");
			trackerRepository.insertTableC();
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(TrackerApplication.class, args);
	}
}
