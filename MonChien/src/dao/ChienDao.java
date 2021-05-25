package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Chien;

public class ChienDao {

	private static ChienDao instance;

	public static ChienDao getInstance() {
		if (instance == null)
			instance = new ChienDao();
		return instance;
	}

	private ChienDao() {

	}

	public List<Chien> getAll() {
		List<Chien> chiens = new ArrayList<Chien>();

		Statement statement;
		try {
			statement = ConnexionBdd.getConnection().createStatement();
			ResultSet resultSet = statement.executeQuery(
					"SELECT c.id,c.numero_puce,c.nom,c.couleur,c.date_naissance, c.id_race FROM chien c;");
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				int numeroPuce = resultSet.getInt("numero_puce");
				String nom = resultSet.getString("nom");
				String couleur = resultSet.getString("couleur");
				Date dateNaissance = resultSet.getDate("date_naissance");
				int idRace = resultSet.getInt("id_race");

				chiens.add(new Chien(id, numeroPuce, nom, couleur, dateNaissance, idRace));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return chiens;

	}
	
	public List<Chien> getAllDisponible() {
		List<Chien> chiens = new ArrayList<Chien>();

		Statement statement;
		try {
			statement = ConnexionBdd.getConnection().createStatement();
			ResultSet resultSet = statement.executeQuery(
					"select c.id,c.numero_puce,c.nom,c.couleur,c.date_naissance, c.id_race from chien c left join adoption a on c.id =a.id_chien where a.id_chien is  null;");
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				int numeroPuce = resultSet.getInt("numero_puce");
				String nom = resultSet.getString("nom");
				String couleur = resultSet.getString("couleur");
				Date dateNaissance = resultSet.getDate("date_naissance");
				int idRace = resultSet.getInt("id_race");

				chiens.add(new Chien(id, numeroPuce, nom, couleur, dateNaissance, idRace));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return chiens;

	}
	
	public List<Chien> getAllEnCours() {
		List<Chien> chiens = new ArrayList<Chien>();

		Statement statement;
		try {
			statement = ConnexionBdd.getConnection().createStatement();
			ResultSet resultSet = statement.executeQuery(
					"SELECT c.id,c.numero_puce,c.nom,c.couleur,c.date_naissance, c.id_race FROM chien c, adoption a where c.id = a.id_chien and a.id_etat_adoption=2;");
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				int numeroPuce = resultSet.getInt("numero_puce");
				String nom = resultSet.getString("nom");
				String couleur = resultSet.getString("couleur");
				Date dateNaissance = resultSet.getDate("date_naissance");
				int idRace = resultSet.getInt("id_race");

				chiens.add(new Chien(id, numeroPuce, nom, couleur, dateNaissance, idRace));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return chiens;

	}

	public Chien getById(Integer id) {

		Statement statement;
		try {
			statement = ConnexionBdd.getConnection().createStatement();
			ResultSet resultSet = statement
					.executeQuery("SELECT id,numero_puce,nom,couleur,date_naissance,id_race FROM chien where id=" + id);
			while (resultSet.next()) {
				return new Chien(resultSet.getInt("id"), resultSet.getInt("numero_puce"), resultSet.getString("nom"),
						resultSet.getString("couleur"), resultSet.getDate("date_naissance"),
						resultSet.getInt("id_race"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public String getDescriptionRace(Chien chien) {
		String description ="";
		int id = chien.getId();
		Statement statement;
		try {
			statement = ConnexionBdd.getConnection().createStatement();
			ResultSet resultSet = statement
					.executeQuery("SELECT (select description from race r where r.id=c.id_race) FROM chien c where id=" + id);
			while (resultSet.next()) {
				description = resultSet.getString("description");
				return description;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public Chien save(Chien p) {

		Integer id = null;

		Statement statement;
		try {
			statement = ConnexionBdd.getConnection().createStatement();
			ResultSet resultSet = statement.executeQuery("select nextval('chien_id_seq') as id");
			while (resultSet.next()) {
				id = resultSet.getInt("id");
				break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		p.setId(id);

		// DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		String requete = "INSERT INTO public.chien (id,numero_puce,nom,couleur,date_naissance,id_race)"
				+ "VALUES(?,?,?,?,?,?)";
		PreparedStatement preparedStatement;
		try {
			preparedStatement = ConnexionBdd.getConnection().prepareStatement(requete);

			preparedStatement.setInt(1, p.getId());
			preparedStatement.setInt(2, p.getNumeroPuce());
			preparedStatement.setString(3, p.getNom());
			preparedStatement.setString(4, p.getCouleur());
			preparedStatement.setDate(5, new java.sql.Date(p.getDateNaissance().getTime()));
			preparedStatement.setInt(6, p.getIdRace());

			int nbreDeLignesAjour = preparedStatement.executeUpdate();
			System.out.println(nbreDeLignesAjour + " ligne(s) ont été ajouter ");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return p;
	}

	public void deleteById(Integer id) {

		String requete = "delete from chien where id= " + id;

		Statement statement;
		try {
			statement = ConnexionBdd.getConnection().createStatement();
			int nbreDeLignesAjour = statement.executeUpdate(requete);
			System.out.println(nbreDeLignesAjour + " ligne(s) ont été supprimées ");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void modify(Chien nvChien) {
		String requete = "UPDATE chien " + "set  numero_puce=?, nom=?, couleur=?,date_naissance=?,id_race=?"
				+ "where id=?";

		PreparedStatement statement;
		try {
			statement = ConnexionBdd.getConnection().prepareStatement(requete);

			statement.setInt(1, nvChien.getNumeroPuce());
			statement.setString(2, nvChien.getNom());
			statement.setString(3, nvChien.getCouleur());
			statement.setDate(4, new java.sql.Date(nvChien.getDateNaissance().getTime()));
			statement.setInt(5, nvChien.getIdRace());
			statement.setInt(6, nvChien.getId());

			int nbreDeLignesAjour = statement.executeUpdate();
			System.out.println(nbreDeLignesAjour + " ligne(s) ont été Maj ");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public Boolean isAdopte(Integer id) {
		PreparedStatement statement;
		String requete= "select exists (select * from adoption a where a.id_chien = ? and a.id_etat_adoption = 3) as est_adopte";
		try {
			statement = ConnexionBdd.getConnection().prepareStatement(requete);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				return resultSet.getBoolean("est_adopte");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
		

	}

}
