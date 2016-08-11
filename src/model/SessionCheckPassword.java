package model;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.*;
import javax.servlet.http.*;

import controller.Controller;

public class SessionCheckPassword implements Filter {
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		if (request.getSession().getAttribute("user") == null){
			PrintWriter out = response.getWriter();
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Please login first.');");
			out.println("location='login.jsp';");
			out.println("</script>");
		}else {

			Controller c = new Controller();

			System.out.println("Ehem");

			if (c.getUserType(((User) request.getSession().getAttribute("user")).getUserType()).equals("Customer")) {
				response.sendRedirect("index.jsp");
			} else if (c.getUserType(((User) request.getSession().getAttribute("user")).getUserType())
					.equals("Administrator")) {
				response.sendRedirect("admin.jsp");
			} else{
				arg2.doFilter(request, response);
			}
			// else if(c.getUserType(((User)
			// request.getSession().getAttribute("user")).getUserType()).equals("Accounting
			// Manager"))
			// {
			// response.sendRedirect("account-manager.jsp");
			// //return;
			// System.out.println("hello");
			// }
		}
		

		// HttpServletRequest req= (HttpServletRequest) request;
		// req.getRequestDispather("error.jsp).forward(request,response);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}