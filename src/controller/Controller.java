package controller;

import db.UserDAO;
import model.User;

public class Controller {
	private static Controller instance = null;
	private UserDAO userDao;
	
	public static Controller getInstance() {
		if (instance == null) {
			instance = new Controller();
		}
		return instance;
	}
	
	public Controller(){
		userDao = new UserDAO();
	}
	
	public User authenticateUser(String username, String encryptedPass){
		return userDao.authenticateUser(username, encryptedPass);
	}
}
