package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Adresse;
import model.Client;

public class AdresseDao {

	private static AdresseDao instance;

	public static AdresseDao getInstance() {
		if (instance == null)
			instance = new AdresseDao();
		return instance;
	}

	private AdresseDao() {

	}

	public Adresse save(Adresse adresse) {

		Integer id = null;

		Statement statement;
		try {
			statement = ConnexionBdd.getConnection().createStatement();
			ResultSet resultSet = statement.executeQuery("select nextval('adresse_id_seq') as id");
			while (resultSet.next()) {
				id = resultSet.getInt("id");
				break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		adresse.setId(id);

		// DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		String requete = "INSERT INTO public.adresse (id,ligne1,ligne2,Lieu,code_postal,ville,pays)"
				+ "VALUES(?,?,?,?,?,?,?)";
		PreparedStatement preparedStatement;
		try {
			preparedStatement = ConnexionBdd.getConnection().prepareStatement(requete);

			preparedStatement.setInt(1, adresse.getId());
			preparedStatement.setString(2, adresse.getLigne1());
			preparedStatement.setString(3, adresse.getLigne2());
			preparedStatement.setString(4, adresse.getLieu());
			preparedStatement.setInt(5, adresse.getCodePostal());
			preparedStatement.setString(6, adresse.getVille());
			preparedStatement.setString(7, adresse.getPays());

			int nbreDeLignesAjour = preparedStatement.executeUpdate();
			System.out.println(nbreDeLignesAjour + " ligne(s) ont été ajouter ");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return adresse;
	}
	
	public Adresse getById(Integer id) {

		Statement statement;
		try {
			statement = ConnexionBdd.getConnection().createStatement();
			ResultSet resultSet = statement.executeQuery(
					"select a.id, a.ligne1, a.ligne2, a.lieu, a.code_postal, a.ville, a.pays from adresse a where a.id="
							+ id);
			while (resultSet.next()) {
				return new Adresse(resultSet.getInt("id"), resultSet.getString("ligne1"), resultSet.getString("ligne2"),
						resultSet.getString("lieu"), resultSet.getInt("code_postal"),resultSet.getString("ville"),resultSet.getString("pays"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

}
