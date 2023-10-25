package com.bankingApplication;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ChangePassword
 */
public class ChangePassword extends HttpServlet {
	public void service(HttpServletRequest request, HttpServletResponse response)
	{
		try
		{
			String newPassword=request.getParameter("newPassword");
			HttpSession session = request.getSession();
			int accountNumber = (int) session.getAttribute("ACCOUNTNUMBER");
			Model m = new Model();
			m.setAccountNumber(accountNumber);
			boolean res=m.change(newPassword);
			if(res==true)
			{
				response.sendRedirect("successChange.html");
			}
			else
			{
				response.sendRedirect("failureChange.html");
			}
		}
		catch(Exception e)
		{
			
		}
	}

}
