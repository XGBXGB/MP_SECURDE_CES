package controller;

import java.util.ArrayList;
import java.util.Locale.Category;

import db.AddressDAO;
import db.CategoriesDAO;
import db.ProductsDAO;
import db.TransactionsDAO;
import db.UserDAO;
import model.Address;
import model.Product;
import model.Transaction;
import model.User;

public class Controller {
	private static Controller instance = null;
	private UserDAO userDao;
	private AddressDAO addressDao;
	private ProductsDAO productsDao;
	private CategoriesDAO categoriesDao;
	private TransactionsDAO transactionsDao;
	
	public static Controller getInstance() {
		if (instance == null) {
			instance = new Controller();
		}
		return instance;
	}
	
	public Controller(){
		categoriesDao = new CategoriesDAO();
		productsDao = new ProductsDAO();
		userDao = new UserDAO();
		addressDao = new AddressDAO();
		transactionsDao = new TransactionsDAO();
	}
	
	public ArrayList<String> getCategories(){
		return categoriesDao.getAllCategories();
	}
	
	public String getCategory(int id){
		return categoriesDao.getCategory(id);
	}
	
	
	public ArrayList<Product> getAllProducts(){
		return productsDao.getAllProducts();
	}
	
	public ArrayList<Product> getProductsviaCategory(int category){
		return productsDao.getProductsviaCategory(category);
	}
	
	public Product getProduct(int id)
	{
		return productsDao.getProduct(id);
	}
	
	public User getUser(int id){
		return userDao.getUser(id);
	}
	
	public User authenticateUser(String username, String encryptedPass){
		return userDao.authenticateUser(username, encryptedPass);
	}
	
	public boolean validateUsername(String username)
	{
		return userDao.validateUsername(username);
	}
	
	public boolean validateEmail(String email)
	{
		return userDao.validateEmail(email);
	}
	
	public void addUser(User user)
	{
		userDao.addUser(user);
	}
	
	public boolean validateAddress(Address address)
	{
		return addressDao.validateAddress(address);
	}

	public void addAddress(Address address) {
		addressDao.addAddress(address);	
	}
	
	public int getAddressID(Address address)
	{
		return addressDao.getAddressID(address);
	}
	
	public ArrayList<Transaction> getTransactionsviaProduct(int productID)
	{
		return transactionsDao.getTransactionsviaProduct(productID);
	}
	
	public ArrayList<Transaction> getTransactionsviaProductReviewed(int productID)
	{
		return transactionsDao.getTransactionsviaProductReviewed(productID);
	}
	
	public double getProductScore(int productID)
	{
		return transactionsDao.getProductScore(productID);
	}
}
