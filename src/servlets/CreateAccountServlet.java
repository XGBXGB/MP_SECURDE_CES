package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Address;
import model.BCrypt;
import model.User;

/**
 * Servlet implementation class CreateAccountServlet
 */
@WebServlet("/CreateAccountServlet")
public class CreateAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateAccountServlet() {
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
		if (request.getSession().getAttribute("user") == null){
			PrintWriter out = response.getWriter();
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Session Expired! Please try logging in again.');");
			out.println("location='index.jsp';");
			out.println("</script>");
		}else {
			String fName = request.getParameter("firstname");
			String mInitial = request.getParameter("middleinitial");
			String lName = request.getParameter("lastname");
			;
			String userName = request.getParameter("username");
			String password = request.getParameter("confirmpassword");
			String userType = request.getParameter("userType");

			// SIGNUP FUCNTION
			Controller controller = new Controller();
			if (controller.validateUsername(userName)) {
				String encryptedPass = BCrypt.hashpw(password, BCrypt.gensalt());
				User u = new User();
				u.setUsername(userName);
				u.setpassword(encryptedPass);
				u.setLastName(lName);
				u.setFirstName(fName);
				u.setMiddleName(mInitial);
				if (userType.equals("Accounting Manager")) {
					u.setUserType(3);
				} else {
					u.setUserType(2);
				}
				controller.addUser(u);
				PrintWriter out = response.getWriter();
				out.println("<script type=\"text/javascript\">");
				out.println("alert('Account Created!');");
				out.println("location='admincreate.jsp';");
				out.println("</script>");
			} else {
				PrintWriter out = response.getWriter();
				out.println("<script type=\"text/javascript\">");
				out.println("alert('That username already exists! Choose a different one.');");
				out.println("location='admincreate.jsp';");
				out.println("</script>");
			}
		}
	}
}
