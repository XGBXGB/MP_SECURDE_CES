package controller;

import db.AddressDAO;
import db.UserDAO;
import model.Address;
import model.User;

public class Controller {
	private static Controller instance = null;
	private UserDAO userDao;
	private AddressDAO addressDao;
	
	public static Controller getInstance() {
		if (instance == null) {
			instance = new Controller();
		}
		return instance;
	}
	
	public Controller(){
		userDao = new UserDAO();
		addressDao = new AddressDAO();
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
	
	public int validateAddress(Address address)
	{
		return addressDao.validateAddress(address);
	}
	
}
