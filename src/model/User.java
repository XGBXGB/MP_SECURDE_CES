package model;

import java.sql.Date;

public class User {
	public static String TABLE_NAME = "users";
	public static String COLUMN_ID = "id";
	public static String COLUMN_USERNAME = "username";
	public static String COLUMN_PASSWORD = "password";
	public static String COLUMN_FNAME = "firstname";
	public static String COLUMN_LNAME = "lastname";
	public static String COLUMN_MNAME = "middlename";
	public static String COLUMN_EMAIL = "email";
	public static String COLUMN_BILLING = "billingAddressID";
	public static String COLUMN_SHIPPING = "shippingAddressID";
	public static String COLUMN_TYPE = "typeID";
	public static String COLUMN_EXPIRY = "expiry";
	
	private int id;
	private String username;
	private String password;
	private String lastName;
	private String firstName;
	private String middleName;
	private String email;
	private int billingAddressId;
	private int shippingAddressId;
	private int userType;
	private Date expiry;
	
	
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public User(int id, String username, String password, String lastName, String firstName, String middleName,
			String email, int billingAddressId, int shippingAddressId, int userType) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.lastName = lastName;
		this.firstName = firstName;
		this.middleName = middleName;
		this.email = email;
		this.billingAddressId = billingAddressId;
		this.shippingAddressId = shippingAddressId;
		this.userType = userType;
	}

	public Date getExpiry() {
		return expiry;
	}

	public void setExpiry(Date expiry) {
		this.expiry = expiry;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getpassword() {
		return password;
	}
	public void setpassword(String password) {
		this.password = password;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getBillingAddressId() {
		return billingAddressId;
	}
	public void setBillingAddressId(int billingAddressId) {
		this.billingAddressId = billingAddressId;
	}
	public int getShippingAddressId() {
		return shippingAddressId;
	}
	public void setShippingAddressId(int shippingAddressId) {
		this.shippingAddressId = shippingAddressId;
	}
	public int getUserType() {
		return userType;
	}
	public void setUserType(int userType) {
		this.userType = userType;
	}
	
	
	
}
