package com.financedoc.edu_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.financedoc.edu_service.domain")
public class EduServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EduServiceApplication.class, args);
	}

}
