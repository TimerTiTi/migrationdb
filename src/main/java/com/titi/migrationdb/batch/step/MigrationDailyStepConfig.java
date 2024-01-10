package com.titi.migrationdb.batch.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import lombok.RequiredArgsConstructor;

import com.titi.migrationdb.batch.task.MigrationDailyTasklet;

@Configuration
@RequiredArgsConstructor
public class MigrationDailyStepConfig {

	private final MigrationDailyTasklet migrationDailyTasklet;

	@Bean
	@JobScope
	public Step migrationDailyStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
		return new StepBuilder("migrationDailyStep", jobRepository)
			.tasklet(migrationDailyTasklet, transactionManager)
			.build();
	}

}
