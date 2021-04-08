package com.pwc.denali2.estimator.configuration;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan({ "com.pwc.denali2.estimator.configuration" })
@PropertySource(value = { "classpath:defaultSettings.properties" })
public class DBSetupConfiguration {
	@Value("classpath:DBSetup_CreateTable.sql")
	private Resource tableInitScript;

	@Value("classpath:DBSetup_StaticDataInitialization.sql")
	private Resource masterDataInitScript;

	@Autowired
	private Environment environment;
	
	@Bean
	public DataSourceInitializer dataSourceInitializer(final DataSource dataSource) throws Exception {

		DatabaseMetaData dbm = dataSource.getConnection().getMetaData();
		ResultSet tables = dbm.getTables(null, null, environment.getRequiredProperty("appuser.table"), null);
		if (tables.next()) {
			return null;
		} else {
			final DataSourceInitializer initializer = new DataSourceInitializer();
			initializer.setDataSource(dataSource);
			initializer.setDatabasePopulator(databasePopulator());
			return initializer;
		}

	}

	private DatabasePopulator databasePopulator() {
		final ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.addScript(tableInitScript);
		populator.addScript(masterDataInitScript);
		return populator;
	}
}
