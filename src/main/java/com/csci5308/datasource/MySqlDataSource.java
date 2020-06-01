package com.csci5308.datasource;

import java.sql.Connection;
import java.sql.DriverManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;


@ComponentScan
@Component
@PropertySource("classpath:database.properties")
public class MySqlDataSource implements DataSource {

	Logger logger = LoggerFactory.getLogger(MySqlDataSource.class);
	
	@Value("${development.driver}")
	private String JDBC_DRIVER;

	@Value("${development.url}")
	private String DB_URL;

	@Value("${development.username}")
	private String USER;

	@Value("${development.password}")
	private String PASS;

	private Connection connection;

	@SuppressWarnings("unchecked")
	@Override
	public Connection openConnection() throws Exception {
		try {
		Class.forName(JDBC_DRIVER);
		logger.info("Connecting to the selected database...");
		connection = DriverManager.getConnection(DB_URL, USER, PASS);
		} catch(Exception e) {
			e.printStackTrace();
		}
		logger.info("Connected to the database successfully...");
		return connection;
	}

	@Override
	public void closeConnection() throws Exception {
		try {
		connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
