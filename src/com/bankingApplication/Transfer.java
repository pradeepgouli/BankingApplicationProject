package com.bankingApplication;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Transfer
 */
public class Transfer extends HttpServlet {
	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		HttpSession session= ((HttpServletRequest)request).getSession();
		int balance =(int)session.getAttribute("BALANCE");
		int amount = Integer.parseInt(request.getParameter("amount"));
		int toAccountNumber = Integer.parseInt(request.getParameter("toaccountnumber"));
		int accountNumber=(int) session.getAttribute("ACCOUNTNUMBER");
		
		try {
			Model m = new Model();
			m.setAccountNumber(accountNumber);
			m.setBalance(balance);
			boolean val=m.transfer(amount,toAccountNumber);
			if(val==true)
				response.sendRedirect("successTransfer.html");
			else
				response.sendRedirect("failureTransfer.html");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			response.sendRedirect("failureTransfer.html");
			e.printStackTrace();
		}
		
	}

}
