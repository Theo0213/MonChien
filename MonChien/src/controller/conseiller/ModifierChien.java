package controller.conseiller;

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
import model.Chien;

/**
 * Servlet implementation class Acceuil
 */

//http://localhost:8080/MonChien/ajouter_chien

@WebServlet("/modifier_chien")
public class ModifierChien extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ModifierChien() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String idChienStr = request.getParameter("id_chien");
		int idChien = 0;
		try {

			idChien = Integer.parseInt(idChienStr);
		} catch (java.lang.NumberFormatException d) {

		}

		Chien chien = ChienDao.getInstance().getById(idChien);

		request.setAttribute("chien", chien);

		request.getRequestDispatcher("/jsp/modifier_chien.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		String idStr = request.getParameter("id_chien");
		Integer id = 0;
		try {
			id = Integer.parseInt(idStr);
		} catch (java.lang.NumberFormatException d) {

		}

		String numeroPuceStr = request.getParameter("numero_puce");
		String nom = request.getParameter("nom");
		String couleur = request.getParameter("couleur");
		String dateNaissanceStr = request.getParameter("date_naissance");
		String idRaceStr = request.getParameter("id_race");

		Date dateNaissance = null;
		try {
			dateNaissance = dateFormat.parse(dateNaissanceStr);
		} catch (ParseException e) {

		}
		int numeroPuce = 0;
		numeroPuce = Integer.parseInt(numeroPuceStr);

		int idRace = 0;
		idRace = Integer.parseInt(idRaceStr);
		
		Chien nvChien = new Chien(id, numeroPuce, nom, couleur, dateNaissance, idRace);

		ChienDao chienDao = ChienDao.getInstance();

		chienDao.modify(nvChien);
		System.out.println(nvChien);

		request.getRequestDispatcher("/liste_chien").forward(request, response);

	}

}
