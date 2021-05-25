<%@page import="javax.xml.ws.handler.MessageContext.Scope"%>
<%@page import="model.Client"%>
<%@page import="dao.ClientDao"%>
<%@page import="dao.ChienDao"%>
<%@page import="model.Chien"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="toto"%>
	
<!DOCTYPE html>
<%
	List<Chien> chiens = (List) request.getAttribute("chiens");
	String email = (String) request.getAttribute("email");
%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>En cours</title>
</head>
<body>
	<%
		if (chiens != null && chiens.size() > 0) {
	%>
		<div
			style="color: #F6F6F6; font-size: 48px; background-color: #696969; padding-top: 1px; padding-left: 30px; padding-bottom: 1px">
			<p>Adoption(s) en cours</p>
		</div>
	<p>Vous êtes connecté(e) avec l'adresse : <%=email %></p>
	
	

	
	<table>
		<thead>
			<tr>
				<th>id</th>
				<th>N° de Puce</th>
				<th>Nom</th>
				<th>Couleur</th>
				<th>Date de naissance</th>
				<th>Race</th>
				<th>		Futur(s) Maitre(s)</th>
			</tr>
		</thead>
		<tbody>
			<%
				for (Chien chien : chiens) {
			%>


			<tr>
				<td><%=chien.getId()%></td>
				<td><%=chien.getNumeroPuce()%></td>
				<td><%=chien.getNom()%></td>
				<td><%=chien.getCouleur()%></td>
				<td><%=chien.getDateNaissance()%></td>
				<td><%=ChienDao.getInstance().getDescriptionRace(chien)%></td>
				<td><%Client client = new Client();%></td>
				<td><%client = ClientDao.getInstance().getClientEnCours(chien.getId());%></td>
				<td><%=client.getNom()%> <%=client.getPrenom()%></td>
				<td><form action="<%=getServletContext().getContextPath()%>/adoption_en_cours"
		method="post">
		<%
	
			session.setAttribute("id_chien",chien.getId());
		    session.setAttribute("id_client",client.getId());
			
		%>	

		<input	type="submit" value="Confirmer">
	</form></td>
				
				
				

			</tr>
			<%
				}
			%>
			
		</tbody>
	</table>

	<%
		} else {
	%>
	<p>Pas de résultats</p>
	<%
		}
	%>
	
	<a href="<%=getServletContext().getContextPath()%>/liste_chien">Retour</a>

</body>
</html>