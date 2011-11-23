package persistency;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import storage.Database;

/**
 *	Parent class from which all mappings between objects and database can be extended
 */
public class Mapper {
	
	private int lastUpdateKey;
	
	/**
	 * Perfoms an SQL UPDATE update statement to the active instance of the database
	 * @param sql The desired UPDATE statement
	 * @return The number of affected rows
	 */
	protected int performUpdate(String sql) {
		try {
			Statement stmt = Database.getInstance().getConnection().createStatement();
			int updateCount = stmt.executeUpdate(sql,Statement.RETURN_GENERATED_KEYS);
                        Database.getInstance().getConnection().commit();
                        ResultSet rs = stmt.getGeneratedKeys();
			if (rs != null) {
				rs.next();
				this.lastUpdateKey = rs.getInt(1);
			}
			stmt.close();
			return updateCount;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	/**
	 * Gets the primary key for the record that was last updates
	 * @return the primary key
	 */
	public int getLastUpdateKey() {
		return lastUpdateKey;
	}
	
}
