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
	<h3>Vous êtes sur le point d'adopter <%=p.getNom()%></h3>
	<br>
	<br>
	<a href="<%=getServletContext().getContextPath()%>/adoption_chien?id_chien=<%=p.getId()%>">Retour</a>

	<br>
	<br>
	<form action="<%=getServletContext().getContextPath()%>/adoption_chien_validation?id_chien=<%=p.getId()%>"
		method="post">


		<input	type="submit" value="Confirmer">
	</form>
	


</body>

</html>