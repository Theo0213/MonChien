package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Race;

public class RaceDao {

	private static RaceDao instance;

	public static RaceDao getInstance() {
		if (instance == null)
			instance = new RaceDao();
		return instance;
	}

	private RaceDao() {

	}

	public List<Race> getAll() {
		List<Race> races = new ArrayList<Race>();

		Statement statement;
		try {
			statement = ConnexionBdd.getConnection().createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT id,description FROM race");
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String description = resultSet.getString("description");
				races.add(new Race(id, description));
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return races;

	}

}
