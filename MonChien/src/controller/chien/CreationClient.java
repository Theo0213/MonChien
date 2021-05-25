package controller.chien;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ChienDao;
import dao.ClientDao;
import dao.CompteDao;
import model.Chien;
import model.Client;
import model.Compte;

/**
 * Servlet implementation class Acceuil
 */

//http://localhost:8080/MonChien/ajouter_chien

@WebServlet("/enregistrement_client")
public class CreationClient extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreationClient() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("/jsp/enregistrement_client.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String dateNaissanceStr = request.getParameter("date_naissance");
		String idAdresseStr = request.getParameter("id_adresse");
		String email = request.getParameter("email");
		String password = request.getParameter("mot_de_passe");
		
		Date dateNaissance = null;
		try {
			dateNaissance = dateFormat.parse(dateNaissanceStr);
		} catch (ParseException e) {

		}
		int idAdresse = 0;
		idAdresse = Integer.valueOf(idAdresseStr);
		
		Client nvClient = new Client(null, nom, prenom, dateNaissance, idAdresse);

		ClientDao clientdao = ClientDao.getInstance();

		nvClient = clientdao.save(nvClient);
		// trouver compte
		Compte compteConnecte = new Compte(null, email, password, "client", false, true, nvClient.getId());

		CompteDao.getInstance().save(compteConnecte);
		
		System.out.println(compteConnecte);

		


		

		request.getRequestDispatcher("/page_login").forward(request, response);

	}

}
