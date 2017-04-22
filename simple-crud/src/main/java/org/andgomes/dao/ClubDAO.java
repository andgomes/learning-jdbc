package org.andgomes.dao;

import java.util.List;
import org.andgomes.model.Club;

public interface ClubDAO {

	public List<Club> list() throws DAOException;
	
	public void add(Club club) throws DAOException;
	
	public void update(Club club) throws DAOException;
	
	public void remove(Club club) throws DAOException;
	
	public Club findById(int id) throws DAOException;

}
