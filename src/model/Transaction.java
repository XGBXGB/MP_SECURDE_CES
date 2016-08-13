package model;

import java.sql.Timestamp;

public class Transaction {
	
	public static final String TABLE_NAME = "transactions";
	public static final String COLUMN_ID = "id";
	public static final String COLUMN_PID = "productID";
	public static final String COLUMN_UID = "userID";
	public static final String COLUMN_SCORE = "score";
	public static final String COLUMN_REV = "review";
	public static final String COLUMN_DATE = "date";
	
	int id;
	int productId;
	int userId;
	double score;
	double price;
	String review;
	Timestamp date;
	
	public Transaction() {
		super();
	}

	public Transaction(int id, int productId, int userId, double score, String review, Timestamp date, double price) {
		super();
		this.id = id;
		this.productId = productId;
		this.userId = userId;
		this.review = review;
		this.date = date;
		this.score = score;
		this.price = price;
	}
	
	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	
	
	
}
