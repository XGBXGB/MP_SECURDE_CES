package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import controller.Controller;
import db.DBConnection;
import model.Product;
import model.User;

public class CategoriesDAO {
	DBConnection dbConnection;

	public CategoriesDAO() {
		dbConnection = DBConnection.getInstance();
		//controller = Controller.getInstance();
	}
	
	public ArrayList<String> getAllCategories() {
		String query = "SELECT * FROM categories; ";
				
		Connection connection = dbConnection.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(query);
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		ArrayList<String> categories = new ArrayList<>();
		try {
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				categories.add(rs.getString("category"));
			}
			return categories;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
