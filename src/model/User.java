package model;

public class User {
	public static String TABLE_NAME = "user";
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

	
	private int id;
	private String username;
	private String psasword;
	private String lastName;
	private String firstName;
	private String middleName;
	private String email;
	private int billingAddressId;
	private int shippingAddressId;
	private int userType;
	
	
	
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
	public int getUserType() {
		return userType;
	}
	public void setUserType(int userType) {
		this.userType = userType;
	}
	
	
	
}
