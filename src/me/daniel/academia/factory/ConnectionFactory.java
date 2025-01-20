package me.daniel.academia.factory;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

	private static final String USERNAME = "user";
	private static final String PASSWORD = "user";
	private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/academia";

	public static Connection conectar() throws Exception {

		Class.forName("com.mysql.cj.jdbc.Driver");

		Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);

		return connection;

	}

}
