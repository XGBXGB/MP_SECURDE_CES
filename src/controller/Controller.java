package controller;

import java.util.ArrayList;

import db.AddressDAO;
import db.CategoriesDAO;
import db.ProductsDAO;
import db.UserDAO;
import model.Address;
import model.Product;
import model.User;

public class Controller {
	private static Controller instance = null;
	private UserDAO userDao;
	private AddressDAO addressDao;
	private ProductsDAO productsDao;
	private CategoriesDAO categoriesDao;
	
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
	}
	
	public ArrayList<String> getCategories(){
		return categoriesDao.getAllCategories();
	}
	
	
	public ArrayList<Product> getAllProducts(){
		return productsDao.getAllProducts();
	}
	
	public ArrayList<Product> getProductsviaCategory(int category){
		return productsDao.getProductsviaCategory(category);
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
}
