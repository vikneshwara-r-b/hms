/*
 *
 */
package global.coda.hmsbackend.utils;

import static global.coda.hmsbackend.constants.NumericConstants.EIGHT;
import static global.coda.hmsbackend.constants.NumericConstants.ELEVEN;
import static global.coda.hmsbackend.constants.NumericConstants.FIVE;
import static global.coda.hmsbackend.constants.NumericConstants.FOUR;
import static global.coda.hmsbackend.constants.NumericConstants.NINE;
import static global.coda.hmsbackend.constants.NumericConstants.ONE;
import static global.coda.hmsbackend.constants.NumericConstants.SEVEN;
import static global.coda.hmsbackend.constants.NumericConstants.SIX;
import static global.coda.hmsbackend.constants.NumericConstants.TEN;
import static global.coda.hmsbackend.constants.NumericConstants.THREE;
import static global.coda.hmsbackend.constants.NumericConstants.TWO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import global.coda.hmsbackend.constants.ApplicationConstants;
import global.coda.hmsbackend.models.User;

// TODO: Auto-generated Javadoc
/**
 * The Class DaoUtil.
 */
public class DaoUtil {

	/** The resource bundle. */
	ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(ApplicationConstants.MESSAGES_BUNDLE);
	/** The logger. */
	private final Logger LOGGER = LogManager.getLogger(DaoUtil.class);

	/**
	 * Sets the id for user.
	 *
	 * @param userPreparedStatement the user prepared statement
	 * @param user                  the user
	 * @return true, if successful
	 * @throws SQLException the SQL exception
	 */
	public boolean setIdForUser(PreparedStatement userPreparedStatement, User user) throws SQLException {
		LOGGER.traceEntry();
		boolean result = false;
		ResultSet resultSetId = userPreparedStatement.getGeneratedKeys();
		if ((resultSetId != null) && resultSetId.next()) {
			int userId = resultSetId.getInt(ONE);
			user.setUserId(userId);
			LOGGER.info(RESOURCE_BUNDLE.getString(ApplicationConstants.HMS1005I));
			result = true;
		}
		LOGGER.traceExit();
		return result;
	}

	/**
	 * Sets the user details.
	 *
	 * @param userPreparedStatement the user prepared statement
	 * @param user                  the user
	 * @throws SQLException the SQL exception
	 */
	public void setUserDetails(PreparedStatement userPreparedStatement, User user) throws SQLException {
		LOGGER.traceEntry();
		userPreparedStatement.setString(ONE, user.getUsername());
		userPreparedStatement.setString(TWO, user.getPassword());
		userPreparedStatement.setString(THREE, user.getEmail());
		userPreparedStatement.setString(FOUR, user.getPhonenumber());
		userPreparedStatement.setString(FIVE, user.getFirstname());
		userPreparedStatement.setString(SIX, user.getLastname());
		userPreparedStatement.setInt(SEVEN, user.getAge());
		userPreparedStatement.setString(EIGHT, user.getCity());
		userPreparedStatement.setString(NINE, user.getState());
		userPreparedStatement.setString(TEN, user.getStreet());
		userPreparedStatement.setInt(ELEVEN, user.getRoleId());
		LOGGER.info(RESOURCE_BUNDLE.getString(ApplicationConstants.HMS1005I));
		LOGGER.traceExit();
	}
}
