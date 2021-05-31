<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	Boolean essai = (Boolean) request.getAttribute("essai");
	Integer i = request.getAttribute("choix_nombre") != null ? (Integer) request.getAttribute("choix_nombre") : null;
%>

<!DOCTYPE html>
<html>
<meta charset="ISO-8859-1">
<!-- L'Élément title utile et obligatoire -->
<title>Page Login</title>
<!-- Icone de l'onglet Attention l'extension de l'image doit être en .ico-->


<!-- On relie ensuite nos feuilles de styles CSS -->
<link rel="stylesheet"
	href="C:\Users\Théo Louise\eclipse-workspace2\MonChien\WebContent\static\css\enregistrement.css">

<!-- Ajout de la police 'Concert One' depuis https://fonts.google.com/ -->
<link
	href="C:\Users\Théo Louise\eclipse-workspace2\MonChien\WebContent\static\css\enregistrement.css"
	type="text/css" rel="stylesheet">
<head>


</head>
<body>
	<header>
		
		
		<div
			style="color: #F6F6F6; font-size: 48px; background-color: #696969; padding-top: 1px; padding-left: 30px; padding-bottom: 1px">
			<p>Login</p>
		</div>
	</header>
	<div
		style="margin-top: 0%; margin-bottom: 0%; margin-left: 35%; margin-right: 35%; background-color: #F8F8F8">
		<div style ="margin-left: 20%;">
			<img src="https://unhommeetdeschiens.com/wp-content/uploads/2018/11/logo.png">
		</div>
		<br>
		<h1
			style="font-size: 26px; padding-top: 10px; padding-bottom: 10px; position: relative; text-align: center">Connectez-vous</h1>
		<br>
		<form action="<%=getServletContext().getContextPath()%>/page_login"
			method="post">


			<label for="email">Email:</label><br> <input type="text"
				name="email" id="email"><br> <br> <label
				for="mot_de_passe">Mot de passe:</label><br> <input
				type="password" name="mot_de_passe" id="mot_de_passe"><br>
			<br> <input type="submit" value="Se connecter">

		</form>
		<c:if test="${essai==true}">
		<p style="color: #D77474; font-size: 24px; text-align: center">Login ou mot de passe incorrect</p>
		</c:if>
		<br>
		<h2>Pas encore de compte ?</h2>
		<a href="<%=getServletContext().getContextPath()%>/enregistrement_client">Enregistrement</a>
	</div>


</body>
</html>

