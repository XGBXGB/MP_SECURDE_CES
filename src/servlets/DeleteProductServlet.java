package servlets;

import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.User;

/**
 * Servlet implementation class DeleteProductServlet
 */
@WebServlet("/DeleteProductServlet")
public class DeleteProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteProductServlet() {
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
		String token = request.getParameter("token");
		String existingToken = (String) request.getSession().getAttribute("token");
		
		c.checkExpired();
		User u = (User) request.getSession().getAttribute("user");
		if (u != null) {
			User user = c.getUser(u.getId());
			if (user == null) {
				request.getSession().invalidate();
				request.getSession().setAttribute("token", new BigInteger(130, new SecureRandom()).toString(32));
			}
		}
		
		User user = (User) request.getSession().getAttribute("user");
		if (request.getSession().getAttribute("user") == null) {
			response.getWriter().print("timeout");
			String logString = System.currentTimeMillis() + " " + "DeleteProduct " + "-" + " " + request.getRemoteAddr()
					+ " timeout";
			c.log(logString);
		} else {
			if (token.equals(existingToken.toString())) {
				String productId = request.getParameter("id");

				c.deleteProduct(productId);
				response.getWriter().print("ok");
				String logString = System.currentTimeMillis() + " " + "DeleteProduct " + user.getId() + " "
						+ request.getRemoteAddr() + " successful";
				c.log(logString);
				System.out.println(logString);
			} else {
				String logString = System.currentTimeMillis() + " " 
						+ "DeleteProduct " 
						+ "-" + " " 
						+ request.getRemoteAddr() + " "
						+ "wrongCSRFToken";
				c.log(logString);

			}
		}
	}

}
