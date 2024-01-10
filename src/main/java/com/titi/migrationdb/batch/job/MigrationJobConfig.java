package com.titi.migrationdb.batch.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class MigrationJobConfig {

	private final Step migrationUserStep;
	private final Step migrationTaskStep;
	private final Step migrationDailyStep;
	private final Step migrationSyncLogStep;
	private final Step migrationRecordTimeStep;
	private final Step migrationTimeLineStep;

	@Bean
	public Job migrationJob(JobRepository jobRepository) {
		return new JobBuilder("migrationJob", jobRepository)
			.start(migrationUserStep)
			.next(migrationDailyStep)
			.next(migrationTaskStep)
			.next(migrationSyncLogStep)
			.next(migrationRecordTimeStep)
			.next(migrationTimeLineStep)
			.build();
	}

}
