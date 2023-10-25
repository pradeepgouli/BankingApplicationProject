package com.bankingApplication;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class ChangeFilter
 */
public class ChangeFilter implements Filter {

    /**
     * Default constructor. 
     */
    public ChangeFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		String newPassword=request.getParameter("newPassword");
		String cnewPassword=request.getParameter("cnewPassword");
		// pass the request along the filter chain
		if(newPassword.equals(cnewPassword))
		chain.doFilter(request, response);
		else
			((HttpServletResponse) response).sendRedirect("errorChange.html");
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
