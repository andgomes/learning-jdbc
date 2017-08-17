package org.andgomes.connection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import org.postgresql.ds.PGPoolingDataSource;

import org.andgomes.connection.datasource.DataSourceFactory;
import org.andgomes.connection.datasource.PostgreSQLDataSource;

public class PGDataSourceConnectionFactory implements ConnectionFactory {
	
	private DataSourceFactory dsFactory = new PostgreSQLDataSource();
	
	public Connection getConnection() throws SQLException {
		
		try {
			return dsFactory.getDataSource().getConnection();
		} catch (IOException e) {
		
			throw new SQLException("Erro ao ler o arquivo " +
					DataSourceFactory.dataSourceFileName);
			
		}
	
	} 

}
