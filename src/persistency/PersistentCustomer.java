package persistency ;

import application.Customer;

/**
 * The persistent object of a customer. This extends the customer 
 * object with a unique identifier, so it can be easily handled
 * by the database
 */
public class PersistentCustomer extends Customer{
	private int oid ;
	
	/**
	 * Constructor to create a new persistent customer
	 * @param id The object ID
	 * @param n The customer name
	 * @param p The phone number
	 */
	public PersistentCustomer(int id, String n, int p) {
		super(n,p);
		oid = id ;
	}
	
	/**
	 * Gets the object ID
	 * @return The object ID
	 */
	public int getId() {
		return oid ;
	}
}
