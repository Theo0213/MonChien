<%@page import="model.Adresse"%>
<%@page import="dao.AdresseDao"%>
<%@page import="dao.ClientDao"%>
<%@page import="model.Client"%>
<%@page import="dao.ChienDao"%>
<%@page import="model.Chien"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%
	List<Client> clients = (List) request.getAttribute("clients");
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
		if (clients != null && clients.size() > 0) {
	%>
	
	<div
			style="color: #F6F6F6; font-size: 48px; background-color: #696969; padding-top: 1px; padding-left: 30px; padding-bottom: 1px">
			<p>Espace Conseiller</p>
		</div>
	
	<p>Vous êtes connecté(e) avec l'adresse : <%=email %></p>

<div>
	
	<table class="table table-striped">
		<thead>
			<tr>
				<th>id</th>
				<th>Nom</th>
				<th>Prenom</th>
				<th>Date de naissance</th>
				<th>Ligne 1</th>
				<th>Ligne 2</th>
				<th>Lieu</th>
				<th>Code postal</th>
				<th>Ville</th>
				<th>Pays</th>
			</tr>
		</thead>
		<tbody>
			<%
				for (Client client : clients) {
			%>


			<tr>
				<td><%=client.getId()%></td>
				<td><%=client.getNom()%></td>
				<td><%=client.getPrenom()%></td>
				<td><%=client.getDateNaissance()%></td>
				<%int idAdresse = client.getAdresse();%>
				<%Adresse adresse = AdresseDao.getInstance().getById(idAdresse);%>
				<td><%=adresse.getLigne1()%></td>
				<td><%=adresse.getLigne2()%></td>
				<td><%=adresse.getLieu()%></td>
				<td><%=adresse.getCodePostal()%></td>
				<td><%=adresse.getVille()%></td>
				<td><%=adresse.getPays()%></td>
				
			</tr>
			<%
				}
			%>
		</tbody>
	</table>

</div>

	<%
		} else {
	%>
	<p>Pas de résultats</p>
	<%
		}
	%>
	<a href="<%=getServletContext().getContextPath()%>/enregistrement_conseiller_client">Nouvel utilisateur</a>


	<br><br><br>
	<div>
		<a href="<%=getServletContext().getContextPath()%>/liste_chien">Retour</a>
	</div>	
	
</body>
</html>