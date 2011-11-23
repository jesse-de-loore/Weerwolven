package persistency;

import application.Customer;
import junit.framework.TestCase;

public class CustomerMapperTest extends TestCase {

	/*
	 * Test method for 'persistency.CustomerMapper.getInstance()'
	 */
	public void testGetInstance() {
		assertNotNull("Instance not created",CustomerMapper.getInstance());
	}

	/*
	 * Test method for 'persistency.CustomerMapper.getCustomer(String)'
	 */
	public void testGetCustomer() {
		assertEquals("Customer name not returned correctly",
				CustomerMapper.getInstance().getCustomer("Pelsmaekers").getName(),"Pelsmaekers");
	}

	/*
	 * Test method for 'persistency.CustomerMapper.getCustomerForOid(int)'
	 */
	public void testGetCustomerForOid() {
		assertEquals("Customer object ID not returned correctly",
				CustomerMapper.getInstance().getCustomerForOid(1).getId(),1);
	}

	/*
	 * Test method for 'persistency.CustomerMapper.getCustomerNames()'
	 */
	public void testGetCustomerNames() {

	}
	
	/*
	 * Test method for 'persistency.CustomerMapper.createCustomer(Customer)'
	 */
	public void testCreateCustomer() {
		PersistentCustomer testCustomer = CustomerMapper.getInstance().createCustomer(new Customer("Patrick",1235));
		assertEquals("No consistent ID's when creating a customer",
				CustomerMapper.getInstance().getCustomer("Patrick").getId(),
				testCustomer.getId());
		CustomerMapper.getInstance().deleteCustomer(testCustomer);
	}

	/*
	 * Test method for 'persistency.CustomerMapper.deleteCustomer(Customer)'
	 */
	public void testDeleteCustomer() {
		PersistentCustomer testCustomer = CustomerMapper.getInstance().createCustomer(new Customer("Patrick",1235));
		assertEquals("Customer not deleted",
				CustomerMapper.getInstance().deleteCustomer(testCustomer),1);
	}

	/*
	 * Test method for 'persistency.CustomerMapper.updateCustomer(Customer)'
	 */
	public void testUpdateCustomer() {
		PersistentCustomer testCustomer = CustomerMapper.getInstance().createCustomer(new Customer("Patrick",1235));
		PersistentCustomer updatedCustomer = new PersistentCustomer(testCustomer.getId(),"Patrick",1236);
		assertEquals("Update not succeded",CustomerMapper.getInstance().updateCustomer(updatedCustomer),1);
		assertEquals("Updated value not changed",
				updatedCustomer.getPhoneNumber(),
				CustomerMapper.getInstance().getCustomer("Patrick").getPhoneNumber());
		CustomerMapper.getInstance().deleteCustomer(testCustomer);
	}
}
