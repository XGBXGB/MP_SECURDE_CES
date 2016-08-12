package controller;

import java.util.ArrayList;
import java.util.Locale.Category;

import db.AddressDAO;
import db.CategoriesDAO;
import db.ProductsDAO;
import db.TransactionsDAO;
import db.UserDAO;
import model.Address;
import model.FinancialRecord;
import model.LogWriter;
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
	private LogWriter logWriter;

	public static Controller getInstance() {
		if (instance == null) {
			instance = new Controller();
		}
		return instance;
	}

	public Controller() {
		categoriesDao = new CategoriesDAO();
		productsDao = new ProductsDAO();
		userDao = new UserDAO();
		addressDao = new AddressDAO();
		transactionsDao = new TransactionsDAO();
		logWriter = new LogWriter();
	}

	public ArrayList<User> getAllUsersForTable() {
		return userDao.getAllUsersForTable();
	}

	public void updatePassword(int userId, String password) {
		userDao.updatePassword(userId, password);
	}

	public ArrayList<String> getCategories() {
		return categoriesDao.getAllCategories();
	}

	public String getCategory(int id) {
		return categoriesDao.getCategory(id);
	}

	public ArrayList<Product> getProductsByName(String name) {
		return productsDao.getProductsByName(name);
	}

	public ArrayList<Product> getAllProducts() {
		System.out.println("inside get all products");
		return productsDao.getAllProducts();
	}

	public ArrayList<Product> getProductsviaCategory(int category) {
		System.out.println("inside get products via category");
		return productsDao.getProductsviaCategory(category);
	}

	public double getTotalPriceAllProducts() {
		return productsDao.getTotalPriceAllProducts();
	}

	public ArrayList<FinancialRecord> getTotalPriceByProduct() {
		return productsDao.getTotalPriceByProduct();
	}

	public ArrayList<FinancialRecord> getTotalPriceByCategory() {
		return productsDao.getTotalPriceByCategory();
	}

	public Product getProduct(int id) {
		return productsDao.getProduct(id);
	}

	public User getUser(int id) {
		return userDao.getUser(id);
	}

	public User authenticateUser(String username, String encryptedPass) {
		return userDao.authenticateUser(username, encryptedPass);
	}

	public boolean validateUsername(String username) {
		return userDao.validateUsername(username);
	}

	public boolean validateEmail(String email) {
		return userDao.validateEmail(email);
	}

	public void addUser(User user) {
		userDao.addUser(user);
	}

	public boolean validateAddress(Address address) {
		return addressDao.validateAddress(address);
	}

	public void addAddress(Address address) {
		addressDao.addAddress(address);
	}

	public int getAddressID(Address address) {
		return addressDao.getAddressID(address);
	}

	public ArrayList<Transaction> getTransactionsviaProduct(int productID) {
		return transactionsDao.getTransactionsviaProduct(productID);
	}

	public ArrayList<Transaction> getTransactionsviaProductReviewed(int productID) {
		return transactionsDao.getTransactionsviaProductReviewed(productID);
	}

	public double getProductScore(int productID) {
		return transactionsDao.getProductScore(productID);
	}

	public void addTransaction(Transaction t) {
		transactionsDao.addTransaction(t);
	}

	public void addTransactionWithReview(Transaction t) {
		transactionsDao.addTransactionWithReview(t);
	}

	public String getUserType(int id) {
		return userDao.getUserType(id);
	}

	public void addProduct(Product p) {
		// TODO Auto-generated method stub
		productsDao.addProduct(p);

	}

	public void deleteProduct(String productId) {
		// TODO Auto-generated method stub
		productsDao.deleteProduct(productId);

	}

	public void editProduct(Product p) {
		// TODO Auto-generated method stub
		productsDao.editProduct(p);

	}

	public void checkExpired() {
		userDao.checkExpired();
	}

	public void log(String logString) {
		// TODO Auto-generated method stub
		logWriter.write(logString);
	}
}
