package com.revature.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DAOUtilities {	// utility for database connection
							// uses properties file to manage database data
	private static DAOUtilities connUtil;
	private static Properties databaseProps;

	private DAOUtilities() {
		databaseProps = new Properties();
		try {
				InputStream propertiesFileStream = DAOUtilities.class.getClassLoader()
					.getResourceAsStream("database.properties");
			databaseProps.load(propertiesFileStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public synchronized static DAOUtilities getConnectionUtil() {
		if (connUtil == null)
			connUtil = new DAOUtilities();
		return connUtil;
	}
	public Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName(databaseProps.getProperty("drv"));
			conn = DriverManager.getConnection(databaseProps.getProperty("url"), databaseProps.getProperty("usr"),
					databaseProps.getProperty("psw"));
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return conn;
	}
}
