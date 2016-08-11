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
import model.Product;

/**
 * Servlet implementation class AddProductServlet
 */
@WebServlet("/AddProductServlet")
public class AddProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddProductServlet() {
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
		if (request.getSession().getAttribute("user") == null) {
			response.getWriter().print("timeout");
		} else {
			Controller c = Controller.getInstance();

			String name = request.getParameter("name");
			String categoryId = request.getParameter("categoryId");
			String price = request.getParameter("price");
			String description = request.getParameter("description");
			String imagePath = request.getParameter("imagePath");

			Pattern nameP = Pattern.compile("^[ A-z]{1,}$");
			Pattern priceP = Pattern.compile("[0-9]+(.[0-9]+)?");
			Pattern descP = Pattern.compile("^[,.!; A-z]{1,}$");
			if (!(nameP.matcher(name).matches() && priceP.matcher(price).matches()
					&& descP.matcher(description).matches())) {
				response.getWriter().print("syntax error");
			} else {
				Product p = new Product();
				p.setName(name);
				p.setCategoryId(Integer.parseInt(categoryId));
				p.setDescription(description);
				p.setPrice(Double.parseDouble(price));

				c.addProduct(p);
				response.getWriter().print("ok");
			}
		}
	}

}
