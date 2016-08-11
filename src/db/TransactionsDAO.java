package db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import controller.Controller;
import db.DBConnection;
import model.Address;
import model.Transaction;
import model.User;

public class TransactionsDAO {
	DBConnection dbConnection;

	public TransactionsDAO() {
		dbConnection = DBConnection.getInstance();
		//controller = Controller.getInstance();
	}
	
	public Transaction getTransaction(int id) {
		String query = "SELECT * FROM " + Transaction.TABLE_NAME + " WHERE " + Transaction.COLUMN_ID + " = ? ";
				
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
				Transaction t = new Transaction();
				t.setId(rs.getInt(Transaction.COLUMN_ID));
				t.setProductId(rs.getInt(Transaction.COLUMN_PID));
				t.setUserId(rs.getInt(Transaction.COLUMN_UID));
				t.setScore(rs.getDouble(Transaction.COLUMN_SCORE));
				t.setReview(rs.getString(Transaction.COLUMN_REV));
				t.setDate(rs.getTimestamp(Transaction.COLUMN_DATE));
				return t;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Transaction> getTransactionsviaUser(int userID) {
		String query = "SELECT * FROM " + Transaction.TABLE_NAME + " WHERE " + Transaction.COLUMN_UID + " = ? ";
				
		Connection connection = dbConnection.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(query);
			pstmt.setInt(1, userID);
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		ArrayList<Transaction> transactions = new ArrayList<Transaction>();
		try {
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				Transaction t = new Transaction();
				t.setId(rs.getInt(Transaction.COLUMN_ID));
				t.setProductId(rs.getInt(Transaction.COLUMN_PID));
				t.setUserId(rs.getInt(Transaction.COLUMN_UID));
				t.setScore(rs.getDouble(Transaction.COLUMN_SCORE));
				t.setReview(rs.getString(Transaction.COLUMN_REV));
				t.setDate(rs.getTimestamp(Transaction.COLUMN_DATE));
				transactions.add(t);
			}
			return transactions;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Transaction> getTransactionsviaProduct(int productID) {
		String query = "SELECT * FROM " + Transaction.TABLE_NAME + " WHERE " + Transaction.COLUMN_PID + " = ? ";
				
		Connection connection = dbConnection.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(query);
			pstmt.setInt(1, productID);
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		ArrayList<Transaction> transactions = new ArrayList<Transaction>();
		try {
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				Transaction t = new Transaction();
				t.setId(rs.getInt(Transaction.COLUMN_ID));
				t.setProductId(rs.getInt(Transaction.COLUMN_PID));
				t.setUserId(rs.getInt(Transaction.COLUMN_UID));
				t.setScore(rs.getDouble(Transaction.COLUMN_SCORE));
				t.setReview(rs.getString(Transaction.COLUMN_REV));
				t.setDate(rs.getTimestamp(Transaction.COLUMN_DATE));
				transactions.add(t);
			}
			return transactions;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Transaction> getTransactionsviaProductReviewed(int productID) {
		String query = "SELECT * FROM " + Transaction.TABLE_NAME + " WHERE " + Transaction.COLUMN_PID + " = ? AND review IS NOT NULL;";
				
		Connection connection = dbConnection.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(query);
			pstmt.setInt(1, productID);
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		ArrayList<Transaction> transactions = new ArrayList<Transaction>();
		try {
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				Transaction t = new Transaction();
				t.setId(rs.getInt(Transaction.COLUMN_ID));
				t.setProductId(rs.getInt(Transaction.COLUMN_PID));
				t.setUserId(rs.getInt(Transaction.COLUMN_UID));
				t.setScore(rs.getDouble(Transaction.COLUMN_SCORE));
				t.setReview(rs.getString(Transaction.COLUMN_REV));
				t.setDate(rs.getTimestamp(Transaction.COLUMN_DATE));
				transactions.add(t);
			}
			return transactions;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public double getProductScore(int productID) {
		String query = "SELECT * FROM " + Transaction.TABLE_NAME + " WHERE " + Transaction.COLUMN_PID + " = ? AND score IS NOT NULL;";
				
		Connection connection = dbConnection.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(query);
			pstmt.setInt(1, productID);
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		double sum = 0;
		int count = 0;
		try {
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				sum += rs.getDouble(Transaction.COLUMN_SCORE);
				count++;
			}
			return sum/count;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	public ArrayList<Transaction> getTransactionsviaDate(Date date) {
		String query = "SELECT * FROM " + Transaction.TABLE_NAME + " WHERE " + Transaction.COLUMN_DATE + " = ? ";
				
		Connection connection = dbConnection.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(query);
			pstmt.setDate(1, date);
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		ArrayList<Transaction> transactions = new ArrayList<Transaction>();
		try {
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				Transaction t = new Transaction();
				t.setId(rs.getInt(Transaction.COLUMN_ID));
				t.setProductId(rs.getInt(Transaction.COLUMN_PID));
				t.setUserId(rs.getInt(Transaction.COLUMN_UID));
				t.setScore(rs.getDouble(Transaction.COLUMN_SCORE));
				t.setReview(rs.getString(Transaction.COLUMN_REV));
				t.setDate(rs.getTimestamp(Transaction.COLUMN_DATE));
				transactions.add(t);
			}
			return transactions;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	public ArrayList<Transaction> getTransactionsviaProductAndUser(int productID, int userID) {
		String query = "SELECT * FROM " + Transaction.TABLE_NAME + " WHERE " + Transaction.COLUMN_PID + " = ? "
					+ " AND " + Transaction.COLUMN_UID + " = ? ";
				
		Connection connection = dbConnection.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(query);
			pstmt.setInt(1, productID);
			pstmt.setInt(2, userID);
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		ArrayList<Transaction> transactions = new ArrayList<Transaction>();
		try {
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				Transaction t = new Transaction();
				t.setId(rs.getInt(Transaction.COLUMN_ID));
				t.setProductId(rs.getInt(Transaction.COLUMN_PID));
				t.setUserId(rs.getInt(Transaction.COLUMN_UID));
				t.setScore(rs.getDouble(Transaction.COLUMN_SCORE));
				t.setReview(rs.getString(Transaction.COLUMN_REV));
				t.setDate(rs.getTimestamp(Transaction.COLUMN_DATE));
				transactions.add(t);
			}
			return transactions;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public void addTransaction(Transaction t) {
		String query = "INSERT INTO " + Transaction.TABLE_NAME + " "
				+ "("+ Transaction.COLUMN_PID +"," + Transaction.COLUMN_UID + "," + Transaction.COLUMN_DATE +") "
				+ "VALUES (" + t.getProductId() + ", " + t.getUserId() + ", NOW() );";
		
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
	
	public void addTransactionWithReview(Transaction t) {
		String query = "INSERT INTO " + Transaction.TABLE_NAME + " "
				+ "("+ Transaction.COLUMN_PID +"," + Transaction.COLUMN_UID + "," + Transaction.COLUMN_DATE +
				"," + Transaction.COLUMN_REV +"," + Transaction.COLUMN_SCORE+") "
				+ "VALUES (" + t.getProductId() + ", " + t.getUserId() + ", NOW() , '" + t.getReview()
				+ "', " + t.getScore() +");";
		
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
