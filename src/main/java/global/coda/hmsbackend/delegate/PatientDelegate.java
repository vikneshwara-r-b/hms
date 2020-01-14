/*
 *
 */
package global.coda.hmsbackend.delegate;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import global.coda.hmsbackend.exception.BusinessException;
import global.coda.hmsbackend.exception.SystemException;
import global.coda.hmsbackend.helper.PatientHelper;
import global.coda.hmsbackend.models.Patient;

// TODO: Auto-generated Javadoc
/**
 * The Class PatientDelegate.
 */
public class PatientDelegate {

	/** The patienthelper. */
	private PatientHelper patienthelper;

	private Patient patient;

	/** The logger. */
	private final Logger LOGGER = LogManager.getLogger(PatientDelegate.class);

	/**
	 * Gets the patient.
	 *
	 * @param id the id
	 * @return the patient
	 * @throws SystemException   the system exception
	 * @throws BusinessException the business exception
	 */
	public Patient getPatient(String id) throws SystemException, BusinessException {
		patient = null;
		LOGGER.traceEntry();
		patienthelper = new PatientHelper();
		patient = patienthelper.getPatient(id);
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
		List<Patient> allPatients = null;
		patienthelper = new PatientHelper();
		allPatients = patienthelper.getAllPatients();
		LOGGER.traceExit();
		return allPatients;
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
		patienthelper = new PatientHelper();
		patient = patienthelper.createPatient(patient);
		LOGGER.traceExit();
		return patient;
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
		patient = null;
		patienthelper = new PatientHelper();
		patient = patienthelper.updatePatient(patient);
		LOGGER.traceExit();
		return patient;
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
		LOGGER.traceEntry();
		boolean isdeleted = false;
		patienthelper = new PatientHelper();
		isdeleted = patienthelper.deletePatient(id);
		LOGGER.traceExit();
		return isdeleted;
	}

}
