package controller.chien;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AdoptionDao;
import dao.ChienDao;
import dao.CompteDao;
import model.Chien;

/**
 * Servlet implementation class AdoptionEnCours
 */
@WebServlet("/adoption_en_cours")
public class AdoptionEnCours extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdoptionEnCours() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Chien> chiens = ChienDao.getInstance().getAllEnCours();
		request.setAttribute("chiens", chiens);
		@SuppressWarnings("deprecation")
		String email = (String) request.getSession().getValue("email");
		@SuppressWarnings("deprecation")
		String password = (String) request.getSession().getValue("mot_de_passe");
		request.setAttribute("email", email);
		
		if(CompteDao.getInstance().login(email, password)!=null && CompteDao.getInstance().login(email, password).getRole().equals("conseiller")) {
			request.getRequestDispatcher("/jsp/adoption_en_cours.jsp").forward(request, response);
		}else {
			request.getRequestDispatcher("/jsp/page_erreur.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		@SuppressWarnings("deprecation")
		Integer idChien = (Integer) request.getSession().getValue("id_chien");
		

		@SuppressWarnings("deprecation")
		Integer idClient = (Integer) request.getSession().getValue("id_client");
		
		System.out.println(idChien);
		System.out.println(idClient);
		AdoptionDao.getInstance().ComfirmerAdoption(idChien, idClient);
		request.getRequestDispatcher("/liste_chien").forward(request, response);
		
	}

}
