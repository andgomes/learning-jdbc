package org.andgomes.connection;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionFactory {

	public Connection createConnection() throws SQLException;

}
