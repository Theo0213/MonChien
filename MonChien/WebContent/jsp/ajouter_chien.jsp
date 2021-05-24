<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="dao.RaceDao"%>
<%@page import="model.Race"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="toto"%>
<%
	List<Race> races = new ArrayList<Race>();
	races= RaceDao.getInstance().getAll();
	request.setAttribute("races", races);
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title></title>
</head>
<body>
	<h3>Ajouter un Chien</h3>
	<form action="<%=getServletContext().getContextPath()%>/ajouter_chien"
		method="post">


		<input type="text" name="numero_puce" placeholder="N° Puce"><br>
		<input type="text" name="nom" placeholder="Nom"><br> 
		<input type="text" name="couleur" placeholder="Couleur"><br>
		<input type="date" name="date_naissance"><br> 
		<select name="id_race" required>
			<option value="" selected disabled>Choix Race</option>
		<c:forEach items="${races}" var="race">
			<option value="<c:out value="${race.getId()}"/>"><c:out value="${race.getDescription()}"/></option>
		</c:forEach>
            
        </select><br> 
		<input	type="submit" value="Submit"> <input type="reset"
			value="Reset">
	</form>

</body>
</html>