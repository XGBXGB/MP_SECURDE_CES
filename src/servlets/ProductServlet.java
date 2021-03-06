package servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.rmi.CORBA.UtilDelegate;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.Product;
import model.Transaction;
import model.User;

/**
 * Servlet implementation class ItemServlet
 */
@WebServlet("/ProductServlet")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		//$(this).closest('form').submit()
		Controller controller = new Controller();
		System.out.println("ID " +Integer.parseInt(request.getParameter("selectedProduct")));
		Product product = controller.getProduct(Integer.parseInt(request.getParameter("selectedProduct")));
		request.getSession().setAttribute("product", product);
		//request.getSession().setAttribute("user", request.getSession().getAttribute("user"));
		response.sendRedirect("product.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Controller con = new Controller();
		System.out.println("Password in Prod " + request.getParameter("password"));
		if(con.authenticateUser(((User)request.getSession().getAttribute("user")).getUsername(), request.getParameter("password"))!= null)
		{
			System.out.println("correct password when buying");
			response.getWriter().print("true");
			//response.sendRedirect("product.jsp");
			//doGet(request, response);
		}
		else
		{
			System.out.println("Wrong password when buying");
			response.getWriter().print("false");
			String logString = System.currentTimeMillis() + " " 
					+ "BuyProduct " 
					+ "-" + " " 
					+ request.getRemoteAddr() + " "
					+ "wrongPassword";
			con.log(logString);
			//request.setAttribute("bought", "wrong pw");
			//response.sendRedirect("product.jsp");
		}
		
		//System.out.println("SUP HEY");
		/*Controller con = new Controller();
		
		if(con.authenticateUser(((User)request.getSession().getAttribute("user")).getUsername(), request.getParameter("password"))!= null)
		{
			Transaction t = new Transaction(0, ((Product)request.getSession().getAttribute("product")).getId(), ((User)request.getSession().getAttribute("user")).getId(), 5.0, "", "2016-07-27");
			con.addTransaction(t);
			request.setAttribute("product",request.getSession().getAttribute("product"));
			request.setAttribute("user",request.getSession().getAttribute("user"));		
			//response.getOutputStream().println("<script type='text/javascript'>$('#SuccessBuyingModal').modal(\"show\");</script>");	
			//request.setAttribute("bought", "bought");
			System.out.println("correct password when buying");
			//response.sendRedirect("product.jsp");
			//doGet(request, response);
		}
		else
		{
			System.out.println("Wrong password when buying");
			//request.setAttribute("bought", "wrong pw");
			//response.sendRedirect("product.jsp");
		}*/
	}

}
