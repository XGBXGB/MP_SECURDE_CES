package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import controller.Controller;
import db.DBConnection;
import model.User;

public class UserDAO {
	DBConnection dbConnection;

	public UserDAO() {
		dbConnection = DBConnection.getInstance();
		//controller = Controller.getInstance();
	}
	
	public User authenticateUser(String username, String encryptedPass) {
		String query = "SELECT * FROM " + User.TABLE_NAME + " WHERE " + User.COLUMN_USERNAME + " = ? "
				+ "AND "+User.COLUMN_PASSWORD+"= ?;";
		
		Connection connection = dbConnection.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, username);
			pstmt.setString(2, encryptedPass);
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		try {
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()){
				User u = new User();
				u.setId(rs.getInt(User.COLUMN_ID));
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
}
