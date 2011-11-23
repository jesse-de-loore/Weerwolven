package persistency;

import junit.framework.TestCase;

public class PersistentCustomerTest extends TestCase {

	/*
	 * Test method for 'persistency.PersistentCustomer.PersistentCustomer(int, int, int)'
	 */
	public void testPersistentCustomer() {
		PersistentCustomer testCustomer = new PersistentCustomer(1,"Koen",1234);
		assertNotNull("Customer object not created", testCustomer);
	}

	/*
	 * Test method for 'persistency.PersistentCustomer.getId()'
	 */
	public void testGetId() {
		PersistentCustomer testCustomer = new PersistentCustomer(1,"Koen",1234);
		assertEquals("Object ID false", testCustomer.getId(),1);
	}

}
