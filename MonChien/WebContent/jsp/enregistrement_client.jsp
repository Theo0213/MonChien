<%@page import="dao.ClientDao"%>
<%@page import="model.Client"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="dao.RaceDao"%>
<%@page import="model.Race"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="toto"%>
<%
	List<Client> clients = new ArrayList<Client>();
	clients= ClientDao.getInstance().getAll();
	request.setAttribute("clients", clients);
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title></title>
</head>
<body>
	<h3>Veuillez saisir vos informations personnels</h3>
	<form action="<%=getServletContext().getContextPath()%>/enregistrement_client"
		method="post">


		<input type="text" name="nom" placeholder="Nom"><br>
		<input type="text" name="prenom" placeholder="Prenom"><br> 
		<input type="date" name="date_naissance"><br> 
		<select name="id_adresse" required>
			<option value=1>1</option>
			<option value=2>2</option>
			<option value=3>3</option>
		</select> <br> 
		<input type="text" name="email" placeholder="Email"><br> 
		<input type="text" name="mot_de_passe" placeholder="Mot de passe"><br> 
		
		<input	type="submit" value="Submit"> <input type="reset"
			value="Reset">
	</form>

</body>
</html>