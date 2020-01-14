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
import global.coda.hmsbackend.exception.PatientNotFound;
import global.coda.hmsbackend.models.DoctorPatientMapper;
import global.coda.hmsbackend.models.Patient;
import global.coda.hmsbackend.utils.DaoUtil;

// TODO: Auto-generated Javadoc
/**
 * The Class PatientDAO.
 */
public class PatientDAO {

	/** The logger. */
	private final Logger LOGGER = LogManager.getLogger(PatientDAO.class);

	/** The userdao. */
	private DaoUtil userDao;

	/**
	 * Read patient.
	 *
	 * @param userId the user id
	 * @return the patient
	 * @throws SQLException           the SQL exception
	 * @throws PatientNotFound        the patient not found
	 * @throws ClassNotFoundException the class not found exception
	 */
	public Patient readPatient(int userId) throws SQLException, ClassNotFoundException {
		LOGGER.traceEntry(Integer.toString(userId));
		List<Patient> allpatients = null;
		Patient patient = null;
		ResultSet resultData;
		Connection databaseConnection = DBConnection.establishConnection();
		PreparedStatement preparedStatementforPatient = databaseConnection
				.prepareStatement(QueryConstants.PATIENT_READ);
		preparedStatementforPatient.setInt(NumericConstants.ONE, userId);
		resultData = preparedStatementforPatient.executeQuery();
		allpatients = getPatientDetails(resultData);
		if (allpatients.size() < 1) {
			return null;
		}
		patient = allpatients.get(0);
		LOGGER.traceExit(allpatients);
		return patient;
	}

	/**
	 * Read all patients.
	 *
	 * @return the list
	 * @throws ClassNotFoundException the class not found exception
	 * @throws SQLException           the SQL exception
	 * @throws PatientNotFound        the patient not found
	 */
	public List<Patient> readAllPatients() throws ClassNotFoundException, SQLException {
		LOGGER.traceEntry();
		List<Patient> allpatients = null;
		ResultSet resultData;
		Connection databaseConnection = DBConnection.establishConnection();
		PreparedStatement patientPreparedStatement = databaseConnection
				.prepareStatement(QueryConstants.PATIENT_READ_ALL);
		resultData = patientPreparedStatement.executeQuery();
		allpatients = getPatientDetails(resultData);
		LOGGER.traceExit(allpatients);
		return allpatients;
	}

	/**
	 * Creates the patient.
	 *
	 * @param patient the patient
	 * @return the patient
	 * @throws SQLException           the SQL exception
	 * @throws ClassNotFoundException the class not found exception
	 */
	public Patient createPatient(Patient patient) throws SQLException, ClassNotFoundException {
		LOGGER.traceEntry(patient.toString());
		userDao = new DaoUtil();
		int rowsChangedForPatient = 0;
		int rowsChangedForUser = 0;
		Connection databaseConnection = DBConnection.establishConnection();
		try {
			databaseConnection.setAutoCommit(false);
			PreparedStatement preparedStatementForUser = databaseConnection.prepareStatement(QueryConstants.USER_INSERT,
					Statement.RETURN_GENERATED_KEYS);
			userDao.setUserDetails(preparedStatementForUser, patient);
			rowsChangedForUser = preparedStatementForUser.executeUpdate();
			if (rowsChangedForUser == 1) {
				if (userDao.setIdForUser(preparedStatementForUser, patient)) {
					LOGGER.info("User Details are set successfully");
					PreparedStatement preparedStatementforPatient = databaseConnection
							.prepareStatement(QueryConstants.PATIENT_INSERT, Statement.RETURN_GENERATED_KEYS);
					setPatientDetails(preparedStatementforPatient, patient);
					rowsChangedForPatient = preparedStatementforPatient.executeUpdate();
					if (rowsChangedForPatient == 1) {
						if (setIdForPatient(preparedStatementforPatient, patient)) {
							databaseConnection.commit();
						} else {
							LOGGER.info("Error occured at setting patient details");
						}
					}
				}
			} else {
				LOGGER.info("Error occured at setting user details");
			}
		} catch (SQLException e) {
			databaseConnection.rollback();
			throw (e);
		}
		LOGGER.traceExit(patient.toString());
		return patient;
	}

	/**
	 * Update patient.
	 *
	 * @param userId         the user id
	 * @param updatedpatient the updatedpatient
	 * @return the patient
	 * @throws SQLException           the SQL exception
	 * @throws PatientNotFound        the patient not found
	 * @throws ClassNotFoundException the class not found exception
	 */
	public Patient updatePatient(int userId, Patient updatedpatient) throws SQLException, ClassNotFoundException {
		LOGGER.traceEntry(Integer.toString(userId));
		LOGGER.traceEntry(updatedpatient.toString());
		userDao = new DaoUtil();
		int rowsChangedForPatient = 0, rowsChangedForUser = 0;
		readPatient(userId);
		Connection databaseConnection = DBConnection.establishConnection();
		try {
			databaseConnection.setAutoCommit(false);
			PreparedStatement userPreparedStatement = databaseConnection.prepareStatement(QueryConstants.USER_UPDATE);
			LOGGER.info("Before Prepared Statement");
			userDao.setUserDetails(userPreparedStatement, updatedpatient);
			LOGGER.info("After Prepared Statement");
			userPreparedStatement.setInt(NumericConstants.ELEVEN, userId);
			rowsChangedForUser = userPreparedStatement.executeUpdate();
			if (rowsChangedForUser > 0) {
				LOGGER.info("User details have been updated successfully");
				PreparedStatement patientPreparedStatement = databaseConnection
						.prepareStatement(QueryConstants.PATIENT_UPDATE);
				setPatientDetails(patientPreparedStatement, updatedpatient);
				patientPreparedStatement.setInt(NumericConstants.FOUR, userId);
				rowsChangedForPatient = patientPreparedStatement.executeUpdate();
				if (rowsChangedForPatient > 0) {
					LOGGER.info("Patient details have been updated successfully");
					databaseConnection.commit();
				}
			}
		} catch (SQLException e) {
			databaseConnection.rollback();
			LOGGER.info("Error occured at updating patient");
			throw (e);
		}
		LOGGER.traceExit(updatedpatient.toString());
		return updatedpatient;
	}

	/**
	 * Delete patient.
	 *
	 * @param userId the user id
	 * @return true, if successful
	 * @throws SQLException           the SQL exception
	 * @throws ClassNotFoundException the class not found exception
	 */
	public boolean deletePatient(int userId) throws SQLException, ClassNotFoundException {
		LOGGER.traceEntry(Integer.toString(userId));
		boolean result = false;
		int rowsChangedForPatient = 0;
		Connection databaseConnection = DBConnection.establishConnection();
		PreparedStatement patientPreparedStatement = databaseConnection.prepareStatement(QueryConstants.PATIENT_DELETE);
		patientPreparedStatement.setInt(NumericConstants.ONE, userId);
		rowsChangedForPatient = patientPreparedStatement.executeUpdate();
		if (rowsChangedForPatient == 2) {
			result = true;
		}
		LOGGER.traceExit(result);
		return result;
	}

	/**
	 * Find patients by doctor id.
	 *
	 * @param userId the user id
	 * @return the list
	 * @throws SQLException           the SQL exception
	 * @throws ClassNotFoundException the class not found exception
	 * @throws PatientNotFound        the patient not found
	 */
	public List<DoctorPatientMapper> findPatientsByDoctorId(int userId) throws SQLException, ClassNotFoundException {
		LOGGER.traceEntry(Integer.toString(userId));
		ResultSet resultData;
		DoctorPatientMapper doctorPatientMapper = null;
		List<DoctorPatientMapper> doctorPatientMappers = new ArrayList<DoctorPatientMapper>();
		Connection databaseConnection = DBConnection.establishConnection();
		PreparedStatement combinedPreparedStatement = databaseConnection
				.prepareStatement(QueryConstants.FIND_PATIENTS_BY_DOCTOR_ID);
		combinedPreparedStatement.setInt(NumericConstants.ONE, userId);
		resultData = combinedPreparedStatement.executeQuery();
		if (resultData.next() == false) {
			doctorPatientMappers = null;
		} else {
			do {
				doctorPatientMapper = new DoctorPatientMapper();
				doctorPatientMapper.setPatient(getPatientDetail(resultData));
				doctorPatientMapper.setDisease(resultData.getString(QueryConstants.PATIENT_DISEASE));
				doctorPatientMapper.setDoctorId(userId);
				doctorPatientMappers.add(doctorPatientMapper);
			} while (resultData.next());
		}
		LOGGER.traceExit(doctorPatientMapper);
		return doctorPatientMappers;
	}

	/**
	 * Gets the patient detail.
	 *
	 * @param resultData the result data
	 * @return the patient detail
	 * @throws SQLException the SQL exception
	 */
	public Patient getPatientDetail(ResultSet resultData) throws SQLException {
		LOGGER.traceEntry();
		Patient patient = new Patient();
		patient.setUserId(resultData.getInt(QueryConstants.USER_ID));
		patient.setUsername(resultData.getString(QueryConstants.USER_NAME));
		patient.setPassword(resultData.getString(QueryConstants.USER_PASSWORD));
		patient.setEmail(resultData.getString(QueryConstants.USER_EMAIL));
		patient.setPhonenumber(resultData.getString(QueryConstants.USER_PHONENUMBER));
		patient.setFirstname(resultData.getString(QueryConstants.USER_FIRSTNAME));
		patient.setLastname(resultData.getString(QueryConstants.USER_LASTNAME));
		patient.setAge(resultData.getInt(QueryConstants.USER_AGE));
		patient.setStreet(resultData.getString(QueryConstants.USER_STREET));
		patient.setCity(resultData.getString(QueryConstants.USER_CITY));
		patient.setState(resultData.getString(QueryConstants.USER_STATE));
		patient.setRoleId(resultData.getInt(QueryConstants.USER_ROLE_ID));
		patient.setPatientHeight(resultData.getInt(QueryConstants.PATIENT_HEIGHT));
		patient.setBloodGroup(resultData.getString(QueryConstants.PATIENT_BLOODGROUP));
		patient.setPatientWeight(resultData.getInt(QueryConstants.PATIENT_WEIGHT));
		patient.setIsActive(resultData.getInt(QueryConstants.USER_ISACTIVE));
		patient.setIsactive(resultData.getInt(QueryConstants.PATIENT_ISACTIVE));
		patient.setCreatedOn(resultData.getDate(QueryConstants.USER_CREATEDON));
		patient.setUpdatedOn(resultData.getDate(QueryConstants.USER_UPDATEDON));
		patient.setPatientId(resultData.getInt(QueryConstants.PATIENT_ID));
		LOGGER.traceExit();
		return patient;
	}

	/**
	 * Gets the patient details.
	 *
	 * @param resultData the result data
	 * @return the patient details
	 * @throws SQLException    the SQL exception
	 * @throws PatientNotFound the patient not found
	 */
	public List<Patient> getPatientDetails(ResultSet resultData) throws SQLException {
		LOGGER.traceEntry();
		List<Patient> allpatients = new ArrayList<Patient>();
		while (resultData.next()) {
			allpatients.add(getPatientDetail(resultData));
		}
		LOGGER.traceExit();
		return allpatients;
	}

	/**
	 * Sets the patient details.
	 *
	 * @param preparedStatement the prepared statement
	 * @param patient           the patient
	 * @throws SQLException the SQL exception
	 */
	public void setPatientDetails(PreparedStatement preparedStatement, Patient patient) throws SQLException {
		LOGGER.traceEntry(patient.toString());
		preparedStatement.setInt(NumericConstants.ONE, patient.getPatientHeight());
		preparedStatement.setString(NumericConstants.TWO, patient.getBloodGroup());
		preparedStatement.setInt(NumericConstants.THREE, patient.getPatientWeight());
		preparedStatement.setInt(NumericConstants.FOUR, patient.getUserId());
		LOGGER.info("Patient details are set successfully");
		LOGGER.traceExit(patient);
	}

	/**
	 * Sets the id for patient.
	 *
	 * @param preparedStatementforPatient the prepared statementfor patient
	 * @param patient                     the patient
	 * @return true, if successful
	 * @throws SQLException the SQL exception
	 */
	public boolean setIdForPatient(PreparedStatement preparedStatementforPatient, Patient patient) throws SQLException {
		LOGGER.traceEntry(patient.toString());
		boolean result = false;
		ResultSet resultSetId = preparedStatementforPatient.getGeneratedKeys();
		if ((resultSetId != null) && resultSetId.next()) {
			int userId = resultSetId.getInt(NumericConstants.ONE);
			patient.setPatientId(userId);
			LOGGER.info("Patient id set successfully");
			result = true;
		}
		LOGGER.traceExit(patient);
		return result;
	}

}
