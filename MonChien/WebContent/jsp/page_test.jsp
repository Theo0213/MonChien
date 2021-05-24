<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="toto"%>
<%
	Integer i = request.getAttribute("choix_nombre") != null ? (Integer) request.getAttribute("choix_nombre") : null;
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<c:if test="i !=null && i>0">
		<p>le nombre est positif</p>
	</c:if>
</body>
</html>