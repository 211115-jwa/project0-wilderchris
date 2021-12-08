package com.revature.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAOUtilities {
private static final String userName_DB = "postgres";
private static final String password_DB = "tarheel";
private static final String URL = "jdbc:postgresql://localhost:5432/BikeShopDB";
private static Connection connection;


//jdbc:postgresql://hostname:port/databaseName
public static Connection getConnection() throws SQLException {
	if (connection == null) {
	
		try {
			Class.forName("org.postgresql.Driver");	
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	
	connection = DriverManager.getConnection(URL,userName_DB,password_DB);
	}
	//if closed?
	return connection;
	
}




}
