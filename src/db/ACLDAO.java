package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ACLDAO {

	DBConnection dbConnection;

	public ACLDAO() {
		dbConnection = DBConnection.getInstance();
		// controller = Controller.getInstance();
	}
	
	public boolean hasAccess(int role, String page) {
		String query = "SELECT * FROM ACL A, pages P WHERE A.role = ? AND P.id = A.page AND P.page = ?;";

		Connection connection = DBConnection.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(query);
			pstmt.setInt(1, role);
			pstmt.setString(2, page);
			
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		try {
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				return true;
			}
			return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public String getDefaultRedirect(int role) {
		String query = "SELECT defaultpage FROM usertypes WHERE id = ?;";

		Connection connection = DBConnection.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(query);
			pstmt.setInt(1, role);
			
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		try {
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getString("defaultpage");
			}
			return "";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	
}
