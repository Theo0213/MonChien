package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Client;
import model.Compte;

public class CompteDao {
	
	private static CompteDao instance;

	public static CompteDao getInstance() {
		if (instance == null)
			instance = new CompteDao();
		return instance;
	}

	private CompteDao() {

	}

	public Compte login(String email, String password) {

		PreparedStatement statement;
		try {

			statement = ConnexionBdd.getConnection().prepareStatement(
					"select c.id, c.email , c.mot_de_passe, c.poste, c.est_bloquee, c.est_active, c.id_client"
							+ " from compte c where c.email=? and mot_de_passe =?");

			statement.setString(1, email);
			statement.setString(2, password);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				return new Compte(resultSet.getInt("id"), resultSet.getString("email"),
						resultSet.getString("mot_de_passe"), resultSet.getString("poste"),
						resultSet.getBoolean("est_bloquee"), resultSet.getBoolean("est_active"),resultSet.getInt("id_client"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch(NullPointerException f) {
			return null;
		}

		return null;
	}
	
	public Client getClient(Compte compte) {
		
		
		
		PreparedStatement statement;
		try {
			int idCompteClient = compte.getId_client();
			statement = ConnexionBdd.getConnection().prepareStatement(
					"select c.id, c.nom,c.prenom,c.date_naissance,c.id_adresse from client c,compte o where c.id = o.id_client and o.id_client=?");

			statement.setInt(1, idCompteClient);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				return new Client(resultSet.getInt("id"), resultSet.getString("nom"), resultSet.getString("prenom"),
						resultSet.getDate("date_naissance"), resultSet.getInt("id_adresse"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException f) {
			System.out.println("Ce compte n'existe pas");
		}
		
		
		return null;
		
	}
	
	

}
