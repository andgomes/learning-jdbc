package org.andgomes.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.andgomes.model.Club;
import org.andgomes.connection.ConnectionFactory;

public class DatabaseClubDAO implements ClubDAO {
	
	private ConnectionFactory factory;
	
	public DatabaseClubDAO(ConnectionFactory factory) {
		this.factory = factory;
	}
	
	public List<Club> list() throws DAOException {
		
		List<Club> clubs = new LinkedList<>();		
		
		try (Connection conn = factory.createConnection()) {
		
			String sql = "SELECT * FROM clubs";
			
			try (PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {
			
				while (rs.next()) {
				
					Club club = new Club();
					
					club.setId(rs.getInt("id"));
					club.setName(rs.getString("name"));
					
					clubs.add(club);
				
				}
			
			}
		
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		
		return clubs;
	
	}
	
	public void add(Club club) throws DAOException {
	
		try (Connection conn = factory.createConnection()) {
		
			String sql = "INSERT INTO clubs (name) VALUES (?)";
			
			try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			
				stmt.setString(1, club.getName());
				
				stmt.execute();
			
			}
		
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	
	}
	
	public void update(Club club) throws DAOException {
	
		try (Connection conn = factory.createConnection()) {
		
			String sql = "UPDATE clubs SET name = ? WHERE id = ?";
			
			try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			
				stmt.setString(1, club.getName());
				stmt.setInt(2, club.getId());
				
				stmt.execute();
			
			}
		
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	
	}
	
	public void remove(Club club) throws DAOException {
	
		try (Connection conn = factory.createConnection()) {
		
			String sql = "DELETE FROM clubs WHERE id = ?";
			
			try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			
				stmt.setInt(1, club.getId());
				
				stmt.execute();
			
			}
		
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	
	}
	
	public Club findById(int id) throws DAOException {
		
		Club club = null;
		
		try (Connection conn = factory.createConnection()) {
		
			String sql = "SELECT * FROM clubs WHERE id = ?";
			
			try (PreparedStatement stmt = conn.prepareStatement(sql)) {
				
				stmt.setInt(1, id);
				
				try (ResultSet rs = stmt.executeQuery()) {
				
					if (rs.next()) {
					
						club = new Club();
					
						club.setId(rs.getInt("id"));
						club.setName(rs.getString("name"));
					
					}
				
				}
			
			}
		
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		
		return club;
	
	}

}
