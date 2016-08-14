package servlets;

import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Product;
import model.User;

/**
 * Servlet implementation class EditProductServlet
 */
@WebServlet("/EditProductServlet")
public class EditProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditProductServlet() {
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
		
		if (request.getSession().getAttribute("user") == null) {
			response.getWriter().print("timeout");
			String logString = System.currentTimeMillis() + " " + "EditProduct " + "-" + " " + request.getRemoteAddr()
					+ " timeout";
			c.log(logString);
		} else {
			User user = (User) request.getSession().getAttribute("user");

			String productId = request.getParameter("id");
			String name = request.getParameter("name");
			String categoryId = request.getParameter("categoryId");
			String price = request.getParameter("price");
			String description = request.getParameter("description");
			String imagePath = request.getParameter("imagePath");

			Pattern nameP = Pattern.compile("^[ A-z]{1,}$");
			Pattern priceP = Pattern.compile("[0-9]+(.[0-9]+)?");
			Pattern descP = Pattern.compile("^[,.!; A-z]{1,}$");
			if (token.equals(existingToken.toString())) {
				if (!(nameP.matcher(name).matches() && priceP.matcher(price).matches()
						&& descP.matcher(description).matches())) {
					response.getWriter().print("syntax error");
					String logString = System.currentTimeMillis() + " " + "EditProduct " + user.getId() + " "
							+ request.getRemoteAddr() + " syntaxError";
					c.log(logString);
				} else {
					Product p = new Product();
					p.setId(Integer.parseInt(productId));
					p.setName(name);
					p.setCategoryId(Integer.parseInt(categoryId));
					p.setDescription(description);
					p.setPrice(Double.parseDouble(price));

					c.editProduct(p);
					response.getWriter().print("ok");
					String logString = System.currentTimeMillis() + " " + "EditProduct " + user.getId() + " "
							+ request.getRemoteAddr() + " " + "successful";
					c.log(logString);
				}
			} else {

				String logString = System.currentTimeMillis() + " " 
						+ "EditProduct " 
						+ "-" + " " 
						+ request.getRemoteAddr() + " "
						+ "wrongCSRFToken";
				c.log(logString);
			}
		}
	}

}
