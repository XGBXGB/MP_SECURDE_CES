package model;

public class Address {

	public static final String TABLE_NAME = "address";
	public static final String COLUMN_ID = "id";
	public static final String COLUMN_HN = "houseNumber";
	public static final String COLUMN_STREET = "street";
	public static final String COLUMN_SUB = "subdivision";
	public static final String COLUMN_CITY = "city";
	public static final String COLUMN_PCODE = "postalCode";
	public static final String COLUMN_COUNTRY = "country";
	
	int id;
	int houseNumber;
	String street;
	String subdivision;
	String city;
	String postalCode;
	String country;
	
	
	
	public Address(int id, int houseNumber, String street, String subdivision, String city, String postalCode,
			String country) {
		super();
		this.id = id;
		this.houseNumber = houseNumber;
		this.street = street;
		this.subdivision = subdivision;
		this.city = city;
		this.postalCode = postalCode;
		this.country = country;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getHouseNumber() {
		return houseNumber;
	}
	public void setHouseNumber(int houseNumber) {
		this.houseNumber = houseNumber;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getSubdivision() {
		return subdivision;
	}
	public void setSubdivision(String subdivision) {
		this.subdivision = subdivision;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	
	
}
