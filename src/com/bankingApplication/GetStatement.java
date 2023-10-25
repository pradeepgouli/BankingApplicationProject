package com.bankingApplication;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class GetStatement
 */
public class GetStatement extends HttpServlet {
	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		HttpSession session=request.getSession();
		int accountNumber= (int) session.getAttribute("ACCOUNTNUMBER");
		Model m = new Model();
		m.setAccountNumber(accountNumber);
		ArrayList al=m.getStatement();
		if(al.size()>0)
		{
			session.setAttribute("AL", al);
			response.sendRedirect("statement.jsp");
		}
	}
}
