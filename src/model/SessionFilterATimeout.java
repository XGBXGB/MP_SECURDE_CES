package model;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.SecureRandom;

import javax.servlet.*;
import javax.servlet.http.*;

import controller.Controller;

public class SessionFilterATimeout implements Filter {
	private long maxPeriod;

	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpSession session = request.getSession(false);
		if (session != null) {
			if (session.getAttribute("user") != null) {
				long activated = (long) session.getAttribute("activation-time");
				if (System.currentTimeMillis() > (activated + maxPeriod)) {
					System.out.println("WENT HEREEE TIMEOUTTTT NIGGAAAAAAAAAAHHHH!!!!!!!");
					session.invalidate();
					request.getSession().setAttribute("token", new BigInteger(130, new SecureRandom()).toString(32));
					PrintWriter out = arg1.getWriter();
					out.println("<script type=\"text/javascript\">");
					out.println("alert('Session Expired!');");
					out.println("</script>");
				}
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
		if (arg0.getInitParameter("max-period") == null) {
			throw new IllegalStateException("max-period must be provided");
		}
		maxPeriod = new Long(arg0.getInitParameter("max-period"));
	}

}