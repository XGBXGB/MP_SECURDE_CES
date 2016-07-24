package model;

public class User {
	public static String TABLE_NAME = "users";
	public static String COLUMN_ID = "id";
	public static String COLUMN_USERNAME = "username";
	public static String COLUMN_PASSWORD = "password";
	public static String COLUMN_FNAME = "firstname";
	public static String COLUMN_LNAME = "lastname";
	
	private int id;
	private String username;
	private String psasword;
	private String lastName;
	private String firstName;
	private String middleName;
	private String email;
	private int billingAddressId;
	private int shippingAddressId;
	private String userType;
	
	
	
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
	public String getPsasword() {
		return psasword;
	}
	public void setPsasword(String psasword) {
		this.psasword = psasword;
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
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	
	
}
