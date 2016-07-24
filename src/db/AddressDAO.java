package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import controller.Controller;
import db.DBConnection;
import model.Address;

public class AddressDAO {
	DBConnection dbConnection;

	public AddressDAO() {
		dbConnection = DBConnection.getInstance();
		//controller = Controller.getInstance();
	}
	
	public boolean validateAddress(Address address) {
		String query = "SELECT * FROM " + Address.TABLE_NAME + " WHERE " + 
						Address.COLUMN_HN + " = ? "
						+ "AND "+ Address.COLUMN_STREET + "= ? "
						+ "AND "+ Address.COLUMN_SUB + "= ? "
						+ "AND "+ Address.COLUMN_CITY + "= ? "
						+ "AND "+ Address.COLUMN_PCODE + "= ? "
						+ "AND "+ Address.COLUMN_COUNTRY + "= ?;";
		
		Connection connection = dbConnection.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(query);
			pstmt.setInt(1, address.getHouseNumber());
			pstmt.setString(2, address.getStreet());
			pstmt.setString(3, address.getSubdivision());
			pstmt.setString(4, address.getCity());
			pstmt.setString(5, address.getPostalCode());
			pstmt.setString(6, address.getCountry());
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			//e2.printStackTrace();
			//return addAddress(address);
		}
		
		try {
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()){
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		return false;
	}
	
	public int getAddressID(Address address)
	{
		String query = "SELECT * FROM " + Address.TABLE_NAME + " WHERE " + 
				Address.COLUMN_HN + " = ? "
				+ "AND "+ Address.COLUMN_STREET + "= ? "
				+ "AND "+ Address.COLUMN_SUB + "= ? "
				+ "AND "+ Address.COLUMN_CITY + "= ? "
				+ "AND "+ Address.COLUMN_PCODE + "= ? "
				+ "AND "+ Address.COLUMN_COUNTRY + "= ?;";

			Connection connection = dbConnection.getConnection();
			PreparedStatement pstmt = null;
			try {
				pstmt = connection.prepareStatement(query);
				pstmt.setInt(1, address.getHouseNumber());
				pstmt.setString(2, address.getStreet());
				pstmt.setString(3, address.getSubdivision());
				pstmt.setString(4, address.getCity());
				pstmt.setString(5, address.getPostalCode());
				pstmt.setString(6, address.getCountry());
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				//e2.printStackTrace();
				//return addAddress(address);
			}
			
			try {
				ResultSet rs = pstmt.executeQuery();
				if(rs.next()){
					return rs.getInt(Address.COLUMN_ID);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
			return -1;
	}
	
	public void addAddress(Address address)
	{
		String query = "INSERT INTO " + Address.TABLE_NAME + " "
				+ "("+ Address.COLUMN_HN +"," + Address.COLUMN_STREET + "," + Address.COLUMN_SUB + "," + Address.COLUMN_CITY + ","
				+ Address.COLUMN_PCODE + "," + Address.COLUMN_COUNTRY +") "
				+ "VALUES (" + address.getHouseNumber() + ", '" + address.getStreet()
		+ "', '" + address.getSubdivision() + "', '" + address.getCity() + "', " + address.getPostalCode() + ", '" + address.getCountry() + "');";
		
		Connection connection = dbConnection.getConnection();
		System.out.println(query);
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(query);
			pstmt.executeUpdate();
			
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			//e2.printStackTrace();
		}			
		
	}
}
