package com.titi.migrationdb.batch.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import lombok.RequiredArgsConstructor;

import com.titi.migrationdb.batch.task.MigrationTaskTasklet;

@Configuration
@RequiredArgsConstructor
public class MigrationTaskStepConfig {

	private final MigrationTaskTasklet migrationTaskTasklet;

	@Bean
	@JobScope
	public Step migrationTaskStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
		return new StepBuilder("migrationTaskStep", jobRepository)
			.tasklet(migrationTaskTasklet, transactionManager)
			.build();
	}

}
