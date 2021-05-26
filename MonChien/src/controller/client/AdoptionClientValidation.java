package controller.client;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AdoptionDao;
import dao.ChienDao;
import dao.CompteDao;
import model.Adoption;
import model.Chien;
import model.Compte;

/**
 * Servlet implementation class DetailsPersonne
 */
@WebServlet("/adoption_client_validation")
public class AdoptionClientValidation extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdoptionClientValidation() {
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

		int idChien = Integer.parseInt(idChienStr);

		Chien chien = ChienDao.getInstance().getById(idChien);

		request.setAttribute("chien", chien);

		request.getRequestDispatcher("/jsp/adoption_client_validation.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idStr = request.getParameter("id_chien");
		Integer idChien = 0;
		try {
			idChien = Integer.parseInt(idStr);
		} catch (java.lang.NumberFormatException d) {

		}
		@SuppressWarnings("deprecation")
		String email = (String) request.getSession().getValue("email");

		request.setAttribute("email", email);

		

		AdoptionDao.getInstance().deleteByIdChien(idChien);

		request.getRequestDispatcher("/adoptions_client").forward(request, response);
	}

}
