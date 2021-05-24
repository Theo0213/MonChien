<%@page import="dao.ChienDao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="dao.RaceDao"%>
<%@page import="model.Race"%>
<%@page import="model.Chien"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="toto"%>
<!DOCTYPE html>
<%
	Chien chien = (Chien) request.getAttribute("chien");
%>
<%
	List<Race> races = new ArrayList<Race>();
	races= RaceDao.getInstance().getAll();
	request.setAttribute("races", races);
	
%>
<html>
<head>
<meta charset="ISO-8859-1">
<title></title>
</head>
<body>
	<h3>Editer Chien</h3>
	<form action="<%=getServletContext().getContextPath()%>/modifier_chien?id_chien=<%=chien.getId()%>"
		method="post">

		<input type="text" name="numero_puce" placeholder="N° Puce"
			value="<%=chien.getNumeroPuce()%>"><br> <input
			type="text" name="nom" placeholder="Nom" value="<%=chien.getNom()%>"><br>
		<input type="text" name="couleur" placeholder="Couleur"
			value="<%=chien.getCouleur()%>"><br> <input type="date"
			name="date_naissance" value="<%=chien.getDateNaissance()%>"><br>
		<select name="id_race">
			<option value="" selected disabled><%=ChienDao.getInstance().getDescriptionRace(chien)%></option>
		<c:forEach items="${races}" var="race">
			<option value="<c:out value="${race.getId()}"/>"><c:out value="${race.getDescription()}"/></option>
		</c:forEach>
            
        </select><br> 
		<input type="submit" value="Submit"> <input type="reset"
			value="Reset">
	</form>

</body>
</html>