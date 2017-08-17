package org.andgomes.connection.datasource;

import java.io.InputStream;
import java.io.IOException;
import java.util.Properties;
import javax.sql.DataSource;
import org.postgresql.ds.PGPoolingDataSource;

public class PostgreSQLDataSource implements DataSourceFactory {
	
	public DataSource getDataSource() throws IOException {
	
		try (InputStream is = getClass().getClassLoader().getResourceAsStream(
				DataSourceFactory.dataSourceFileName)) {
			
			Properties prop = new Properties();
			prop.load(is);
			
			PGPoolingDataSource dataSource = new PGPoolingDataSource();
			
			System.out.println(prop.getProperty("serverName"));
			System.out.println(prop.getProperty("databaseName"));
			System.out.println(prop.getProperty("portNumber"));
			System.out.println(prop.getProperty("user"));
			System.out.println(prop.getProperty("password"));
			System.out.println(prop.getProperty("dataSourceName"));
			System.out.println(prop.getProperty("initialConnections"));
			System.out.println(prop.getProperty("maxConnections"));
			
			String serverName = prop.getProperty("serverName");
			String databaseName = prop.getProperty("databaseName");
			int portNumber = Integer.parseInt(prop.getProperty("portNumber"));
			String user = prop.getProperty("user");
			String password = prop.getProperty("password");
			String dataSourceName = prop.getProperty("dataSourceName");
			int initialConnections = Integer.parseInt(
					prop.getProperty("initialConnections"));
			int maxConnections = Integer.parseInt(
					prop.getProperty("maxConnections"));
			
			dataSource.setServerName(serverName);
			dataSource.setDatabaseName(databaseName);
			dataSource.setPortNumber(portNumber);
			dataSource.setUser(user);
			dataSource.setPassword(password);
			dataSource.setDataSourceName(dataSourceName);
			dataSource.setInitialConnections(initialConnections);
			dataSource.setMaxConnections(maxConnections);
			
			return dataSource;
			
		}
	
	}

}
