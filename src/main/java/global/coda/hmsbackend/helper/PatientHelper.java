/*
 *
 */
package global.coda.hmsbackend.helper;

import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import global.coda.hmsbackend.dao.PatientDAO;
import global.coda.hmsbackend.exception.BusinessException;
import global.coda.hmsbackend.exception.EmptyValueException;
import global.coda.hmsbackend.exception.PatientNotFound;
import global.coda.hmsbackend.exception.SystemException;
import global.coda.hmsbackend.models.Patient;
import global.coda.hmsbackend.utils.InputValidator;

// TODO: Auto-generated Javadoc
/**
 * The Class PatientHelper.
 */
public class PatientHelper {

	/** The inputchecker. */
	private InputValidator inputchecker;

	/** The patientdao. */
	private PatientDAO patientdao;

	/** The logger. */
	private final Logger LOGGER = LogManager.getLogger(PatientHelper.class);

	/**
	 * Gets the patient.
	 *
	 * @param id the id
	 * @return the patient
	 * @throws SystemException   the system exception
	 * @throws BusinessException the business exception
	 */
	public Patient getPatient(String id) throws SystemException, BusinessException {
		LOGGER.traceEntry();
		Patient patient = null;
		try {
			inputchecker = new InputValidator();
			patientdao = new PatientDAO();
			boolean validQuery = inputchecker.validateInteger(id);
			if (!validQuery) {
				throw new NumberFormatException("Please enter a number");
			}
			boolean nullvalidator = inputchecker.validateNullValue(id);
			if (nullvalidator) {
				throw new EmptyValueException("Please enter a value");
			}
			int patientId = Integer.parseInt(id);
			patient = patientdao.readPatient(patientId);
			if (patient == null) {
				throw new PatientNotFound("Patient details are not found");
			}
		} catch (SQLException | ClassNotFoundException e) {
			LOGGER.info("Exception occured while reading a patient");
			throw new SystemException(e);
		} catch (PatientNotFound e) {
			LOGGER.info("Patient details are not found while reading a patient");
			throw new BusinessException(e);
		} catch (NumberFormatException | EmptyValueException e) {
			throw new BusinessException(e);
		} catch (Exception e) {
			throw new SystemException(e);
		}
		LOGGER.traceExit();
		return patient;
	}

	/**
	 * Gets the all patients.
	 *
	 * @return the all patients
	 * @throws BusinessException the business exception
	 * @throws SystemException   the system exception
	 */
	public List<Patient> getAllPatients() throws BusinessException, SystemException {
		LOGGER.traceEntry();
		List<Patient> allpatients = null;
		try {
			patientdao = new PatientDAO();
			allpatients = patientdao.readAllPatients();
			if (allpatients.size() < 1) {
				throw new PatientNotFound("Patient details are not found");
			}
		} catch (PatientNotFound e) {
			LOGGER.info("Patient details are not found");
			throw new BusinessException(e);
		} catch (ClassNotFoundException | SQLException e) {
			LOGGER.info("Exception while reading all patients");
			throw new SystemException(e);
		} catch (Exception e) {
			throw new SystemException(e);
		}
		LOGGER.traceExit();
		return allpatients;
	}

	/**
	 * Creates the patient.
	 *
	 * @param patient the patient
	 * @return the response body
	 * @throws SystemException the system exception
	 */
	public Patient createPatient(Patient patient) throws SystemException {
		LOGGER.traceEntry();
		Patient createdpatient = null;
		try {
			patientdao = new PatientDAO();
			createdpatient = patientdao.createPatient(patient);
		} catch (SQLException | ClassNotFoundException e) {
			LOGGER.info("Exception while creating a patient");
			throw new SystemException(e);
		} catch (Exception e) {
			throw new SystemException(e);
		}
		LOGGER.traceExit();
		return createdpatient;
	}

	/**
	 * Update patient.
	 *
	 * @param patient the patient
	 * @return the response body
	 * @throws SystemException   the system exception
	 * @throws BusinessException the business exception
	 */
	public Patient updatePatient(Patient patient) throws SystemException, BusinessException {
		LOGGER.traceEntry();
		Patient updatedpatient = null;
		try {
			patientdao = new PatientDAO();
			updatedpatient = patientdao.updatePatient(patient.getUserId(), patient);
		} catch (SQLException | ClassNotFoundException e) {
			LOGGER.info("Exception while updating a patient");
			throw new SystemException(e);
		} catch (Exception e) {
			throw new SystemException(e);
		}
		LOGGER.traceExit();
		return updatedpatient;
	}

	/**
	 * Delete patient.
	 *
	 * @param id the id
	 * @return the response body
	 * @throws SystemException   the system exception
	 * @throws BusinessException the business exception
	 */
	public boolean deletePatient(String id) throws SystemException, BusinessException {
		boolean isdeleted = false;
		try {
			Patient patient = null;
			inputchecker = new InputValidator();
			patientdao = new PatientDAO();
			boolean validQuery = inputchecker.validateInteger(id);
			if (!validQuery) {
				throw new NumberFormatException("Please enter a number");
			}
			boolean nullvalidator = inputchecker.validateNullValue(id);
			if (nullvalidator) {
				throw new EmptyValueException("Please enter a value");
			}
			int patientId = Integer.parseInt(id);
			patient = patientdao.readPatient(patientId);
			if (patient != null) {
				isdeleted = patientdao.deletePatient(patientId);
			} else {
				throw new PatientNotFound("Patient details are not found");
			}
		} catch (SQLException | ClassNotFoundException e) {
			LOGGER.info("Exception while Deleting a patient");
			throw new SystemException(e);
		} catch (PatientNotFound | EmptyValueException e) {
			LOGGER.info("Patient not found while Deleting a patient");
			throw new BusinessException(e);
		} catch (Exception e) {
			throw new SystemException(e);
		}
		return isdeleted;
	}

}
