package com.bankingApplication;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CheckBalance
 */
public class CheckBalance extends HttpServlet {
	public void service(HttpServletRequest request, HttpServletResponse response)
	{
		HttpSession session =request.getSession();
		int accountNumber=(int) session.getAttribute("ACCOUNTNUMBER");
		
			try {
				
				Model m = new Model();
				m.setAccountNumber(accountNumber);
				boolean val=m.checkBalance();
				if(val==true)
				{
					int balance=m.getBalance();
					session.setAttribute("BALANCE", balance);
				response.sendRedirect("successBalance.jsp");
				}
				else
				{
					response.sendRedirect("failureBalance.html");
				} 
			}catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
}
