package application;

import junit.framework.TestCase;

public class CustomerTest extends TestCase {

	/*
	 * Test method for 'application.Customer.Customer(int, int)'
	 */
	public void testCustomer() {
		Customer testCustomer = new Customer("Koen",1234);
		assertNotNull("Customer object not created", testCustomer);
	}

	/*
	 * Test method for 'application.Customer.getNumber()'
	 */
	public void testGetName() {
		Customer testCustomer = new Customer("Koen",1234);
		assertEquals("Customer name false", testCustomer.getName(),"Koen");
	}

	/*
	 * Test method for 'application.Customer.getPlaces()'
	 */
	public void testGetPhoneNumber() {
		Customer testCustomer = new Customer("Koen",1234);
		assertEquals("Phone number false", testCustomer.getPhoneNumber(),1234);
	}

}
