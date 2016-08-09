package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Address;
import model.BCrypt;
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
		
		
		
		String fName  = request.getParameter("firstname");//name
		String mInitial = request.getParameter("middleinitial");//singleLetyrt
		String lName = request.getParameter("lastname");//name
		String userName = request.getParameter("username");//letters_numbers
		String password = request.getParameter("confirmpassword");//letters_numbers
		String email = request.getParameter("email");
		
		//Billing Address:
		int houseNoB = Integer.parseInt(request.getParameter("housenoB"));//numbers
		String streetB = request.getParameter("streetB");//name
		String subdivisionB = request.getParameter("subdivisionB");//name
		String cityB = request.getParameter("cityB");//name
		String postalCodeB = request.getParameter("postalcodeB");//numbers
		String countryB = request.getParameter("countryB");//na
		
		//Shipping Address:
		int houseNoS = Integer.parseInt(request.getParameter("housenoS"));//numbers
		String streetS = request.getParameter("streetS");//name
		String subdivisionS = request.getParameter("subdivisionS");//name
		String cityS = request.getParameter("cityS");//name
		String postalCodeS = request.getParameter("postalcodeS");//numbers
		String countryS = request.getParameter("countryS");//na
		
		Pattern name = Pattern.compile("^[ A-z]{1,}$");
		Pattern singleLetter = Pattern.compile("[A-z]");
		Pattern letters_numbers = Pattern.compile("^[_A-z0-9]{1,}$");
		Pattern numbers = Pattern.compile("^[0-9]{1,}$");
		
		if(!(name.matcher(fName).matches() && name.matcher(lName).matches() && name.matcher(streetB).matches()
		   && name.matcher(subdivisionB).matches() && name.matcher(cityB).matches() && name.matcher(streetS).matches()
		   && name.matcher(subdivisionS).matches() && name.matcher(cityS).matches() 
		   && numbers.matcher(""+houseNoB).matches() && numbers.matcher(postalCodeB).matches()
		   && numbers.matcher(""+houseNoS).matches() && numbers.matcher(postalCodeS).matches()
		   && singleLetter.matcher(mInitial).matches() && letters_numbers.matcher(userName).matches()
		   && letters_numbers.matcher(password).matches())){
			
			PrintWriter out = response.getWriter();
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Double check format of input fields');");
			out.println("</script>");
			
		}
		
		
		
		
		//SIGNUP FUCNTION
		Controller controller = new Controller();
		if(controller.validateUsername(userName))
		{
			if(controller.validateEmail(email))
			{
				//encrypt the password to check against what's stored in DB
				String encryptedPass = BCrypt.hashpw(password, BCrypt.gensalt());
				
				boolean b = controller.validateAddress( new Address(0, houseNoB, streetB, subdivisionB, cityB, postalCodeB, countryB));
				int bid = -1;
				if(!b)
				{
					controller.addAddress(new Address(0, houseNoB, streetB, subdivisionB, cityB, postalCodeB, countryB));
					bid = controller.getAddressID(new Address(0, houseNoB, streetB, subdivisionB, cityB, postalCodeB, countryB));
				}
				
				boolean s = controller.validateAddress( new Address(0, houseNoS, streetS, subdivisionS, cityS, postalCodeS, countryS));
				
				int sid = -1;
				if(!s)
				{
					controller.addAddress(new Address(0, houseNoS, streetS, subdivisionS, cityS, postalCodeS, countryS));
					sid = controller.getAddressID(new Address(0, houseNoS, streetS, subdivisionS, cityS, postalCodeS, countryS));
				}
				controller.addUser(new User(0, userName, encryptedPass, lName, fName, mInitial, email, bid, sid, 4));				
				PrintWriter out = response.getWriter();
				out.println("<script type=\"text/javascript\">");
				out.println("alert('You can now login using your account!');");
				out.println("location='login.jsp';");
				out.println("</script>");
				
//				String url = "login.jsp";
//				response.sendRedirect(url);
//				RequestDispatcher dispatcher = request.getRequestDispatcher(url);
//				dispatcher.forward(request, response);
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
