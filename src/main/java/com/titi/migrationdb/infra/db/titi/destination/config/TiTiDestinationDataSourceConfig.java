package com.titi.migrationdb.infra.db.titi.destination.config;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.batch.BatchDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
	entityManagerFactoryRef = "titiDestinationEntityManagerFactory",
	transactionManagerRef = "titiDestinationTransactionManager",
	basePackages = {"com.titi.migrationdb.infra.db.titi.destination.repository"}
)
public class TiTiDestinationDataSourceConfig {

	@Bean(name = "titiDestinationDataSource")
	@BatchDataSource
	@ConfigurationProperties(prefix = "spring.datasource.hikari.titi-destination")
	public DataSource titiDestinationDataSource() {
		return DataSourceBuilder.create()
			.type(HikariDataSource.class)
			.build();
	}

	@Bean(name = "titiDestinationEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean titiDestinationEntityManagerFactory() {
		final LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
		factoryBean.setDataSource(titiDestinationDataSource());
		factoryBean.setPackagesToScan("com.titi.migrationdb.infra.db.titi.entity");
		factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		return factoryBean;
	}

	@Bean(name = "titiDestinationTransactionManager")
	public PlatformTransactionManager titiDestinationTransactionManager() {
		return new JpaTransactionManager(titiDestinationEntityManagerFactory().getObject());
	}

}
