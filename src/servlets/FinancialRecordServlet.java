package servlets;

import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import controller.Controller;
import model.FinancialRecord;
import model.Product;
import model.User;

/**
 * Servlet implementation class FinancialRecordServlet
 */
@WebServlet("/FinancialRecordServlet")
public class FinancialRecordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FinancialRecordServlet() {
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
		Controller controller = Controller.getInstance();
		controller.checkExpired();
		User u = (User) request.getSession().getAttribute("user");
		if (u != null) {
			User user = controller.getUser(u.getId());
			if (user == null) {
				request.getSession().invalidate();
				request.getSession().setAttribute("token", new BigInteger(130, new SecureRandom()).toString(32));
			}
		}

		if (request.getSession().getAttribute("user") == null) {
			response.getWriter().print("timeout");
		} else {
			controller = Controller.getInstance();
			String sortBy = request.getParameter("sortBy");
			System.out.println("sortBy: " + sortBy);
			if (sortBy.equals("all")) {
				double total = controller.getTotalPriceAllProducts();
				response.getWriter().print(total);
			} else {
				Gson gson = new Gson();
				JsonElement element;
				JsonArray jsonArray;
				ArrayList<FinancialRecord> records = null;
				if (sortBy.equals("product")) {
					records = controller.getTotalPriceByProduct();
				} else if (sortBy.equals("category")) {
					records = controller.getTotalPriceByCategory();
				}
				element = gson.toJsonTree(records, new TypeToken<List<FinancialRecord>>() {
				}.getType());
				jsonArray = element.getAsJsonArray();
				response.setContentType("application/json");
				response.getWriter().print(jsonArray);
			}
		}
	}

}
