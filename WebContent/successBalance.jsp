<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>CheckBalance</title>
</head>
<body>
<h3>the balance is</h3>
<%
{
	out.println(session.getAttribute("BALANCE"));
}
%>
</body>
</html>