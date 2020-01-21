/*
 *
 */
package global.coda.hmsbackend.constants;

/**
 * Database authentication constants.
 *
 * @author Vc
 *
 */
public final class DBConstants {

	/**
	 * Instantiates a new DB constants.
	 */
	private DBConstants() {
		// DBConstants
	}

	/** The Constant DRIVER_CLASS. */
	public static final String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";

	/** The Constant URL. */
	public static final String URL = "jdbc:mysql://localhost:3306/hospital_management_system";

	/** The Constant USERNAME. */
	public static final String USERNAME = "root";

	/** The Constant PASSWORD. */
	public static final String PASSWORD = "admin";
}
