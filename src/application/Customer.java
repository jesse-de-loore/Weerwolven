package application ;

/**
 * This Class holds all the information about a customer
 */
public class Customer {
	private String customerName ;
	private int phoneNumber ;
	
	/**
	 * Constructor to create a new Customer object
	 * @param n Customer name
	 * @param p Customer phone number
	 */
	public Customer(String n, int p) {
		this.customerName = n ;
		this.phoneNumber  = p ;
	}
	
	/**
	 * Gets the customer name
	 * @return The customer name
	 */
	public String getName() {
		return this.customerName ;
	}
	
	/**
	 * Gets the phone number
	 * @return The phone number
	 */
	public int getPhoneNumber() {
		return this.phoneNumber ;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Customer :" + this.getName() + " phonenumber: " + this.getPhoneNumber();
	}
}
