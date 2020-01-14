/*
 *
 */
package global.coda.hmsbackend.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import global.coda.hmsbackend.config.DBConnection;
import global.coda.hmsbackend.constants.NumericConstants;
import global.coda.hmsbackend.constants.QueryConstants;
import global.coda.hmsbackend.exception.DoctorNotFound;
import global.coda.hmsbackend.models.Doctor;
import global.coda.hmsbackend.utils.DaoUtil;

// TODO: Auto-generated Javadoc
/**
 * The Class DoctorDAO.
 */
public class DoctorDAO {

	/** The logger. */
	private final Logger LOGGER = LogManager.getLogger(DoctorDAO.class);

	/** The userdao. */
	private DaoUtil userDao;

	/**
	 * Read doctor.
	 *
	 * @param doctorId the doctor id
	 * @return the doctor
	 * @throws ClassNotFoundException the class not found exception
	 * @throws SQLException           the SQL exception
	 * @throws DoctorNotFound         the doctor not found
	 */
	public Doctor readDoctor(int doctorId) throws ClassNotFoundException, SQLException {
		LOGGER.traceEntry(Integer.toString(doctorId));
		List<Doctor> alldoctors = null;
		Doctor doctor = null;
		ResultSet resultData;
		Connection databaseConnection = DBConnection.establishConnection();
		PreparedStatement preparedStatementforDoctor = databaseConnection.prepareStatement(QueryConstants.DOCTOR_READ);
		preparedStatementforDoctor.setInt(NumericConstants.ONE, doctorId);
		resultData = preparedStatementforDoctor.executeQuery();
		alldoctors = getDoctorDetails(resultData);
		if (alldoctors.size() < 1) {
			return null;
		}
		doctor = alldoctors.get(0);
		LOGGER.traceExit(doctor.toString());
		return doctor;
	}

	/**
	 * Creates the doctor.
	 *
	 * @param doctor the doctor
	 * @return the doctor
	 * @throws ClassNotFoundException the class not found exception
	 * @throws SQLException           the SQL exception
	 */
	public Doctor createDoctor(Doctor doctor) throws ClassNotFoundException, SQLException {
		LOGGER.traceEntry(doctor.toString());
		Connection databaseConnection;
		databaseConnection = DBConnection.establishConnection();
		try {
			int rowsChangedForDoctor = 0;
			int rowsChangedForUser = 0;
			userDao = new DaoUtil();
			databaseConnection.setAutoCommit(false);
			PreparedStatement preparedStatementForUser = databaseConnection.prepareStatement(QueryConstants.USER_INSERT,
					Statement.RETURN_GENERATED_KEYS);
			userDao.setUserDetails(preparedStatementForUser, doctor);
			rowsChangedForUser = preparedStatementForUser.executeUpdate();
			if (rowsChangedForUser == 1) {
				if (userDao.setIdForUser(preparedStatementForUser, doctor)) {
					LOGGER.info("User details are set successfully");
					PreparedStatement preparedStatementForDoctor = databaseConnection
							.prepareStatement(QueryConstants.DOCTOR_INSERT, Statement.RETURN_GENERATED_KEYS);
					setDoctorDetails(preparedStatementForDoctor, doctor);
					rowsChangedForDoctor = preparedStatementForDoctor.executeUpdate();
					if (rowsChangedForDoctor == 1) {
						if (setIdForDoctor(preparedStatementForDoctor, doctor)) {
							LOGGER.info("Doctor ID is set successfully");
							databaseConnection.commit();
						} else {
							LOGGER.info("Doctor ID is not set");
						}
					}
				}
			} else {
				LOGGER.info("User ID is not set");
			}
		} catch (SQLException e) {
			LOGGER.info("Exception while creating doctor");
			databaseConnection.rollback();
			throw (e);
		}
		LOGGER.traceExit(doctor.toString());
		return doctor;
	}

	/**
	 * Update doctor.
	 *
	 * @param userId the user id
	 * @param doctor the doctor
	 * @return the doctor
	 * @throws SQLException           the SQL exception
	 * @throws DoctorNotFound         the doctor not found
	 * @throws ClassNotFoundException the class not found exception
	 */
	public Doctor updateDoctor(int userId, Doctor doctor) throws SQLException, DoctorNotFound, ClassNotFoundException {
		LOGGER.traceEntry(Integer.toString(userId));
		LOGGER.traceExit(doctor.toString());
		userDao = new DaoUtil();
		int rowsChangedForDoctor = 0, rowsChangedForUser = 0;
		Doctor checkDoctor = null;
		checkDoctor = readDoctor(userId);
		if (checkDoctor != null) {
			Connection databaseConnection = DBConnection.establishConnection();
			try {
				databaseConnection.setAutoCommit(false);
				PreparedStatement userpreparedStatement = databaseConnection
						.prepareStatement(QueryConstants.USER_UPDATE);
				userDao.setUserDetails(userpreparedStatement, doctor);
				doctor.setDoctorId(userId);
				userpreparedStatement.setInt(NumericConstants.ELEVEN, userId);
				rowsChangedForUser = userpreparedStatement.executeUpdate();
				if (rowsChangedForUser > 0) {
					LOGGER.info("User details are updated successfully");
					PreparedStatement doctorPreparedStatement = databaseConnection
							.prepareStatement(QueryConstants.DOCTOR_UPDATE);
					setDoctorDetails(doctorPreparedStatement, doctor);
					doctorPreparedStatement.setInt(NumericConstants.THREE, userId);
					rowsChangedForDoctor = doctorPreparedStatement.executeUpdate();
					if (rowsChangedForDoctor > 0) {
						LOGGER.info("Doctor details are updated successfully");
						databaseConnection.commit();
					}
				}
			} catch (SQLException e) {
				databaseConnection.rollback();
				LOGGER.info("Exception while updating patient");
				throw (e);
			}
		} else {
			throw new DoctorNotFound("Doctor details are not found");
		}
		LOGGER.traceExit(doctor.toString());
		return doctor;
	}

	/**
	 * Read all doctors.
	 *
	 * @return the list
	 * @throws SQLException           the SQL exception
	 * @throws DoctorNotFound         the doctor not found
	 * @throws ClassNotFoundException the class not found exception
	 */
	public List<Doctor> readAllDoctors() throws SQLException, ClassNotFoundException {
		LOGGER.traceEntry();
		List<Doctor> alldoctors = null;
		ResultSet resultData;
		Connection databaseConnection = DBConnection.establishConnection();
		PreparedStatement doctorPreparedStatement = databaseConnection.prepareStatement(QueryConstants.DOCTOR_READ_ALL);
		resultData = doctorPreparedStatement.executeQuery();
		alldoctors = getDoctorDetails(resultData);
		LOGGER.traceExit(alldoctors.toString());
		return alldoctors;
	}

	/**
	 * Delete doctor.
	 *
	 * @param userId the user id
	 * @return true, if successful
	 * @throws SQLException           the SQL exception
	 * @throws ClassNotFoundException the class not found exception
	 */
	public boolean deleteDoctor(int userId) throws SQLException, ClassNotFoundException {
		LOGGER.traceEntry(Integer.toString(userId));
		boolean result = false;
		int rowsChangedForDoctor = 0;
		Connection databaseConnection = DBConnection.establishConnection();
		PreparedStatement doctorPreparedStatement = databaseConnection.prepareStatement(QueryConstants.DOCTOR_DELETE);
		doctorPreparedStatement.setInt(1, userId);
		rowsChangedForDoctor = doctorPreparedStatement.executeUpdate();
		if (rowsChangedForDoctor == 2) {
			result = true;
		}
		LOGGER.traceExit(Boolean.toString(result));
		return result;
	}

	/**
	 * Gets the doctor details.
	 *
	 * @param resultData the result data
	 * @return the doctor details
	 * @throws SQLException   the SQL exception
	 * @throws DoctorNotFound the doctor not found
	 */
	public List<Doctor> getDoctorDetails(ResultSet resultData) throws SQLException {
		LOGGER.traceEntry();
		List<Doctor> alldoctors = new ArrayList<Doctor>();
		while (resultData.next()) {
			Doctor doctor = new Doctor();
			doctor.setUserId(resultData.getInt(QueryConstants.USER_ID));
			doctor.setUsername(resultData.getString(QueryConstants.USER_NAME));
			doctor.setPassword(resultData.getString(QueryConstants.USER_PASSWORD));
			doctor.setEmail(resultData.getString(QueryConstants.USER_EMAIL));
			doctor.setPhonenumber(resultData.getString(QueryConstants.USER_PHONENUMBER));
			doctor.setFirstname(resultData.getString(QueryConstants.USER_FIRSTNAME));
			doctor.setLastname(resultData.getString(QueryConstants.USER_LASTNAME));
			doctor.setAge(resultData.getInt(QueryConstants.USER_AGE));
			doctor.setStreet(resultData.getString(QueryConstants.USER_STREET));
			doctor.setCity(resultData.getString(QueryConstants.USER_CITY));
			doctor.setState(resultData.getString(QueryConstants.USER_STATE));
			doctor.setRoleId(resultData.getInt(QueryConstants.USER_ROLE_ID));
			doctor.setDoctorexperience(resultData.getInt(QueryConstants.DOCTOR_EXPERIENCE));
			doctor.setDoctorspeciality(resultData.getString(QueryConstants.DOCTOR_SPECIALITY));
			doctor.setIsactive(resultData.getInt(QueryConstants.DOCTOR_ISACTIVE));
			doctor.setIsActive(resultData.getInt(QueryConstants.USER_ISACTIVE));
			doctor.setCreatedOn(resultData.getDate(QueryConstants.USER_CREATEDON));
			doctor.setUpdatedOn(resultData.getDate(QueryConstants.USER_UPDATEDON));
			doctor.setDoctorId(resultData.getInt(QueryConstants.DOCTOR_ID));
			alldoctors.add(doctor);
		}
		LOGGER.traceExit(alldoctors.toString());
		return alldoctors;
	}

	/**
	 * Sets the doctor details.
	 *
	 * @param preparedStatementForDoctor the prepared statement for doctor
	 * @param doctor                     the doctor
	 * @throws SQLException the SQL exception
	 */
	public void setDoctorDetails(PreparedStatement preparedStatementForDoctor, Doctor doctor) throws SQLException {
		LOGGER.traceEntry(doctor.toString());
		preparedStatementForDoctor.setInt(NumericConstants.ONE, doctor.getDoctorexperience());
		preparedStatementForDoctor.setString(NumericConstants.TWO, doctor.getDoctorspeciality());
		preparedStatementForDoctor.setInt(NumericConstants.THREE, doctor.getUserId());
		LOGGER.info("Doctor details are set successfully");
		LOGGER.traceExit(doctor.toString());
	}

	/**
	 * Sets the id for doctor.
	 *
	 * @param preparedStatementForDoctor the prepared statement for doctor
	 * @param doctor                     the doctor
	 * @return true, if successful
	 * @throws SQLException the SQL exception
	 */
	public boolean setIdForDoctor(PreparedStatement preparedStatementForDoctor, Doctor doctor) throws SQLException {
		LOGGER.traceEntry(doctor.toString());
		boolean result = false;
		ResultSet resultSetId = preparedStatementForDoctor.getGeneratedKeys();
		if ((resultSetId != null) && resultSetId.next()) {
			int userId = resultSetId.getInt(1);
			doctor.setDoctorId(userId);
			LOGGER.info("Doctor ID is set successfully");
			result = true;
		}
		LOGGER.traceExit(doctor.toString());
		return result;
	}

	/**
	 * Find all distinct doctor id.
	 *
	 * @return the list
	 * @throws ClassNotFoundException the class not found exception
	 * @throws SQLException           the SQL exception
	 */
	public List<Integer> findAllDistinctDoctorId() throws ClassNotFoundException, SQLException {
		LOGGER.traceEntry();
		ResultSet resultData;
		List<Integer> doctorsId = new ArrayList<Integer>();
		Connection databaseConnection = DBConnection.establishConnection();
		PreparedStatement doctorPreparedStatement = databaseConnection
				.prepareStatement(QueryConstants.SELECT_ALL_DOCTOR_ID);
		resultData = doctorPreparedStatement.executeQuery();
		while (resultData.next()) {
			doctorsId.add(resultData.getInt(QueryConstants.USER_ID));
		}
		LOGGER.traceExit(doctorsId.toString());
		return doctorsId;
	}
}
