package com.jfsfeb.stockmanagementsystemwithjdbc.utility;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class JdbcUtilityForStock {
	public Connection getConnection() {
    Connection connection = null;
	try {
		FileInputStream inputStream = new FileInputStream("stockManagement.properties");
		Properties properties = new Properties();
		properties.load(inputStream);
		Class.forName(properties.getProperty("Driver"));
        connection =  DriverManager.getConnection(properties.getProperty("dburl"));

		return connection;
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}
public String getSQLQuery(String query) {
	String getQuery = null;
	FileInputStream inputStream;
	try {
		inputStream = new FileInputStream("stockManagement.properties");
		Properties properties = new Properties();
		properties.load(inputStream);
		getQuery = properties.getProperty(query);

		return getQuery;
	} catch (Exception e) {
		e.printStackTrace();
	}

	return null;

}

}
