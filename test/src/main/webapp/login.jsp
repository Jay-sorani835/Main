<%@page import="com.dao.Dao"%>
<%@page import="com.model.SignupModel"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


	<%
		String email = request.getParameter("email");
		String pass = request.getParameter("password");
		
		SignupModel m = new SignupModel();
		m.setEmail(email);
		m.setPassword(pass);
		
		
		
		SignupModel m2 = Dao.login(m);
		if(m2!=null)
		{
			
			
			session.setAttribute("fruit", true);
			session.setAttribute("email", email);
			session.setAttribute("name", m2.getName());
			session.setAttribute("phone", m2.getPhone());
			response.sendRedirect("index.jsp");
		}
		else
		{
			
		
	%>
	<br>
		<center>
				
				<b><p style="color:red;font-family: sans-serif; font-size: 25px;">Login Failed!! Please Try Again</p></b>
			</center>

			<br>
			<br>
		<%@ include file="signin.jsp"%>
		<%
		}
		%>

</body>
</html>