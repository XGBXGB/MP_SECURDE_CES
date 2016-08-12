package servlets;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Product;
import model.Transaction;
import model.User;

/**
 * Servlet implementation class RatingServlet
 */
@WebServlet("/RatingServlet")
public class RatingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RatingServlet() {
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
		// response.getWriter().append("Served at:
		// ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		// System.out.println("I HAVE RATED F YEAH");

		Controller con = new Controller();
		User user = (User) request.getSession().getAttribute("user");
		Boolean retVal = Boolean.parseBoolean(request.getParameter("retVal"));
		System.out.println("RET VAL _" + retVal + "_");
		Transaction t = null;
		String token = request.getParameter("token");
		String existingToken = (String) request.getSession().getAttribute("token");
		if (token.equals(existingToken.toString())) {
			// if(retVal.equals("false"))
			if (retVal) {
				System.out.println("Score for prod " + request.getParameter("rateScore"));
				t = new Transaction(0, ((Product) request.getSession().getAttribute("product")).getId(),
						((User) request.getSession().getAttribute("user")).getId(),
						Double.parseDouble(request.getParameter("rateScore")), request.getParameter("rateReview"),
						new Timestamp(1));
				con.addTransactionWithReview(t);
				response.getWriter().print("true");
				String logString = System.currentTimeMillis() + " " + "BuyProductWithReview " + user.getId() + " "
						+ request.getRemoteAddr() + " " + "successful";
				con.log(logString);

			} else {
				t = new Transaction(0, ((Product) request.getSession().getAttribute("product")).getId(),
						((User) request.getSession().getAttribute("user")).getId(), 5.0, "", new Timestamp(1));
				con.addTransaction(t);
				response.getWriter().print("false");
				String logString = System.currentTimeMillis() + " " + "BuyProduct " + user.getId() + " "
						+ request.getRemoteAddr() + " " + "successful";
				con.log(logString);

			}
		} else {

			String logString = System.currentTimeMillis() + " " 
					+ "BuyProduct " 
					+ "-" + " " 
					+ request.getRemoteAddr() + " "
					+ "wrongCSRFToken";
			con.log(logString);
		}
		// response.sendRedirect("product.jsp");
	}

}
