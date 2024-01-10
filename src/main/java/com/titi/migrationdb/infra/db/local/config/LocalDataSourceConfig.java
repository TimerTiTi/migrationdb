package com.titi.migrationdb.infra.db.local.config;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.batch.BatchDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
	entityManagerFactoryRef = "localEntityManagerFactory",
	transactionManagerRef = "localTransactionManager",
	basePackages = {"com.titi.migrationdb.infra.db.local.repository"}
)
public class LocalDataSourceConfig {

	@Primary
	@Bean(name = "localDataSource")
	@BatchDataSource
	@ConfigurationProperties(prefix = "spring.datasource.hikari.local")
	public DataSource localDataSource() {
		return DataSourceBuilder.create()
			.type(HikariDataSource.class)
			.build();
	}

	@Primary
	@Bean(name = "localEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean localEntityManagerFactory() {
		final LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
		factoryBean.setDataSource(localDataSource());
		factoryBean.setPackagesToScan("com.titi.migrationdb.infra.db.local.entity");
		factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		return factoryBean;
	}

	@Primary
	@Bean(name = "localTransactionManager")
	public PlatformTransactionManager localTransactionManager() {
		return new JpaTransactionManager(localEntityManagerFactory().getObject());
	}

}
