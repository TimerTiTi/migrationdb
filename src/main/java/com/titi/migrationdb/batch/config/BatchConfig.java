package com.titi.migrationdb.batch.config;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.boot.autoconfigure.batch.JobLauncherApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

/**
 * Changes due to @EnableBatchProcessing: <br/>
 * - The Map-based Job Repository and explorer have been completely removed. <br/>
 * - Therefore, @EnableBatchProcessing sets up the JDBC-based JobRepository. <br/>
 * - It looks for DataSource and PlatformTransactionManager beans within the application context. <br/>
 * - If needed, one can override @EnableBatchProcessing or DefaultBatchConfiguration#getTransactionManager()  <br/>
 *   to specify a custom TransactionManager.
 * - New annotation properties added: dataSourceRef, transactionManagerRef.
 */
@Configuration
@EnableConfigurationProperties(BatchProperties.class)
@EnableBatchProcessing(
	dataSourceRef = "localDataSource",
	transactionManagerRef = "localTransactionManager"
)
public class BatchConfig {

	/**
	 * From version 5.0 onwards, @EnableBatchProcessing annotation is no longer mandatory. Previously, in versions 4.x,
	 * this annotation was necessary to register beans related to Batch processing. However, now, even without using
	 * this annotation, the bean registration is facilitated.
	 * <p>
	 * However, BatchAutoConfiguration now includes @ConditionalOnMissingBean. If this annotation is used to change
	 * default settings such as the datasource or transaction manager, certain beans like JobLauncherApplicationRunner
	 * or BatchDataSourceScriptDatabaseInitializer won’t be registered automatically. Consequently, these beans need
	 * to be manually registered.
	 */
	@Bean
	@ConditionalOnMissingBean
	@ConditionalOnProperty(prefix = "spring.batch.job", name = "enabled", havingValue = "true", matchIfMissing = true)
	public JobLauncherApplicationRunner jobLauncherApplicationRunner(JobLauncher jobLauncher, JobExplorer jobExplorer,
		JobRepository jobRepository, BatchProperties properties) {
		JobLauncherApplicationRunner runner = new JobLauncherApplicationRunner(jobLauncher, jobExplorer, jobRepository);
		String jobNames = properties.getJob().getName();
		if (StringUtils.hasText(jobNames)) {
			runner.setJobName(jobNames);
		}
		return runner;
	}

}
