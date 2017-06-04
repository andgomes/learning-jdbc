package org.andgomes.fileimport;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.List;

import org.andgomes.connection.ConnectionFactory;
import org.andgomes.model.Interviewed;
import org.andgomes.reader.InterviewReader;

public class FileImport {

	public static void importFile(ConnectionFactory connFactory, File file)
			throws SQLException, IOException {
		
		Connection connection = null;
		
		try {
			
			connection = connFactory.getConnection();
			
			connection.setAutoCommit(false);
			
			String sql = "INSERT INTO census (id, name, first_grade, " +
					"second_grade) VALUES (?, ?, ?, ?)";
			
			try (PreparedStatement stmt = connection.prepareStatement(sql)) {
				
				List<Interviewed> census = InterviewReader.readInterviews(file);
				
				int batchSize = 0;
				
				for (Interviewed interviewed : census) {
					
					stmt.setInt(1, interviewed.getId());
					stmt.setString(2, interviewed.getName());
					stmt.setDouble(3, interviewed.getFirstGrade());
					stmt.setDouble(4, interviewed.getSecondGrade());
					
					stmt.addBatch();
					
					++batchSize;
					
					if (batchSize == 200) {
						
						stmt.executeBatch();
						batchSize = 0;
						
					}
				
				}
				
				if (batchSize > 0) {
			
					stmt.executeBatch();
					batchSize = 0;
				
				}
			
			}
			
			connection.commit();
		
		} catch (SQLException e) {
		
			connection.rollback();
			connection.close();
			
			throw e;
			
		}
	
	}

}
