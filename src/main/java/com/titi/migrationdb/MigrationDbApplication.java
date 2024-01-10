package com.titi.migrationdb;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class MigrationDbApplication {

	public static void main(String[] args) {
		SpringApplication.run(MigrationDbApplication.class, args);
	}

}
