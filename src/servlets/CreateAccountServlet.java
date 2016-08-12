package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import controller.Controller;
import model.Address;
import model.BCrypt;
import model.User;

/**
 * Servlet implementation class CreateAccountServlet
 */
@WebServlet("/CreateAccountServlet")
public class CreateAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateAccountServlet() {
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
		
		System.out.println("IN POST CREATE ACCOUNT");
		Controller controller = new Controller();
		
		String adminPW = "false";
		String acctPW = "false";
		String dupeUN = "true";
		
		if (request.getSession().getAttribute("user") == null) {
			PrintWriter out = response.getWriter();
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Session Expired! Please try logging in again.');");
			out.println("location='index.jsp';");
			out.println("</script>");
			String logString = System.currentTimeMillis() + " " 
					+ "CreateAccount " 
					+ "-" + " " 
					+ request.getRemoteAddr() 
					+ " timeout";
			controller.log(logString);
		} else {
			User user = (User)request.getSession().getAttribute("user");
			Controller con = new Controller();
			if(con.authenticateUser(((User)request.getSession().getAttribute("user")).getUsername(), request.getParameter("password"))!= null)
			{	
				String fName = request.getParameter("firstname");
				String mInitial = request.getParameter("middleinitial");
				String lName = request.getParameter("lastname");

				String userName = request.getParameter("username");
				String password = request.getParameter("pw");
				String cpassword = request.getParameter("confirmpassword");
				String userType = request.getParameter("userType");

				Pattern name = Pattern.compile("^[ A-z]{1,}$");
				Pattern singleLetter = Pattern.compile("[A-z]");
				Pattern letters_numbers = Pattern.compile("^[_A-z0-9]{1,}$");

				if (!(name.matcher(fName).matches() && name.matcher(lName).matches()
						&& singleLetter.matcher(mInitial).matches()) && letters_numbers.matcher(userName).matches()
						&& letters_numbers.matcher(password).matches() && letters_numbers.matcher(cpassword).matches()) {

					PrintWriter out = response.getWriter();
					out.println("<script type=\"text/javascript\">");
					out.println("alert('Double check format of input fields');");
					out.println("location='admincreate.jsp';");
					out.println("</script>");
					String logString = System.currentTimeMillis() + " " + "CreateAccount " + user.getId() + " " + request.getRemoteAddr() + " wrongFormat";
					controller.log(logString);
					System.out.println(logString);
					
					acctPW = "true";
				} 
				else if(!password.equals(cpassword))
				{
					//PrintWriter out = response.getWriter();
					//out.println("<script type=\"text/javascript\">");
					//out.println("alert('Passwords do not match.');");
					//out.println("location='admincreate.jsp';");
					//out.println("</script>");
					String logString = System.currentTimeMillis() + " " + "CreateAccount " + user.getId() + " " + request.getRemoteAddr() + " PasswordMismatch";
					controller.log(logString);
					System.out.println(logString);
					
					acctPW = "false";
				}
				else {

					// SIGNUP FUCNTION
					if (controller.validateUsername(userName)) {
						String encryptedPass = BCrypt.hashpw(password, BCrypt.gensalt());
						User u = new User();
						u.setUsername(userName);
						u.setpassword(encryptedPass);
						u.setLastName(lName);
						u.setFirstName(fName);
						u.setMiddleName(mInitial);
						if (userType.equals("Accounting Manager")) {
							u.setUserType(3);
						} else {
							u.setUserType(2);
						}
						controller.addUser(u);
						String logString = System.currentTimeMillis() + " " + "CreateAccount " + user.getId() + " " + request.getRemoteAddr() + " successful";
						controller.log(logString);
						System.out.println(logString);
						//PrintWriter out = response.getWriter();
						//out.println("<script type=\"text/javascript\">");
						//out.println("alert('Account Created!');");
						//out.println("location='admincreate.jsp';");
						//out.println("</script>");
						dupeUN = "false";
					} else {
						//PrintWriter out = response.getWriter();
						//out.println("<script type=\"text/javascript\">");
						//out.println("alert('That username already exists! Choose a different one.');");
						//out.println("location='admincreate.jsp';");
						//out.println("</script>");
						dupeUN = "true";
					}
				}

				System.out.println("correct password when creating");
				//response.getWriter().print("true");
				acctPW = "true";
				adminPW = "true";
			}
			else
			{
				System.out.println("Wrong password when creating");
				//response.getWriter().print("false");
				adminPW = "false";
				dupeUN = "false";
				acctPW = "true";
				String logString = System.currentTimeMillis() + " " 
						+ "CreateAccount " 
						+ user.getId() + " " 
						+ request.getRemoteAddr() 
						+ " wrongPassword";
				controller.log(logString);
				//request.setAttribute("bought", "wrong pw");
				//response.sendRedirect("product.jsp");
			}
			String json1 = new Gson().toJson(adminPW); 
			String json2 = new Gson().toJson(acctPW); 
			String json3 = new Gson().toJson(dupeUN); 
			response.setContentType("application/json"); 
			response.setCharacterEncoding("utf-8"); 
			String bothJson = "["+json1+","+json2+"," + json3 + "]"; //Put both objects in an array of 2 elements
			response.getWriter().write(bothJson);
			
			
		}
	}
}
