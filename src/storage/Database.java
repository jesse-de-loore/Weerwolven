package storage ;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Database connection class
 * Connects to the database and stores this connection in a 
 * unique instance. 
 */
public class Database {
	
	private static Connection con ;
	
	// Singleton:
	private static Database uniqueInstance ;
	
	/**
	 * Sets up the unique instance of the Singleton
	 * @return The unique instance
	 */
	public static Database getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new Database() ;
		}
		return uniqueInstance ;
	}
	
	/**
	 * Private contructor to make a new connection
	 */
	private Database() {
		// Get a connection
		System.out.println("start");
		con = makeConnection("booksys.properties") ;
		System.out.println("end");
	}
	
	/**
	 * Gets the unique connection
	 * @return The unique connection
	 */
	public Connection getConnection() {
		return con ;
	}
	
	/**
	 * Makes a new connection to the server defined in the properties file
	 * @param propFile File containing the connection properties
	 * @return The newly created connection
	 */
	private Connection makeConnection(String propFile) {
		Properties props = new Properties() ;
		InputStream pfile = null ;
		Connection con = null ;
		
		// Load in the property file
		try {
			pfile = new FileInputStream(propFile) ;
			//pfile =  Thread.currentThread().getContextClassLoader().getResourceAsStream(propFile);
			props.load(pfile) ;
			pfile.close() ;
		}
		catch (IOException e) {
			e.printStackTrace() ;
			return con ;
		}
		finally {
			if (pfile != null) {
				try {
					pfile.close() ;
				}
				catch (IOException ignored) {}
			}
		}
		
		// Load the Driver class
		String driver = props.getProperty("jdbc.driver") ;
		try {
			Class.forName(driver) ;
		}
		catch (ClassNotFoundException e) {
			System.out.println("driver not loaded");
			e.printStackTrace() ;
			return con ;
		}
		
		// Try to make a connection
		String dbURL = props.getProperty("jdbc.url") ;
		try{
			System.out.println("start2");
			con = DriverManager.getConnection(dbURL);
			System.out.println("end");
		}
		catch (SQLException e) {
			System.out.println("url = " + dbURL);
			e.printStackTrace() ;
		}
		return con ;
	}
}
