<%@page import="dao.ChienDao"%>
<%@page import="model.Chien"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%
	Chien p = (Chien) request.getAttribute("chien");
%>
<html>
<head>
<meta charset="ISO-8859-1">

<title>Detail du chien</title>
</head>


<body>
	<h3>
		Consulter
		<%=p.getNom()%></h3>
	<br>
	<br>
	<a href="<%=getServletContext().getContextPath()%>/vitrine_chien">Retour</a>
	<br>
	<ul>
		<li>N� Puce : <%=p.getNumeroPuce()%></li>
		<li>Nom : <%=p.getNom()%></li>
		<li>Couleur : <%=p.getCouleur()%></li>
		<li>Date de naissance : <%=p.getDateNaissance()%></li>
		<li>Race : <%=ChienDao.getInstance().getDescriptionRace(p)%></li>
	</ul>
	<a href="<%=getServletContext().getContextPath()%>/vitrine_chien">Adopter</a>

</body>

</html>