package com.tosan.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class JdbcConnection {

	public Connection connection;

	public Statement statement;

	private static JdbcConnection jdbcConnection;

	private static final String jdbcUrl = "jdbc:mysql://localhost:3306/Tosan?useSSL=false";

	private static final String user = "tosanuser";

	private static final String pass = "tosanuser";

	private JdbcConnection() {
		try {
			connection = DriverManager.getConnection(jdbcUrl, user, pass);
			statement = connection.createStatement();
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}

	// new constructor
	public static JdbcConnection getJdbcConnection() {

		if (jdbcConnection == null) {
			jdbcConnection = new JdbcConnection();
		}
		return jdbcConnection;
	}
}
