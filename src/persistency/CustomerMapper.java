/*
 * Restaurant Booking System: example code to accompany
 *
 * "Practical Object-oriented Design with UML"
 * Mark Priestley
 * McGraw-Hill (2004)
 */

package persistency;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import storage.Database;
import application.Customer;

public class CustomerMapper extends Mapper {
	// Implementation of hidden cache
	
	// Singleton:
	private static CustomerMapper uniqueInstance;
	

	/**
	 * Private constructor for singleton
	 */
	private CustomerMapper() {
	}
	
	/**
	 * Sets up the unique instance of the Singleton
	 * @return The unique instance
	 */
	public static CustomerMapper getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new CustomerMapper();
		}
		return uniqueInstance;
	}
	
	/**
	 * Gets a customer from the database by the customers name
	 * @param customerName The name of the customer
	 * @return The desired customer
	 */
	public PersistentCustomer getCustomer(String customerName) {
		PersistentCustomer pc = null;
		String select = "SELECT oid, name, phoneNumber FROM Customer WHERE name = ?";
		try {
			PreparedStatement pstmt = Database.getInstance().getConnection().prepareStatement(select);
			pstmt.setString(1, customerName);
			pc = this.queryCustomer(pstmt);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pc;
	}
	
	/**
	 * Gets a customer from the database by his object ID
	 * @param oid The object ID of the desired customere
	 * @return The desired customer
	 */
	public PersistentCustomer getCustomerForOid(int oid) {
		PersistentCustomer pc = queryCustomer("SELECT oid, name, phoneNumber FROM Customer WHERE oid ='"
				+ oid + "'");
		return pc;
	}
	
	/**
	 * Executes an SQL query and formats the result set in a Customer object
	 * @param sql The SQL query to execute
	 * @return The desired customer
	 */
	private PersistentCustomer queryCustomer(String sql) {
		PersistentCustomer pc = null;
		try {
			/* Get the database connection from the current instance in the database 
			 * class. For this connection create a new statement 
			 */ 
			Statement stmt = Database.getInstance().getConnection().createStatement();
			ResultSet rset = stmt.executeQuery(sql);			// Execute a query in the statement
			while (rset.next()) {								// Load the result set
				int oid = rset.getInt(1);
				String customerName = rset.getString(2);
				int phoneNumber = rset.getInt(3);
				pc = new PersistentCustomer(oid, customerName, phoneNumber);	// Create a new object from the data
			}
			rset.close();		// close the result set
			stmt.close();		// close the statement
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pc;
	}
	
	/**
	 * Executes an SQL query from a prepared statement and formats the result set in a Customer object
	 * @param pstmt The prepared statement to execute
	 * @return The desired customer
	 */
	private PersistentCustomer queryCustomer(PreparedStatement pstmt) {
		PersistentCustomer pc = null;
		try {
			ResultSet rset = pstmt.executeQuery();			// Execute the statement
			while (rset.next()) {								// Load the result set
				int oid = rset.getInt(1);
				String customerName = rset.getString(2);
				int phoneNumber = rset.getInt(3);
				pc = new PersistentCustomer(oid, customerName, phoneNumber);	// Create a new object from the data
			}
			rset.close();		// close the result set
			pstmt.close();		// close the statement
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pc;
	}
	
	/**
	 * Gets all customer names
	 * @return A Collection of all customer names
	 */
	public List<String> getCustomerNames() {
		List<String> v = new LinkedList<String>();
		try {
			Statement stmt = Database.getInstance().getConnection().createStatement();
			ResultSet rset = stmt
			.executeQuery("SELECT name FROM Customer ORDER BY name");
			while (rset.next()) {
				v.add(rset.getString(1)); // scroll trough the data and fill the collection
			}
			rset.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return v;
	}
	
	/**
	 * Stores a customer in the database
	 * @param customer The customer object that needs to be stored
	 * @return The newly stored customer
	 */
	public PersistentCustomer createCustomer(Customer customer) {
		performUpdate("INSERT INTO Customer (name, phoneNumber) VALUES ('"
				+ customer.getName() + "', " + customer.getPhoneNumber() + ")");
		return new PersistentCustomer(this.getLastUpdateKey(), customer.getName(), customer.getPhoneNumber());
	}
	
	/**
	 * Deletes a customer from the database
	 * @param customer The customer that needs to be deleted
	 * @return The number of deleted customers (1 if everything went OK, 0 if the customer was not found)
	 */
	public int deleteCustomer(PersistentCustomer pc) {
		return performUpdate("DELETE FROM Customer WHERE oid = "
				+ ((PersistentCustomer) pc).getId() + "");
	}
	
	/**
	 * Update a customer in the database
	 * @param pc The customer containing the new data
	 * @return The number of updated customers (1 if everything went OK, 0 if the customer was not found)
	 */
	public int updateCustomer(PersistentCustomer pc) {
		StringBuffer sql = new StringBuffer(128) ;
		
		// Build the SQL statement
		sql.append("UPDATE Customer SET name = '") ;
		sql.append(pc.getName()) ;
		sql.append("', phoneNumber = ") ;
		sql.append(pc.getPhoneNumber()) ;
		sql.append(" WHERE oid = ") ;
		sql.append(pc.getId()) ;
		
		return performUpdate(sql.toString()) ;
	}
}
