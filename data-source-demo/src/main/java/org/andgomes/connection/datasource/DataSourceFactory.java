package org.andgomes.connection.datasource;

import java.io.IOException;
import javax.sql.DataSource;

public interface DataSourceFactory {
	
	public static final String dataSourceFileName = "db.properties";
	
	public DataSource getDataSource() throws IOException;

}
