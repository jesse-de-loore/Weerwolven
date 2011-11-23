package storage;

import junit.framework.TestCase;

public class DatabaseTest extends TestCase {

	/*
	 * Test method for 'storage.Database.getInstance()'
	 */
	public void testGetInstance() {
		assertNotNull("Instance not created",Database.getInstance());
	}

	/*
	 * Test method for 'storage.Database.getConnection()'
	 */
	public void testGetConnection() {
		assertNotNull("Connection not set up",Database.getInstance().getConnection());
	}
}
