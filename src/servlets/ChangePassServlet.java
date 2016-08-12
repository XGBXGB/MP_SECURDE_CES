package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.BCrypt;
import model.User;

/**
 * Servlet implementation class ChangePassServlet
 */
@WebServlet("/ChangePassServlet")
public class ChangePassServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ChangePassServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Controller c = Controller.getInstance();
		User currUser = (User) request.getSession().getAttribute("user");
		String oldPassword = request.getParameter("oldpassword");
		String newPassword = request.getParameter("newpassword");
		String confPassword = request.getParameter("confirmpassword");


		String username = currUser.getUsername();

		if (request.getSession().getAttribute("user") == null) {
			PrintWriter out = response.getWriter();
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Session Expired! Please try logging in again.');");
			out.println("location='login.jsp';");
			out.println("</script>");
			String logString = System.currentTimeMillis() + " " 
					+ "ChangePassword " 
					+ "-" + " " 
					+ request.getRemoteAddr() 
					+ " timeout";
			c.log(logString);
		} else {
			Pattern letters_numbers = Pattern.compile("^[_A-z0-9]{1,}$");

			User user = (User)request.getSession().getAttribute("user");
			if (letters_numbers.matcher(oldPassword).matches() && letters_numbers.matcher(newPassword).matches()
					&& letters_numbers.matcher(confPassword).matches()) {
				if (c.authenticateUser(username, oldPassword) != null) {
					if (newPassword.equals(confPassword)) {
						c.updatePassword(currUser.getId(), BCrypt.hashpw(confPassword, BCrypt.gensalt()));
						PrintWriter out = response.getWriter();
						out.println("<script type=\"text/javascript\">");
						out.println("alert('Password successfully changed!');");
						out.println("location='changepass.jsp';");
						out.println("</script>");
						String logString = System.currentTimeMillis() + " " + "ChangePassword " + user.getId() + " " + request.getRemoteAddr();
						c.log(logString);
						System.out.println(logString);
					}
				} else {
					PrintWriter out = response.getWriter();
					out.println("<script type=\"text/javascript\">");
					out.println("alert('Old password incorrect!');");
					out.println("location='changepass.jsp';");
					out.println("</script>");
					String logString = System.currentTimeMillis() + " " 
							+ "ChangePassword " 
							+ user.getId() + " " 
							+ request.getRemoteAddr() 
							+ " wrongPassword";
					c.log(logString);
				}
			}else{
				PrintWriter out = response.getWriter();
				out.println("<script type=\"text/javascript\">");
				out.println("alert('Please recheck format of input fields.');");
				out.println("location='changepass.jsp';");
				out.println("</script>");
				String logString = System.currentTimeMillis() + " " 
						+ "ChangePassword " 
						+ user.getId() + " " 
						+ request.getRemoteAddr() 
						+ " wrongFormat";
				c.log(logString);
			}
		}
	}

}
