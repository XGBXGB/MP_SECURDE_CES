package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SignUpServlet
 */
@WebServlet("/SignUpServlet")
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUpServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//Personal Information:
		String fName  = request.getParameter("firstname");
		String mInitial = request.getParameter("middleinitial");
		String lName = request.getParameter("lastname");;
		String userName = request.getParameter("username");
		String password = request.getParameter("confirmpassword");
		String email = request.getParameter("email");
		
		//Billing Address:
		String houseNoB = request.getParameter("housenoB");
		String streetB = request.getParameter("streetB");
		String subdivisionB = request.getParameter("subdivisionB");
		String cityB = request.getParameter("cityB");
		String postalCodeB = request.getParameter("postalcodeB");
		String countryB = request.getParameter("countryB");
		
		//Shipping Address:
		String houseNoS = request.getParameter("housenoS");
		String streetS = request.getParameter("streetS");
		String subdivisionS = request.getParameter("subdivisionS");
		String cityS = request.getParameter("cityS");
		String postalCodeS = request.getParameter("postalcodeS");
		String countryS = request.getParameter("countryS");
		
		
		
		doGet(request, response);
	}

}
