<%@page import="com.dao.Dao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<jsp:useBean id="m" class="com.model.SignupModel"/>
<jsp:setProperty property="*" name="m"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<%
	  	//get
		String fname=request.getParameter("name");  
    	String email=request.getParameter("email");  
    	String phone=request.getParameter("phone");  
		String pass=request.getParameter("password");
		String repass = request.getParameter("repassword");
		
		//set
		session.setAttribute("fname", fname);
		session.setAttribute("email", email);
		session.setAttribute("phone", phone);
		session.setAttribute("pass", pass);
		session.setAttribute("repass", fname);
		
		//random number generation
		
		int number11 = 1 + (int)(9* Math.random());
		int number22 = 1 + (int)(9* Math.random());
		int number33 = 1 + (int)(9* Math.random());
		int number44 = 1 + (int)(9* Math.random());
		
		//set random number
		session.setAttribute("n1",number11);
		session.setAttribute("n2",number22);
		session.setAttribute("n3",number33);
		session.setAttribute("n4",number44);
		
		//System.out.print(number11+" "+number22+" "+number33+" "+number44);
		
		
		//Going in next page with some data
		
		RequestDispatcher rd = request.getRequestDispatcher("EmailSendingServlet2");
		 
		request.setAttribute("e1", email);
		session.setAttribute("n1",number11);
		session.setAttribute("n2",number22);
		session.setAttribute("n3",number33);
		session.setAttribute("n4",number44);
	    rd.forward(request, response);
		
		
	%>


</body>
</html>