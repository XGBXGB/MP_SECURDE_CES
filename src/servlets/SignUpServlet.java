package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Address;
import model.PasswordService;
import model.User;

/**
 * Servlet implementation class SignUpServlet
 */
@WebServlet(name="SignUpServlet",urlPatterns={"/SignUpServlet"})
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
		int houseNoB = Integer.parseInt(request.getParameter("housenoB"));
		String streetB = request.getParameter("streetB");
		String subdivisionB = request.getParameter("subdivisionB");
		String cityB = request.getParameter("cityB");
		String postalCodeB = request.getParameter("postalcodeB");
		String countryB = request.getParameter("countryB");
		
		//Shipping Address:
		int houseNoS = Integer.parseInt(request.getParameter("housenoS"));
		String streetS = request.getParameter("streetS");
		String subdivisionS = request.getParameter("subdivisionS");
		String cityS = request.getParameter("cityS");
		String postalCodeS = request.getParameter("postalcodeS");
		String countryS = request.getParameter("countryS");
		
		
		//SIGNUP FUCNTION
		Controller controller = new Controller();
		if(controller.validateUsername(userName))
		{
			if(controller.validateEmail(email))
			{
				//encrypt the password to check against what's stored in DB
				PasswordService pws = new PasswordService();
				String encryptedPass = pws.encrypt(password);
				
				int b = controller.validateAddress( new Address(0, houseNoB, streetB, subdivisionB, cityB, postalCodeB, countryB));
				int s = controller.validateAddress( new Address(0, houseNoS, streetS, subdivisionS, cityS, postalCodeS, countryS));
				controller.addUser(new User(0, userName, encryptedPass, lName, fName, mInitial, email, b, s, 4));				
				PrintWriter out = response.getWriter();
				out.println("<script type=\"text/javascript\">");
				out.println("alert('You can now login using your account!');");
				out.println("location='index.jsp';");
				out.println("</script>");
				
				String url = "index.jsp";
				RequestDispatcher dispatcher = request.getRequestDispatcher(url);
				dispatcher.forward(request, response);
			}
			else
			{
				PrintWriter out = response.getWriter();
				out.println("<script type=\"text/javascript\">");
				out.println("alert('That email already exists! Choose a different one.');");
				out.println("</script>");

			}
		}
		else
		{
			PrintWriter out = response.getWriter();
			out.println("<script type=\"text/javascript\">");
			out.println("alert('That username already exists! Choose a different one.');");
			out.println("</script>");
			
		}
		
		
		//SIGNUP FUNCTION
		
		
		
		//RESPONSE
	}

}
