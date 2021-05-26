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

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<title>Enregistrement</title>
</head>
<body style ="background-color: #F8F8F8">

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>


<h3 style="margin-top: 5%; margin-bottom: 0%; margin-left: 25%; margin-right: 20%; background-color: #F8F8F8">Veuillez renseignez les informations du nouvel utilisateur</h3><br>		

<div style="margin-top: 0%; margin-bottom: 0%; margin-left: 35%; margin-right: 35%; background-color: #e0e0e0">	
<a href="<%=getServletContext().getContextPath()%>/liste_client">Retour</a>


		<form action="<%=getServletContext().getContextPath()%>/enregistrement_conseiller_client"
			method="post">
		
			<label>Nom : </label>
			<input type="text" name="nom" placeholder="Nom"><br>
			<label>Prénom : </label>
			<input type="text" name="prenom" placeholder="Prenom"><br> 
			<label>Date de naissance : </label>
			<input type="date" name="date_naissance"><br> 
			
			<label>Role : </label>
			<select name="role" required>
				<option value="" selected disabled>Choix role</option>
				<option value="client">Client</option>
				<option value="conseiller">Conseiller</option>

       		</select><br> 
			
			
			<label>Numero de téléphone 1 : </label>
			<input type="text" name="ligne1" placeholder="Ligne 1"><br>
			<label>Numero de téléphone 2 : </label>
			<input type="text" name="ligne2" placeholder="Ligne 2"><br>
			<label>Lieu de résidence : </label> 
			<input type="text" name="lieu" placeholder="Lieu"><br>
			<label>Code postal : </label>
			<input type="text" name="code_postal" placeholder="Code postal"><br> 
			<label>Ville : </label>
			<input type="text" name="ville" placeholder="Ville"><br>
			<label>Pays : </label>
			<input type="text" name="pays" placeholder="Pays"><br>
			
			<label>Email : </label>
			<input type="email" name="email" placeholder="Email"><br> 
			<label>Mot de passe : </label>
			<input type="password" name="mot_de_passe" placeholder="Mot de passe"><br> 
			
			<input	type="submit" value="Submit"> <input type="reset"
				value="Reset">
		</form>
</div>
</body>
</html>