package com.csci5308.datasource;

public interface DataSource {

	public <T> T openConnection() throws Exception;
	
	public void closeConnection() throws Exception;
	
}
