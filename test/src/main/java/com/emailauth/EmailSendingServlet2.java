package com.emailauth;
import java.io.IOException;
import java.io.PrintWriter;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/EmailSendingServlet2")
public class EmailSendingServlet2 extends HttpServlet
{
	String resultMessage = "";
	private String host;
	private String port;
	private String user;
	private String pass;

	
	@Override
	public void init() throws ServletException
	{
		// TODO Auto-generated method stub
		//super.init();
		ServletContext context = getServletContext();
		host = context.getInitParameter("host");
		port = context.getInitParameter("port");
		user = context.getInitParameter("user");
		pass = context.getInitParameter("pass");
	
	}
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		
		
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		
	
		Object recipient = req.getAttribute("e1");
		String data = (String) recipient;
		
		String subject = "Welcome to Webwing E-commerce Shopping Project";
		
		HttpSession sess = req.getSession();
		
		int n1 = (int) sess.getAttribute("n1");
		int n2 = (int) sess.getAttribute("n2");
		int n3 = (int) sess.getAttribute("n3");
		int n4 = (int) sess.getAttribute("n4");
		
		
		
		
		String content = "Welcome to Webwing Website Here you can purchase your Valuable Products Your Verification OTP is "+n1+n2+n3+n4;
		//System.out.println(recipient);
		//System.out.println(recipient);
	
		try 
		{
			EmailUtility.sendEmail(host, port, user, pass, data, subject,content);
		
			Thread.sleep(3000);
			
			//RequestDispatcher rd = req.getRequestDispatcher("register.jsp");
			//req.setAttribute("fname","abcd");
			//rd.forward(req, resp);
			//resp.sendRedirect("otpverify.jsp");
			
			
			resp.sendRedirect("otpsend.jsp");
		} 
		catch (AddressException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (MessagingException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		resultMessage = "The e-mail was sent successfully";
		
		
		
	}
}
