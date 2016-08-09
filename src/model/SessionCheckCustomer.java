package model;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;

import controller.Controller;

public class SessionCheckCustomer implements Filter
{
public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2) throws IOException, ServletException
    {
	System.out.println("begin customer");
        HttpServletRequest request = (HttpServletRequest) arg0;
        HttpServletResponse response = (HttpServletResponse) arg1;
        Controller c = new Controller();
        
        System.out.println("Ehem");
        if(request.getSession().getAttribute("user") != null){
	        if(c.getUserType(((User) request.getSession().getAttribute("user")).getUserType()).equals("Accounting Manager"))
	        {
	            response.sendRedirect("account-manager.jsp");
	        }else if(c.getUserType(((User) request.getSession().getAttribute("user")).getUserType()).equals("Product Manager")){
	        	response.sendRedirect("home_product_manager.jsp");
	        }else if(c.getUserType(((User) request.getSession().getAttribute("user")).getUserType()).equals("Administrator")){
	        	response.sendRedirect("admin.jsp");
	        }
        }
//        else if(c.getUserType(((User) request.getSession().getAttribute("user")).getUserType()).equals("Accounting Manager"))
//        {           
//        	response.sendRedirect("account-manager.jsp");
//        	//return;
//        	System.out.println("hello");
//        }        	
        System.out.println("end customer");
        arg2.doFilter(request, response);
        
        
        //HttpServletRequest req= (HttpServletRequest) request;
        //req.getRequestDispather("error.jsp).forward(request,response);
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