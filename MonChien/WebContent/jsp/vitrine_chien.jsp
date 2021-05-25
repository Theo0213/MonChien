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
<title>Liste de Chiens</title>
</head>
<body>
	<%
		if (chiens != null && chiens.size() > 0) {
	%>
	
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
			</tr>
		</thead>
		<tbody>
			<%
				for (Chien chien : chiens) {
			%>


			<tr>
				<td><a
					href="<%=getServletContext().getContextPath()%>/adoption_chien?id_chien=<%=chien.getId()%>"><%=chien.getId()%></a></td>
				<td><%=chien.getNumeroPuce()%></td>
				<td><%=chien.getNom()%></td>
				<td><%=chien.getCouleur()%></td>
				<td><%=chien.getDateNaissance()%></td>
				
				<td><%=ChienDao.getInstance().getDescriptionRace(chien)%></td>

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

</body>
</html>