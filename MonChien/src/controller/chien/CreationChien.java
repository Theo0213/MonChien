package controller.chien;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ChienDao;
import dao.RaceDao;
import model.Chien;
import model.Race;

/**
 * Servlet implementation class Acceuil
 */

//http://localhost:8080/MonChien/ajouter_chien

@WebServlet("/ajouter_chien")
public class CreationChien extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreationChien() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("/jsp/ajouter_chien.jsp").forward(request, response);
		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

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
		numeroPuce = Integer.valueOf(numeroPuceStr);
		
		
		int idRace = 0;
		idRace = Integer.valueOf(idRaceStr);

		Chien nvChien = new Chien(null, numeroPuce, nom, couleur, dateNaissance, idRace);

		ChienDao chienDao = ChienDao.getInstance();

		nvChien = chienDao.save(nvChien);

		request.getRequestDispatcher("/liste_chien").forward(request, response);

	}

}
