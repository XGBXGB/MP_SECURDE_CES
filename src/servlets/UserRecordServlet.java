package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import controller.Controller;
import model.FinancialRecord;

/**
 * Servlet implementation class UserRecordServlet
 */
@WebServlet("/UserRecordServlet")
public class UserRecordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserRecordServlet() {
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
		Controller controller = Controller.getInstance();
		String sortBy = request.getParameter("sortBy");
		System.out.println("sortBy: "+sortBy);
		/*if(sortBy.equals("all")){
			double total = controller.getTotalPriceAllProducts();
			response.getWriter().print(total);
		}else{ 
			Gson gson = new Gson();
			JsonElement element;
			JsonArray jsonArray;
			ArrayList<FinancialRecord> records = null;
			if(sortBy.equals("product")){
				records = controller.getTotalPriceByProduct();
			}else if(sortBy.equals("category")){
				records = controller.getTotalPriceByCategory();
			}
			element = gson.toJsonTree(records, new TypeToken<List<FinancialRecord>>(){}.getType());
			jsonArray = element.getAsJsonArray();
			response.setContentType("application/json");
			response.getWriter().print(jsonArray);
		}*/
	}

}
