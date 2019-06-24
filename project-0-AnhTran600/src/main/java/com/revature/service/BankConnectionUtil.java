package com.revature.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class BankConnectionUtil {

private static final Logger LOGGER = Logger.getLogger(BankConnectionUtil.class);
	
	public static Connection getConnection() throws SQLException {
		String url = "jdbc:oracle:thin:@admin.c3t4snnggml8.us-east-1.rds.amazonaws.com:1521:ORCL";
		String username = "admin";
		String password = "password";
		
		return DriverManager.getConnection(url, username, password);
	}
	
	public static void main(String[] args) {
		try {
			getConnection();
			LOGGER.info("Connection successful");
		} catch (SQLException e) {
			LOGGER.error("Could not connect.",e);
		}
	}
}
