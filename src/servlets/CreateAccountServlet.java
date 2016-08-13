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

		System.out.println("IN POST CREATE ACCOUNT");
		Controller controller = new Controller();

		String createAccountToken = request.getParameter("token");
		String createAccountTokenFromSession = (String) request.getSession().getAttribute("token");

		System.out.println("CREATE ACCOUNT TOKEN FROM SESSION: " + createAccountTokenFromSession);
		if (request.getSession().getAttribute("user") == null) {
			response.getWriter().print("timeout");
			String logString = System.currentTimeMillis() + " " + "CreateAccount " + "-" + " " + request.getRemoteAddr()
					+ " timeout";
			controller.log(logString);
		} else {
			User user = (User) request.getSession().getAttribute("user");
			Controller con = new Controller();
			if (con.authenticateUser(((User) request.getSession().getAttribute("user")).getUsername(),
					request.getParameter("password")) != null) {
				String fName = request.getParameter("firstname");
				String mInitial = request.getParameter("middleinitial");
				String lName = request.getParameter("lastname");

				String userName = request.getParameter("username");
				String password = request.getParameter("pw");
				String confPassword = request.getParameter("confirmpassword");
				String userType = request.getParameter("userType");

				System.out.println(createAccountToken + " " + createAccountTokenFromSession);
				Pattern name = Pattern.compile("^[ A-z]{1,}$");
				Pattern singleLetter = Pattern.compile("[A-z]");
				Pattern letters_numbers = Pattern.compile("^[_A-z0-9]{1,}$");
				Pattern passwordP = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$");
				if (createAccountToken.equals(createAccountTokenFromSession.toString())) {
					if (!(name.matcher(fName).matches() && name.matcher(lName).matches()
							&& singleLetter.matcher(mInitial).matches()) && letters_numbers.matcher(userName).matches()
							&& passwordP.matcher(password).matches()
							&& passwordP.matcher(confPassword).matches()) {
						response.getWriter().print("syntax error");

						String logString = System.currentTimeMillis() + " " + "CreateAccount " + user.getId() + " "
								+ request.getRemoteAddr() + " wrongFormat";
						controller.log(logString);
						System.out.println(logString);
					} else {

						// SIGNUP FUCNTION
						if (controller.validateUsername(userName)) {
							if (password.equals(confPassword)) {
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
								System.out.println("correct password when creating");
								response.getWriter().print("true");
								String logString = System.currentTimeMillis() + " " + "CreateAccount " + user.getId()
										+ " " + request.getRemoteAddr() + " successful";
								controller.log(logString);
								System.out.println(logString);
								// PrintWriter out = response.getWriter();
								// out.println("<script
								// type=\"text/javascript\">");
								// out.println("alert('Account Created!');");
								// out.println("location='admincreate.jsp';");
								// out.println("</script>");
							}else{
								System.out.println("PASSWORD NOT MATCHING: "+confPassword+" "+password);
								response.getWriter().print("password not matching");
							}
						} else {
							// PrintWriter out = response.getWriter();
							// out.println("<script type=\"text/javascript\">");
							// out.println("alert('That username already exists!
							// Choose a different one.');");
							// out.println("location='admincreate.jsp';");
							// out.println("</script>");
							response.getWriter().print("username taken");
						}
					}
				} else {
					String logString = System.currentTimeMillis() + " " + "CreateAccount " + "-" + " "
							+ request.getRemoteAddr() + " " + "wrongCSRFToken";
					controller.log(logString);
				}

				
			} else {
				System.out.println("Wrong password when creating");
				response.getWriter().print("false");
				String logString = System.currentTimeMillis() + " " + "CreateAccount " + user.getId() + " "
						+ request.getRemoteAddr() + " wrongPassword";
				controller.log(logString);
				// request.setAttribute("bought", "wrong pw");
				// response.sendRedirect("product.jsp");
			}

		}
	}
}
