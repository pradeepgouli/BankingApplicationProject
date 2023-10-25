package com.bankingApplication;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String customerId = request.getParameter("customerid");
		String password = request.getParameter("password");
		Model m = new Model();
		m.setCustomerId(customerId);
		m.setPassword(password);
		boolean val = m.login();
		HttpSession session = request.getSession(true);
		if (val == true) {
			int accountNumber = m.getAccountNumber();
			System.out.println(accountNumber);
			session.setAttribute("ACCOUNTNUMBER", accountNumber);
			response.sendRedirect("successLogin.html");
		} else {
			response.sendRedirect("failureLogin.html");
		}
	}
}
