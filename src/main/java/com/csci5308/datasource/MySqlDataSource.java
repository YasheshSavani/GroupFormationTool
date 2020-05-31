package com.csci5308.datasource;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MySqlDataSource implements DataSource {

	Logger logger = LoggerFactory.getLogger(MySqlDataSource.class);

	private static String JDBC_DRIVER;
	private static String DB_URL;
	private static String USER;
	private static String PASSWORD;

	private Connection connection = null;

	private static String databasePropertiesFilePath = "src/main/resources/database.properties";

	@SuppressWarnings("unchecked")
	@Override
	public Connection openConnection() throws Exception {
		try {

			Reader dbPropertiesReader = new BufferedReader(new FileReader(databasePropertiesFilePath));
			Properties properties = new Properties();
			properties.load(dbPropertiesReader);

			JDBC_DRIVER = properties.getProperty("development.driver");
			DB_URL = properties.getProperty("development.url");
			USER = properties.getProperty("development.username");
			PASSWORD = properties.getProperty("development.password");

			Class.forName(JDBC_DRIVER);
			logger.info("Connecting to the selected database...");
			connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Connected to the database successfully...");
		return connection;
	}

	@Override
	public void closeConnection() throws Exception {
		try {
			connection.close();
			logger.info("Connection Closed Successfully...");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
