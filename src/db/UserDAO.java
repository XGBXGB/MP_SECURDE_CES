package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import controller.Controller;
import db.DBConnection;
import model.BCrypt;
import model.Product;
import model.User;

public class UserDAO {
	DBConnection dbConnection;

	public UserDAO() {
		dbConnection = DBConnection.getInstance();
		// controller = Controller.getInstance();
	}
	
	public User getUser(int id) {
		String query = "SELECT * FROM " + User.TABLE_NAME + " WHERE " + User.COLUMN_ID + " = ?;";

		Connection connection = DBConnection.getConnection();
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
			if (rs.next()) {
				User u = new User();
				u.setId(rs.getInt(User.COLUMN_ID));
				String userP = rs.getString(User.COLUMN_PASSWORD);
				u.setUsername(rs.getString(User.COLUMN_USERNAME));
				u.setFirstName(rs.getString(User.COLUMN_FNAME));
				u.setLastName(rs.getString(User.COLUMN_LNAME));
				u.setEmail(rs.getString(User.COLUMN_EMAIL));
				u.setMiddleName(rs.getString(User.COLUMN_MNAME));
				u.setBillingAddressId(rs.getInt(User.COLUMN_BILLING));
				u.setShippingAddressId(rs.getInt(User.COLUMN_SHIPPING));
				u.setUserType(rs.getInt(User.COLUMN_TYPE));
				return u;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public String getUserType(int id) {
		String query = "SELECT * FROM usertypes WHERE id = ?;";

		Connection connection = DBConnection.getConnection();
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
			if (rs.next()) {
				return rs.getString("type");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	
	public User authenticateUser(String username, String password) {
		String query = "SELECT * FROM " + User.TABLE_NAME + " WHERE " + User.COLUMN_USERNAME + " = ?;";

		Connection connection = DBConnection.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, username);
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		try {
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				User u = new User();
				u.setId(rs.getInt(User.COLUMN_ID));
				String userP = rs.getString(User.COLUMN_PASSWORD);
				u.setUsername(rs.getString(User.COLUMN_USERNAME));
				u.setFirstName(rs.getString(User.COLUMN_FNAME));
				u.setLastName(rs.getString(User.COLUMN_LNAME));
				u.setEmail(rs.getString(User.COLUMN_EMAIL));
				u.setMiddleName(rs.getString(User.COLUMN_MNAME));
				u.setBillingAddressId(rs.getInt(User.COLUMN_BILLING));
				u.setShippingAddressId(rs.getInt(User.COLUMN_SHIPPING));
				u.setUserType(rs.getInt(User.COLUMN_TYPE));
				if (BCrypt.checkpw(password, userP))
					return u;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public boolean validateUsername(String username) {
		String query = "SELECT * FROM " + User.TABLE_NAME + " WHERE " + User.COLUMN_USERNAME + " = ? ";

		Connection connection = dbConnection.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, username);
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		try {
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	public boolean validateEmail(String email) {
		String query = "SELECT * FROM " + User.TABLE_NAME + " WHERE " + User.COLUMN_EMAIL + " = ? ";

		Connection connection = dbConnection.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, email);
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		try {
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	
	public ArrayList<User> getAllUsersForTable(){
		String query = "SELECT u."+User.COLUMN_USERNAME+", u."+User.COLUMN_LNAME+", u."+User.COLUMN_FNAME+", "
				+"u."+User.COLUMN_MNAME+", u."+User.COLUMN_TYPE+", u.expiry "
				+ "FROM " + User.TABLE_NAME + " u "
				+ "ORDER BY "+User.COLUMN_USERNAME+" ASC ";
		System.out.println("query: "+query);
		Connection connection = dbConnection.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(query);
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		ArrayList<User> users = new ArrayList<User>();
		try {
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				
				User u = new User();
				u.setUsername(rs.getString(User.COLUMN_USERNAME));
				u.setLastName(rs.getString(User.COLUMN_LNAME));
				u.setFirstName(rs.getString(User.COLUMN_FNAME));
				u.setMiddleName(rs.getString(User.COLUMN_MNAME));
				u.setUserType(rs.getInt(User.COLUMN_TYPE));
				u.setExpiry(rs.getDate(User.COLUMN_EXPIRY));
				users.add(u);
			}
			return users;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void addUser(User user) {
		String query = "INSERT INTO " + User.TABLE_NAME + " " + " (" + User.COLUMN_USERNAME + "," + User.COLUMN_PASSWORD
				+ "," + User.COLUMN_LNAME + "," + User.COLUMN_FNAME + "," + User.COLUMN_MNAME + "," + User.COLUMN_EMAIL
				+ "," + User.COLUMN_BILLING + "," + User.COLUMN_SHIPPING + "," + User.COLUMN_TYPE + ") " + " VALUES ('"
				+ user.getUsername() + "', '" + user.getpassword() + "', '" + user.getLastName() + "', '"
				+ user.getFirstName() + "', '" + user.getMiddleName() + "', '" + user.getEmail() + "', "
				+ user.getBillingAddressId() + ", " + user.getShippingAddressId() + ", " + user.getUserType() + ");";

		System.out.println("QUERY: " + query);
		Connection connection = dbConnection.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(query);
			pstmt.executeUpdate();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	}

}
