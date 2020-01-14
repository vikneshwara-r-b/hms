/*
 *
 */
package global.coda.hmsbackend.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;

import global.coda.hmsbackend.constants.DBConstants;

// TODO: Auto-generated Javadoc
/**
 * The Class DBConnection.
 */
public class DBConnection {
	/** The db connection. */
	private static Connection dbConnection = null;

	/**
	 * Establish connection.
	 *
	 * @return the connection
	 * @throws ClassNotFoundException the class not found exception
	 * @throws SQLException           the SQL exception
	 */
	public static Connection establishConnection() throws ClassNotFoundException, SQLException {
		LogManager.getLogger(DBConnection.class);
		if (dbConnection == null) {
			Class.forName(DBConstants.DRIVER_CLASS);
			dbConnection = DriverManager.getConnection(DBConstants.URL, DBConstants.USERNAME, DBConstants.PASSWORD);
		}
		return dbConnection;
	}

	/**
	 * Close connection.
	 *
	 * @throws SQLException the SQL exception
	 */
	public void closeConnection() throws SQLException {
		dbConnection.close();
	}
}
