package com.titi.migrationdb.infra.db.titi.config;

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
	entityManagerFactoryRef = "titiEntityManagerFactory",
	transactionManagerRef = "titiTransactionManager",
	basePackages = {"com.titi.migrationdb.infra.db.titi.repository"}
)
public class TiTiDataSourceConfig {

	@Bean(name = "titiDataSource")
	@BatchDataSource
	@ConfigurationProperties(prefix = "spring.datasource.hikari.titi")
	public DataSource titiDataSource() {
		return DataSourceBuilder.create()
			.type(HikariDataSource.class)
			.build();
	}

	@Bean(name = "titiEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean titiEntityManagerFactory() {
		final LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
		factoryBean.setDataSource(titiDataSource());
		factoryBean.setPackagesToScan("com.titi.migrationdb.infra.db.titi.entity");
		factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		return factoryBean;
	}

	@Bean(name = "titiTransactionManager")
	public PlatformTransactionManager titiTransactionManager() {
		return new JpaTransactionManager(titiEntityManagerFactory().getObject());
	}

}
