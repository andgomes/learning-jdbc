package org.andgomes;

import org.andgomes.model.Club;
import org.andgomes.connection.ConnectionFactory;
import org.andgomes.connection.PostgreSQLConnectionFactory;
import org.andgomes.dao.ClubDAO;
import org.andgomes.dao.DatabaseClubDAO;
import org.andgomes.dao.DAOException;

import java.util.List;
import java.util.Scanner;

public class Main {
	
	private static ConnectionFactory conn = new PostgreSQLConnectionFactory();
	private static ClubDAO clubDAO = new DatabaseClubDAO(conn);
	
	private static Scanner input = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		try {
			
			int choose;
			
			do {
			
				System.out.println(menu());
			
				System.out.print("Choose: ");
				choose = input.nextInt();
				input.nextLine();
		
				switch (choose) {
		
					case 1: listClubs(); break;
					
					case 2: addClub(); break;
					
					case 3: updateClub(); break;
					
					case 4: removeClub(); break;
					
					case 5: findClub(); break;
		
				}
			
			} while (choose != 6);
			
		} catch (DAOException e) {
			e.printStackTrace();
		}
	
	}
	
	private static String menu() {
	
		return String.format("1 - %s\n2 - %s\n3 - %s\n4 - %s\n5 - %s\n6 - %s",
				"List Clubs", "Add Club", "Update Club", "Remove Club",
				"Find Club", "Exit");
	
	}
	
	private static void listClubs() throws DAOException {
	
		List<Club> clubs = clubDAO.list();
		
		for (Club club : clubs) {
		
			System.out.println("Id: " + club.getId());
			System.out.println("Name: " + club.getName());
			
			System.out.println();

		}			
	
	}
	
	private static void addClub() throws DAOException {
	
		System.out.print("Enter the club's name: ");
		String clubName = input.nextLine();
		
		Club club = new Club();
		club.setName(clubName);
		
		clubDAO.add(club);
	
	}
	
	private static void updateClub() throws DAOException {
	
		System.out.print("Enter the club's ID: ");
		int clubId = input.nextInt();
		input.nextLine();
		
		System.out.print("Enter the updated club's name: ");
		String clubName = input.nextLine();
		
		Club club = new Club();
		club.setId(clubId);
		club.setName(clubName);
		
		clubDAO.update(club);
	
	}
	
	private static void removeClub() throws DAOException {
	
		System.out.print("Enter the club's ID: ");
		int clubId = input.nextInt();
		input.nextLine();
		
		Club club = new Club();
		club.setId(clubId);
		
		clubDAO.remove(club);
	
	}
	
	private static void findClub() throws DAOException {
	
		System.out.print("Enter the club's ID: ");
		int clubId = input.nextInt();
		input.nextLine();
		
		Club club = clubDAO.findById(clubId);
		
		System.out.println("Club name: " + club.getName());
	
	}

}
