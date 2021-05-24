package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Client;

public class ClientDao {

	private static ClientDao instance;

	public static ClientDao getInstance() {
		if (instance == null)
			instance = new ClientDao();
		return instance;
	}

	private ClientDao() {

	}

	public List<Client> getAll() {
		List<Client> clients = new ArrayList<Client>();

		Statement statement;
		try {
			statement = ConnexionBdd.getConnection().createStatement();
			ResultSet resultSet = statement.executeQuery(
					"select c.id, c.nom,c.prenom,c.date_naissance, c.id_adresse from client c");
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String nom = resultSet.getString("nom");
				String prenom = resultSet.getString("prenom");
				Date dateNaissance = resultSet.getDate("date_naissance");
				int idadresse = resultSet.getInt("id_adresse");

				clients.add(new Client(id, nom, prenom, dateNaissance, idadresse));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return clients;

	}

	public Client getById(Integer id) {

		Statement statement;
		try {
			statement = ConnexionBdd.getConnection().createStatement();
			ResultSet resultSet = statement.executeQuery(
					"select c.id, c.nom,c.prenom,c.date_naissance, c.id_adresse from client c where id="
							+ id);
			while (resultSet.next()) {
				return new Client(resultSet.getInt("id"), resultSet.getString("nom"), resultSet.getString("prenom"),
						resultSet.getDate("date_naissance"), resultSet.getInt("id_adresse"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public Client save(Client c) {

		Integer id = null;

		Statement statement;
		try {
			statement = ConnexionBdd.getConnection().createStatement();
			ResultSet resultSet = statement.executeQuery("select nextval('client_id_seq') as id");
			while (resultSet.next()) {
				id = resultSet.getInt("id");
				break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		c.setId(id);

		// DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		String requete = "INSERT INTO public.client (id, nom, prenom, date_naissance, id_adresse)" + "VALUES(?,?,?,?,?)";
		PreparedStatement preparedStatement;
		try {
			preparedStatement = ConnexionBdd.getConnection().prepareStatement(requete);

			preparedStatement.setInt(1, c.getId());
			preparedStatement.setString(2, c.getNom());
			preparedStatement.setString(3, c.getPrenom());
			preparedStatement.setDate(4, new java.sql.Date(c.getDateNaissance().getTime()));
			preparedStatement.setInt(5, c.getAdresse());

			int nbreDeLignesAjour = preparedStatement.executeUpdate();
			System.out.println(nbreDeLignesAjour + " ligne(s) ont été ajouter ");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return c;
	}

	public void deleteById(Integer id) {

		String requete = "delete from client where id= " + id;

		Statement statement;
		try {
			statement = ConnexionBdd.getConnection().createStatement();
			int nbreDeLignesAjour = statement.executeUpdate(requete);
			System.out.println(nbreDeLignesAjour + " ligne(s) ont été supprimées ");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void modify(Client nvClient)  {
		String requete = "UPDATE client "
				+ "set  nom=?, prenom=?, date_naissance=?, id_adresse=?"
				+ "where id=?";

		PreparedStatement statement;
		try {
			statement = ConnexionBdd.getConnection().prepareStatement(requete);
			
			statement.setString(1, nvClient.getNom());
			statement.setString(2, nvClient.getPrenom());
			statement.setDate(3, new java.sql.Date(nvClient.getDateNaissance().getTime()));
			statement.setInt(4, nvClient.getAdresse());
			statement.setInt(5, nvClient.getId());
			
			int nbreDeLignesAjour = statement.executeUpdate();
			System.out.println(nbreDeLignesAjour + " ligne(s) ont été Maj ");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	//ne marche pas 
	public String getAdresse(Client client) {
		int idClient = client.getId();
		String adresse = "";
		Statement statement;
		try {
			statement = ConnexionBdd.getConnection().createStatement();
			ResultSet resultSet = statement.executeQuery(
					"select (select concat('Ligne 1 : ',ligne1,' Ligne 2 : ',ligne2,' Lieu : ',Lieu,' Code postale : ',code_postal,' Ville : ',ville,' Pays : ',pays)"
					+ " as Adresse from adresse a where a.id=c.id_adresse)"
					+ " from client c where id="+ idClient);

			while (resultSet.next()) {
				adresse=resultSet.getString("*");
				return adresse;

			}
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	return null;
	}
	
	
}
