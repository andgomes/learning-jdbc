package org.andgomes.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgreSQLConnectionFactory implements ConnectionFactory {

	public Connection createConnection() throws SQLException {
	
		Connection connection = DriverManager.getConnection(
				"jdbc:postgresql://localhost/postgres", "postgres", "admin");
		
		return connection;
		
	}

}
