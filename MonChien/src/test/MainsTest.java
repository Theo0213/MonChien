package test;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dao.AdoptionDao;
import dao.ChienDao;
import dao.ClientDao;
import dao.CompteDao;
import dao.RaceDao;
import model.Adoption;
import model.Chien;
import model.Client;
import model.Compte;
import model.Race;

public class MainsTest {

	public static void main(String[] args) throws ParseException {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		//System.out.println(ClientDao.getInstance().getById(1));
		Client paulette = new Client(null, "toto", "tata", dateFormat.parse("12-12-1956"), 1);
		ClientDao.getInstance().save(paulette);
		Chien chien = new Chien(null, 789456, "test", "rouge", dateFormat.parse("12-12-1956"),1);
		ChienDao.getInstance().save(chien);
		//System.out.println(ClientDao.getInstance().getAdresse(paulette));
		Compte comptetheo = new Compte();
		//Client theo = new Client();
		comptetheo = CompteDao.getInstance().login("theolouise@hotmail.fr", "toto");
		System.out.println(comptetheo);
		
		Chien test = new Chien();
		test=ChienDao.getInstance().getById(5);
		List<Adoption> adoptions = new ArrayList<Adoption>();
		adoptions = AdoptionDao.getInstance().getAll();
		System.out.println(adoptions);
		
		System.out.println(ChienDao.getInstance().isAdopte(1));
		System.out.println(ChienDao.getInstance().isAdopte(3));
		
		
		Date date_util = new Date();
		java.sql.Date date_sql = new java.sql.Date(date_util.getTime());
		Adoption nvAdoption = new Adoption(null, (java.sql.Date) date_sql, 4,4, 1);
		AdoptionDao.getInstance().save(nvAdoption);

	}

}
