package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import controller.Controller;
import db.DBConnection;
import model.FinancialRecord;
import model.Product;
import model.User;

public class ProductsDAO {
	DBConnection dbConnection;

	public ProductsDAO() {
		dbConnection = DBConnection.getInstance();
		//controller = Controller.getInstance();
	}
	public void addProduct(Product p) {
		// TODO Auto-generated method stub
		System.out.println("Added " + p.getName());
		String query = "INSERT INTO " + Product.TABLE_NAME + " " + " (" 
				+ Product.COLUMN_NAME+ ","
				+ Product.COLUMN_PRICE+ ","
				+ Product.COLUMN_CAT+ ","
				+ Product.COLUMN_DESC + ") " + " VALUES (?, ?, ?, ?);";

		System.out.println("QUERY: " + query);
		Connection connection = dbConnection.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, p.getName());
			pstmt.setDouble(2, p.getPrice());
			pstmt.setInt(3, p.getCategoryId());
			pstmt.setString(4, p.getDescription());
			pstmt.executeUpdate();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
	}

	public void deleteProduct(String productId) {
		// TODO Auto-generated method stub
		System.out.println("Deleted " + productId);
		String query = "DELETE FROM " + Product.TABLE_NAME + " WHERE " + Product.COLUMN_ID +  " = ?";

		System.out.println("QUERY: " + query);
		Connection connection = dbConnection.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, productId);
			pstmt.executeUpdate();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	}

	public void editProduct(Product p) {
		String query = "UPDATE " + Product.TABLE_NAME
				+ " SET "
					+ Product.COLUMN_NAME +  " = ?, "
					+ Product.COLUMN_CAT + " = ?, " 
					+ Product.COLUMN_PRICE + " = ?, "
					+ Product.COLUMN_DESC + " = ? "
				+ "WHERE " + Product.COLUMN_ID + " = ?";

		System.out.println("QUERY: " + query);
		Connection connection = dbConnection.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, p.getName());
			pstmt.setInt(2, p.getCategoryId());
			pstmt.setDouble(3, p.getPrice());
			pstmt.setString(4, p.getDescription());
			pstmt.setInt(5, p.getId());
			pstmt.executeUpdate();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
	}
	
	
	public Product getProduct(int id) {
		String query = "SELECT * FROM " + Product.TABLE_NAME + " WHERE " + Product.COLUMN_ID + " = ? ";
				
		Connection connection = dbConnection.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(query);
			pstmt.setInt(1, id);
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		try {
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()){
				Product p = new Product();
				p.setId(rs.getInt(Product.COLUMN_ID));
				p.setName(rs.getString(Product.COLUMN_NAME));
				p.setDescription(rs.getString(Product.COLUMN_DESC));
				p.setPrice(rs.getDouble(Product.COLUMN_PRICE));
				p.setCategoryId(rs.getInt(Product.COLUMN_CAT));
				return p;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public Product getProduct(String name) {
		String query = "SELECT * FROM " + Product.TABLE_NAME + " WHERE " + Product.COLUMN_NAME + " = ? ";
				
		Connection connection = dbConnection.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, name);
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		try {
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()){
				Product p = new Product();
				p.setId(rs.getInt(Product.COLUMN_ID));
				p.setName(rs.getString(Product.COLUMN_NAME));
				p.setDescription(rs.getString(Product.COLUMN_DESC));
				p.setPrice(rs.getDouble(Product.COLUMN_PRICE));
				p.setCategoryId(rs.getInt(Product.COLUMN_CAT));
				return p;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Product> getProductsviaCategory(int category) {
		String query = "SELECT * FROM " + Product.TABLE_NAME + " WHERE " + Product.COLUMN_CAT + " = ? ";
				
		Connection connection = dbConnection.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(query);
			pstmt.setInt(1, category);
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		ArrayList<Product> products = new ArrayList<Product>();
		try {
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				Product p = new Product();
				p.setId(rs.getInt(Product.COLUMN_ID));
				p.setName(rs.getString(Product.COLUMN_NAME));
				p.setDescription(rs.getString(Product.COLUMN_DESC));
				p.setPrice(rs.getDouble(Product.COLUMN_PRICE));
				p.setCategoryId(rs.getInt(Product.COLUMN_CAT));
				products.add(p);
			}
			return products;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public double getTotalPriceAllProducts(){
		String query = "SELECT SUM(p.price) AS total "
				+ "FROM transactions t, products p "
				+ "WHERE t.productID = p.id;";
		Connection connection = dbConnection.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(query);
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		try {
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()){
				return rs.getDouble("total");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	public ArrayList<FinancialRecord> getTotalPriceByProduct(){
		String query = "SELECT p.name, SUM(p.price) AS total "
				+ "FROM transactions t, products p "
				+ "WHERE t.productID = p.id "
				+ "GROUP BY p.name;";
		Connection connection = dbConnection.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(query);
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		ArrayList<FinancialRecord> records = new ArrayList();
		try {
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				records.add(new FinancialRecord(rs.getString("name"),rs.getDouble("total")));
			}
			return records;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<FinancialRecord> getTotalPriceByCategory(){
		String query = "SELECT c.category, SUM(p.price) AS total "
				+ "FROM transactions t "
				+ "INNER JOIN products p "
				+ "ON t.productID = p.id "
				+ "INNER JOIN categories c "
				+ "ON p.categoryID = c.id "
				+ "GROUP BY c.category;";
		Connection connection = dbConnection.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(query);
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		ArrayList<FinancialRecord> records = new ArrayList();
		try {
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				records.add(new FinancialRecord(rs.getString("category"),rs.getDouble("total")));
			}
			return records;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Product> getProductsByName(String name) {
		String query = "SELECT * FROM " + Product.TABLE_NAME + " WHERE " + Product.COLUMN_NAME + " LIKE ? ";
		System.out.println("PRODNAME: "+name);
		Connection connection = dbConnection.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, "%"+name+"%");
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		ArrayList<Product> products = new ArrayList<Product>();
		try {
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				
				Product p = new Product();
				p.setId(rs.getInt(Product.COLUMN_ID));
				p.setName(rs.getString(Product.COLUMN_NAME));
				System.out.println("IN: "+p.getName());
				p.setDescription(rs.getString(Product.COLUMN_DESC));
				p.setPrice(rs.getDouble(Product.COLUMN_PRICE));
				p.setCategoryId(rs.getInt(Product.COLUMN_CAT));
				products.add(p);
			}
			return products;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Product> getAllProducts() {
		String query = "SELECT * FROM " + Product.TABLE_NAME + " ORDER BY "+Product.COLUMN_NAME+" ASC";
				
		Connection connection = dbConnection.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(query);
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		ArrayList<Product> products = new ArrayList<Product>();
		try {
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				Product p = new Product();
				p.setId(rs.getInt(Product.COLUMN_ID));
				p.setName(rs.getString(Product.COLUMN_NAME));
				p.setDescription(rs.getString(Product.COLUMN_DESC));
				p.setPrice(rs.getDouble(Product.COLUMN_PRICE));
				p.setCategoryId(rs.getInt(Product.COLUMN_CAT));
				products.add(p);
			}
			return products;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Product> getProductsviaPriceRange(double minimum, double maximum) {
		String query = "SELECT * FROM " + Product.TABLE_NAME + " WHERE " + Product.COLUMN_PRICE + " BETWEEN ? AND ? ";
				
		Connection connection = dbConnection.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(query);
			pstmt.setDouble(1, minimum);
			pstmt.setDouble(2, maximum);	
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		ArrayList<Product> products = new ArrayList<Product>();
		try {
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				Product p = new Product();
				p.setId(rs.getInt(Product.COLUMN_ID));
				p.setName(rs.getString(Product.COLUMN_NAME));
				p.setDescription(rs.getString(Product.COLUMN_DESC));
				p.setPrice(rs.getDouble(Product.COLUMN_PRICE));
				p.setCategoryId(rs.getInt(Product.COLUMN_CAT));
				products.add(p);
			}
			return products;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
