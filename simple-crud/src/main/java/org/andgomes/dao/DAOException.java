package org.andgomes.dao;

public class DAOException extends Exception {

	public DAOException(Throwable e) {
		super(e);
	}
	
	public DAOException(String message) {
		super(message);
	}
	
	public DAOException() {}

}
