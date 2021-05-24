package test;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import dao.ChienDao;
import dao.ClientDao;
import dao.CompteDao;
import dao.RaceDao;
import model.Chien;
import model.Client;
import model.Compte;
import model.Race;

public class MainsTest {

	public static void main(String[] args) throws ParseException {
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		//System.out.println(ClientDao.getInstance().getById(1));
		Client paulette = new Client(3, "toto", "tata", dateFormat.parse("12-12-1956"), 1);
		//System.out.println(ClientDao.getInstance().getAdresse(paulette));
		Compte comptetheo = new Compte();
		//Client theo = new Client();
		comptetheo = CompteDao.getInstance().login("theolouise@hotmail.fr", "toto");
		System.out.println(comptetheo);
		
		Chien test = new Chien();
		test=ChienDao.getInstance().getById(1);
		String description = ChienDao.getInstance().getDescriptionRace(test);
		System.out.println(description);

	}

}
