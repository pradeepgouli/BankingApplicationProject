<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%

{
		ArrayList a =(ArrayList)session.getAttribute("AL");
		int s=a.size();
		for(int i=0; i<s; i++)
		{
			out.println(a.get(i));
			out.println("<br/>");
			
		}
	
	
}
%>
</body>
</html>