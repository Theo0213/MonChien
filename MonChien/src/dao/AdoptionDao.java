package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Adoption;
import model.Chien;
import model.Client;


public class AdoptionDao {

	private static AdoptionDao instance;

	public static AdoptionDao getInstance() {
		if (instance == null)
			instance = new AdoptionDao();
		return instance;
	}

	private AdoptionDao() {

	}

	
	public void deleteByIdChien(Integer idChien) {

		String requete = "delete from adoption a where a.id_chien=" + idChien;

		Statement statement;
		try {
			statement = ConnexionBdd.getConnection().createStatement();
			int nbreDeLignesAjour = statement.executeUpdate(requete);
			System.out.println(nbreDeLignesAjour + " ligne(s) ont été supprimées ");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Adoption> getAll() {
		List<Adoption> adoptions = new ArrayList<Adoption>();

		Statement statement;
		try {
			statement = ConnexionBdd.getConnection().createStatement();
			ResultSet resultSet = statement.executeQuery("select id, date_adoption, id_client, id_chien, id_etat_adoption from adoption");
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				java.sql.Date dateAdoption = resultSet.getDate("date_adoption");
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
	
	public List<Adoption> getEnCours() {
		List<Adoption> adoptions = new ArrayList<Adoption>();

		Statement statement;
		try {
			statement = ConnexionBdd.getConnection().createStatement();
			ResultSet resultSet = statement.executeQuery("select id, date_adoption, id_client, id_chien, id_etat_adoption from adoption where id_etat_adoption=2");
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				java.sql.Date dateAdoption = resultSet.getDate("date_adoption");
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
	
	public List<Adoption> getDisponible() {
		List<Adoption> adoptions = new ArrayList<Adoption>();

		Statement statement;
		try {
			statement = ConnexionBdd.getConnection().createStatement();
			ResultSet resultSet = statement.executeQuery("select id, date_adoption, id_client, id_chien, id_etat_adoption from adoption where id_etat_adoption=1");
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				java.sql.Date dateAdoption = resultSet.getDate("date_adoption");
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
	
	@SuppressWarnings("null")
	public Adoption save(Adoption adoption) {

		Integer id = null;

		Statement statement;
		try {
			statement = ConnexionBdd.getConnection().createStatement();
			ResultSet resultSet = statement.executeQuery("select nextval('adoption_seq') as id");
			while (resultSet.next()) {
				id = resultSet.getInt("id");
				break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		adoption.setId(id);

		// DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		String requete = "INSERT INTO public.adoption (id,date_adoption,id_client,id_chien,id_etat_adoption)"
				+ "VALUES(?,?,?,?,?)";
		PreparedStatement preparedStatement;
		try {
			preparedStatement = ConnexionBdd.getConnection().prepareStatement(requete);

			preparedStatement.setInt(1, adoption.getId());
			preparedStatement.setDate(2, (java.sql.Date) adoption.getDateAdoption());
			preparedStatement.setInt(3, adoption.getIdClient());

			preparedStatement.setInt(4, adoption.getIdChien());
			preparedStatement.setInt(5, adoption.getIdEtatAdoption());

			int nbreDeLignesAjour = preparedStatement.executeUpdate();
			System.out.println(nbreDeLignesAjour + " ligne(s) ont été ajouter ");
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return adoption;
	}
	
	public void ComfirmerAdoption(Integer idChien, Integer idClient)  {
		String requete = "update adoption set id_etat_adoption =3 where id_chien = ? and id_client = ?";

		PreparedStatement statement;
		try {
			statement = ConnexionBdd.getConnection().prepareStatement(requete);
			
			statement.setInt(1, idChien);
			statement.setInt(2, idClient);
			
			int nbreDeLignesAjour = statement.executeUpdate();
			System.out.println(nbreDeLignesAjour + " ligne(s) ont été Maj ");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

}
