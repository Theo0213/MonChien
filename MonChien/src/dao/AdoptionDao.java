package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Adoption;


public class AdoptionDao {

	private static AdoptionDao instance;

	public static AdoptionDao getInstance() {
		if (instance == null)
			instance = new AdoptionDao();
		return instance;
	}

	private AdoptionDao() {

	}

	public List<Adoption> getAll() {
		List<Adoption> adoptions = new ArrayList<Adoption>();

		Statement statement;
		try {
			statement = ConnexionBdd.getConnection().createStatement();
			ResultSet resultSet = statement.executeQuery("select id, date_adoption, id_client, id_chien, id_etat_adoption from adoption");
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				Date dateAdoption = resultSet.getDate("date_adoption");
				int idClient = resultSet.getInt("id_client");
				int idChien = resultSet.getInt("id_chien");
				int idEtatAdoption = resultSet.getInt("id_etat_adoption");

				
				adoptions.add(new Adoption(id, dateAdoption,idClient,idChien,idEtatAdoption));
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return adoptions;

	}

}
