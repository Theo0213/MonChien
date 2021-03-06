<%@page import="dao.ChienDao"%>
<%@page import="model.Chien"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%
	List<Chien> chiens = (List) request.getAttribute("chiens");
	String email = (String) request.getAttribute("email");
%>
<html>
<head>
<meta charset="ISO-8859-1">

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">


<title>Liste de Chiens</title>
</head>
<body>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>


	<%
		if (chiens != null && chiens.size() > 0) {
	%>
	
	<div
			style="color: #F6F6F6; font-size: 48px; background-color: #696969; padding-top: 1px; padding-left: 30px; padding-bottom: 1px">
			<p>Espace Conseiller</p>
		</div>
	
	<p>Vous ?tes connect?(e) avec l'adresse : <%=email %></p>
	<div style="position: relative; padding-left: 90%">
		<button>
		<a href="<%=getServletContext().getContextPath()%>/page_login">Se deconnecter</a>
		</button>
	</div>
	<table class="table table-striped">
		<thead>
			<tr>
				<th>id</th>
				<th>N? de Puce</th>
				<th>Nom</th>
				<th>Couleur</th>
				<th>Date de naissance</th>
				<th>Race</th>
			</tr>
		</thead>
		<tbody>
			<%
				for (Chien chien : chiens) {
			%>


			<tr>
				<td><a
					href="<%=getServletContext().getContextPath()%>/details_chien?id_chien=<%=chien.getId()%>"><%=chien.getId()%></a></td>
				<td><%=chien.getNumeroPuce()%></td>
				<td><%=chien.getNom()%></td>
				<td><%=chien.getCouleur()%></td>
				<td><%=chien.getDateNaissance()%></td>
				
				<td><%=ChienDao.getInstance().getDescriptionRace(chien)%></td>
				<td><a
					href="<%=getServletContext().getContextPath()%>/modifier_chien?id_chien=<%=chien.getId()%>">Editer</a></td>
			</tr>
			<%
				}
			%>
		</tbody>
	</table>

	<%
		} else {
	%>
	<p>Pas de r?sultats</p>
	<%
		}
	%>
	<a href="<%=getServletContext().getContextPath()%>/ajouter_chien">Cr?er
		un chien</a>
	<br><br><br>
	<div>
		<a href="<%=getServletContext().getContextPath()%>/adoption_en_cours">Adoptions en Cours</a>
	</div>	
	<br><br><br>
	<div>
		<a href="<%=getServletContext().getContextPath()%>/liste_client">Liste Clients</a>
	</div>	
	
</body>
</html>