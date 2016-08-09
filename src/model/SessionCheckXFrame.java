package model;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;

import com.sun.xml.internal.ws.api.databinding.JavaCallInfo;

import controller.Controller;

public class SessionCheckXFrame implements Filter
{
public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2) throws IOException, ServletException
    {
        //HttpServletResponse response = (HttpServletResponse) arg1;
		System.out.println("begin xframe");
		HttpServletResponse response = new javax.servlet.http.HttpServletResponseWrapper((HttpServletResponse)arg1);
		response.addHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
		System.out.println("end xframe");
		arg2.doFilter(arg0,response);
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