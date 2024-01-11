package com.titi.migrationdb.infra.db.titi.source.config;

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
	entityManagerFactoryRef = "titiSourceEntityManagerFactory",
	transactionManagerRef = "titiSourceTransactionManager",
	basePackages = {"com.titi.migrationdb.infra.db.titi.source.repository"}
)
public class TiTiSourceDataSourceConfig {

	@Bean(name = "titiSourceDataSource")
	@BatchDataSource
	@ConfigurationProperties(prefix = "spring.datasource.hikari.titi-source")
	public DataSource titiSourceDataSource() {
		return DataSourceBuilder.create()
			.type(HikariDataSource.class)
			.build();
	}

	@Bean(name = "titiSourceEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean titiSourceEntityManagerFactory() {
		final LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
		factoryBean.setDataSource(titiSourceDataSource());
		factoryBean.setPackagesToScan("com.titi.migrationdb.infra.db.titi.entity");
		factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		return factoryBean;
	}

	@Bean(name = "titiSourceTransactionManager")
	public PlatformTransactionManager titiSourceTransactionManager() {
		return new JpaTransactionManager(titiSourceEntityManagerFactory().getObject());
	}

}
