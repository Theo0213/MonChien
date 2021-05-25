package controller.chien;

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
import model.Client;
import model.Compte;

/**
 * Servlet implementation class DetailsPersonne
 */
@WebServlet("/adoption_chien_validation")
public class AdoptionChienValidation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdoptionChienValidation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idChienStr = request.getParameter("id_chien");
		
		int idChien = Integer.parseInt(idChienStr);
		
		Chien chien = ChienDao.getInstance().getById(idChien);
		
		request.setAttribute("chien", chien);
		
		request.getRequestDispatcher("/jsp/adoption_chien_validation.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String idStr = request.getParameter("id_chien");
		Integer id = 0;
		try {
			id = Integer.parseInt(idStr);
		} catch (java.lang.NumberFormatException d) {

		}
		@SuppressWarnings("deprecation")
		String email = (String) request.getSession().getValue("email");
		@SuppressWarnings("deprecation")
		String password = (String) request.getSession().getValue("mot_de_passe");
		request.setAttribute("email", email);
		
		Compte compte = new Compte();
		compte = CompteDao.getInstance().login(email, password);
		int idClient = compte.getId_client();
		
		
		Date date_util = new Date();
		java.sql.Date date_sql = new java.sql.Date(date_util.getTime());
		Adoption nvAdoption = new Adoption(null, (java.sql.Date) date_sql, idClient,id, 2);
		AdoptionDao.getInstance().save(nvAdoption);

		
		request.getRequestDispatcher("/vitrine_chien").forward(request, response);
	}

}
