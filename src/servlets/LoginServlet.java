package servlets;

import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.BCrypt;
import model.PasswordService;
import model.Product;
import model.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HttpSession session;
	private String url;
	private int loginAttempts;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
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
		session = request.getSession();

		// check to make sure we've clicked link
		if (request.getParameter("logout") != null) {

			// logout and redirect to frontpage
			logout();
			url = "index.jsp";
		}

		// forward our request along
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		session = request.getSession();
		String currToken = request.getParameter("CSRFToken");
		// get the number of logins
		String existingToken = (String) session.getAttribute("token");
		if (currToken.equals(existingToken.toString())) {
			System.out.println("matching token::::");
			System.out.println("currToken: " + currToken);
			System.out.println("existingToken: " + existingToken);
			if (session.getAttribute("loginAttempts") == null) {
				loginAttempts = 0;
			}

			// exceeded logins
			if (loginAttempts > 2) {
				String errorMessage = "Error: Number of Login Attempts Exceeded";
				request.setAttribute("errorMessage", errorMessage);
				url = "login.jsp";
			} else { // proceed
				// pull the fields from the form
				String username = request.getParameter("username");
				String password = request.getParameter("password");

				// encrypt the password to check against what's stored in DB
				// create a user helper class to make database calls, and call
				// authenticate user method
				Controller controller = Controller.getInstance();
				User user = controller.authenticateUser(username, password);

				// we've found a user that matches the credentials
				System.out.println("User adsfadsf");
				if (user != null) {
					System.out.println("User is not null " + user.getUserType());
					if (user.getUserType() == 4) {
						Product p = (Product) session.getAttribute("product");
						// invalidate current session, then get new session for
						// our
						// user (combats: session hijacking)
						session.invalidate();
						session = request.getSession(true);
						SecureRandom random = new SecureRandom();
						session.setAttribute("token", new BigInteger(130, random).toString(32));
						session.setAttribute("user", user);
						if (p != null) {
							session.setAttribute("product", p);
							url = "product.jsp";
						} else {
							url = "index.jsp";
						}
					} else if (user.getUserType() == 3) {
						session.invalidate();
						session = request.getSession(true);
						SecureRandom random = new SecureRandom();
						session.setAttribute("token", new BigInteger(130, random).toString(32));
						session.setAttribute("user", user);
						url = "account-manager.jsp";
					} else if (user.getUserType() == 2) {
						session.invalidate();
						session = request.getSession(true);
						SecureRandom random = new SecureRandom();
						session.setAttribute("token", new BigInteger(130, random).toString(32));
						session.setAttribute("user", user);
						url = "home_product_manager.jsp";
					} else if (user.getUserType() == 1) {
						System.out.println("Usertype = 1");
						session.invalidate();
						session = request.getSession(true);
						SecureRandom random = new SecureRandom();
						session.setAttribute("token", new BigInteger(130, random).toString(32));
						session.setAttribute("user", user);
						url = "admin.jsp";
					}
					session.setAttribute("activation-time", System.currentTimeMillis());
				} else if(user == null){
					System.out.println("USER IS NULL");
					String errorMessage = "Error: Unrecognized Username or Password<br>Login attempts remaining: "
							+ (3 - (loginAttempts));
					request.setAttribute("errorMessage", errorMessage);

					// track login attempts (combats: brute force attacks)
					session.setAttribute("loginAttempts", loginAttempts++);
					url = "login.jsp";
				}
			}
		} else {
			System.out.println("not matching token");
			url = "login.jsp";
		}
		// forward our request along
		System.out.println("url: " + url);
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

	public void logout() {
		session.invalidate();
	}

}
