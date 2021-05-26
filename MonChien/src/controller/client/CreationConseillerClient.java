package controller.client;

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

import dao.AdresseDao;
import dao.ClientDao;
import dao.CompteDao;
import model.Adresse;
import model.Client;
import model.Compte;

/**
 * Servlet implementation class Acceuil
 */

//http://localhost:8080/MonChien/ajouter_chien

@WebServlet("/enregistrement_conseiller_client")
public class CreationConseillerClient extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreationConseillerClient() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("/jsp/enregistrement_conseiller_client.jsp").forward(request, response);

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
		String role = request.getParameter("role");

		String email = request.getParameter("email");
		String password = request.getParameter("mot_de_passe");
		
		String ligne1 = request.getParameter("ligne1");
		String ligne2 = request.getParameter("ligne2");
		String lieu = request.getParameter("lieu");
		String codePostalStr = request.getParameter("code_postal");
		int codePostal = Integer.parseInt(codePostalStr);
		String ville = request.getParameter("ville");
		String pays = request.getParameter("pays");

		Date dateNaissance = null;
		try {
			dateNaissance = dateFormat.parse(dateNaissanceStr);
		} catch (ParseException e) {

		}
		
		Adresse nvAdresse = new Adresse(null,ligne1,ligne2,lieu,codePostal,ville,pays);
		nvAdresse = AdresseDao.getInstance().save(nvAdresse);
		int idAdresse = nvAdresse.getId();
		

		Client nvClient = new Client(null, nom, prenom, dateNaissance, idAdresse);

		ClientDao clientdao = ClientDao.getInstance();

		nvClient = clientdao.save(nvClient);
		// trouver compte
		Compte compteConnecte = new Compte(null, email, password, role, false, true, nvClient.getId());

		CompteDao.getInstance().save(compteConnecte);

		System.out.println(compteConnecte);

		request.getRequestDispatcher("/liste_client").forward(request, response);

	}

}
