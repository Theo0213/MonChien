package controller.client;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ChienDao;
import dao.CompteDao;
import model.Chien;
import model.Compte;

/**
 * Servlet implementation class ListePersonne
 */

//http://localhost:8080/MonChien/liste_chien

@WebServlet("/adoptions_client_comfirme")
public class AdoptionClientComfirme extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdoptionClientComfirme() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		@SuppressWarnings("deprecation")
		String email = (String) request.getSession().getValue("email");
		@SuppressWarnings("deprecation")
		String password = (String) request.getSession().getValue("mot_de_passe");
		request.setAttribute("email", email);
		
		Compte compteconnecte = new Compte();
		compteconnecte = CompteDao.getInstance().login(email, password);
		int idClient = compteconnecte.getId_client();

		
		
		List<Chien> chiens = ChienDao.getInstance().getAllComfirmePerso(idClient);
		request.setAttribute("chiens", chiens);
		
		if(CompteDao.getInstance().login(email, password)!=null && CompteDao.getInstance().login(email, password).getRole().equals("client")) {
			request.getRequestDispatcher("/jsp/adoptions_client_comfirme.jsp").forward(request, response);
		}else {
			request.getRequestDispatcher("/jsp/page_erreur.jsp").forward(request, response);
		}
		
		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
