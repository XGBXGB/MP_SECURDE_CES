package model;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;

import controller.Controller;

public class SessionFilterATimeout implements Filter {
	private long maxPeriod;
	
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) arg0;
        HttpSession session = request.getSession( false );
        if ( session != null ) {
            long activated = (long) session.getAttribute( "activation-time" );
            if ( System.currentTimeMillis() > ( activated + maxPeriod ) ) {
                 session.invalidate();
            }
        }
        arg2.doFilter(arg0, arg1);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		if ( arg0.getInitParameter("max-period") == null ) {
            throw new IllegalStateException( "max-period must be provided" );
       }
       maxPeriod = new Long( arg0.getInitParameter("max-period") );
	}

}