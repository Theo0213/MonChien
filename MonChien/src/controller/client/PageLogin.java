package controller.client;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CompteDao;
import model.Compte;

/**
 * Servlet implementation class PageLogin
 */

//http://localhost:8080/MonChien/page_login

@WebServlet("/page_login")
public class PageLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PageLogin() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("/jsp/page_login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String email = request.getParameter("email");
		String password = request.getParameter("mot_de_passe");
		// trouver compte
		Compte compteConnecte = new Compte();
		compteConnecte = CompteDao.getInstance().login(email, password);

		
		HttpSession session = request.getSession();
		session.setAttribute("email", email);
		session.setAttribute("mot_de_passe", password);
		

		// trouver role
		try {
			if (compteConnecte.getRole().equalsIgnoreCase("conseiller")) {
				request.getRequestDispatcher("/liste_chien").forward(request, response);
			}
			if (compteConnecte.getRole().equalsIgnoreCase("client")) {
				request.getRequestDispatcher("/vitrine_chien").forward(request, response);
			}
		}catch(NullPointerException e){
			
			System.out.println("Compte non trouvé");
			request.getRequestDispatcher("/jsp/page_login.jsp").forward(request, response);
		}

	}

}
