package model;

public class Product {

	public static String TABLE_NAME = "products";
	public static String COLUMN_ID = "id";
	public static String COLUMN_NAME = "name";
	public static String COLUMN_DESC = "description";
	public static String COLUMN_CAT = "categoryID";
	public static String COLUMN_PRICE = "price";
	public static String COLUMN_STATUS = "status";

	private int id;
	private String name;
	private String description;
	private int categoryId;
	private double price;
	private boolean status;

	public Product() {
		super();
		this.id = 0;
		this.name = "";
		this.description = "";
		this.categoryId = 0;
		this.price = 0;
	}

	public Product(int id, String name, String description, int categoryId, double price, boolean status) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.categoryId = categoryId;
		this.price = price;
		this.status = status;
	}
	
	public boolean getStatus() {
		return status;
	}
	
	public void setStatus(boolean status) {
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}
