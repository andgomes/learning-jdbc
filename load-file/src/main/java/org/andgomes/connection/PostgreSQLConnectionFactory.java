package org.andgomes.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgreSQLConnectionFactory implements ConnectionFactory {

	public Connection getConnection() throws SQLException {
	
		Connection conn = DriverManager.getConnection(
				"jdbc:postgresql://127.0.0.1/postgres", "postgres", "admin");
		
		return conn;
		
	}

}
