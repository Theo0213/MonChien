package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnexionBdd {

	private static Connection connection;

	public static Connection getConnection() throws SQLException {
		if (connection == null) {
			
				try {
					Class.forName("org.postgresql.Driver");
					connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/cda_21013", "postgres", "root");
					System.out.println("Connected to PostgreSQL database!");
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				
			
			
		}
		return connection;
	}
	
}


