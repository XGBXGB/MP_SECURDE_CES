package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static DBConnection instance = null;

    private String driverName;
    private String url;
    private String database;
    private String username;
    private String password;


    DBConnection() {
        driverName = "com.mysql.jdbc.Driver";
        url = "jdbc:mysql://localhost:3306/";
        database = "mp_securde";
        username = "root";
        password = "";//tempo
    }
     

    public static DBConnection getInstance() {
        if (instance == null) {
            instance = new DBConnection();
        }

        return instance;
    }


    public static Connection getConnection() {
        /*if (instance == null) {
            instance = new DBConnection();
        }*/

    	instance = new DBConnection();
        
    	try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			return DriverManager.getConnection(instance.getUrl()
                    + instance.getDatabase(),
                    instance.getUsername(),
                    instance.getPassword());
        } catch (SQLException se) {
            se.printStackTrace();
        }

        return null;
    }


    public String getUrl() {
        return url;
    }
    public String getDatabase() {
        return database;
    }


    public String getUsername() {
        return username;
    }

    private String getPassword() {
        return password;
    }


    public boolean isCorrectPassword(String password) {
        return password.equals(this.password);
    }
}
