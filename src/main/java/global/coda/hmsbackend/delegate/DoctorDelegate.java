/*
 *
 */
package global.coda.hmsbackend.delegate;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import global.coda.hmsbackend.exception.BusinessException;
import global.coda.hmsbackend.exception.SystemException;
import global.coda.hmsbackend.helper.DoctorHelper;
import global.coda.hmsbackend.models.Doctor;
import global.coda.hmsbackend.models.DoctorPatientMapper;

// TODO: Auto-generated Javadoc
/**
 * The Class DoctorDelegate.
 */
public class DoctorDelegate {

	/** The logger. */
	private final Logger LOGGER = LogManager.getLogger(DoctorDelegate.class);

	/** The doctor helper. */
	private DoctorHelper doctorHelper;

	private Doctor doctor;

	/**
	 * Gets the doctor.
	 *
	 * @param id the id
	 * @return the doctor
	 * @throws SystemException   the system exception
	 * @throws BusinessException the business exception
	 */
	public Doctor getDoctor(String id) throws SystemException, BusinessException {
		LOGGER.traceEntry();
		doctorHelper = new DoctorHelper();
		doctor = doctorHelper.getDoctor(id);
		LOGGER.traceExit();
		return doctor;
	}

	/**
	 * Gets the all doctors.
	 *
	 * @return the all doctors
	 * @throws BusinessException the business exception
	 * @throws SystemException   the system exception
	 */
	public List<Doctor> getAllDoctors() throws BusinessException, SystemException {
		LOGGER.traceEntry();
		List<Doctor> alldoctors = null;
		doctorHelper = new DoctorHelper();
		alldoctors = doctorHelper.getAllDoctors();
		LOGGER.traceExit();
		return alldoctors;
	}

	/**
	 * Creates the doctor.
	 *
	 * @param doctor the doctor
	 * @return the response body
	 * @throws SystemException the system exception
	 */
	public Doctor createDoctor(Doctor doctor) throws SystemException {
		LOGGER.traceEntry();
		doctor = null;
		doctorHelper = new DoctorHelper();
		doctor = doctorHelper.createDoctor(doctor);
		LOGGER.traceExit();
		return doctor;
	}

	/**
	 * Update doctor.
	 *
	 * @param doctor the doctor
	 * @return the response body
	 * @throws SystemException   the system exception
	 * @throws BusinessException the business exception
	 */
	public Doctor updateDoctor(Doctor doctor) throws SystemException, BusinessException {
		LOGGER.traceEntry();
		doctor = null;
		doctorHelper = new DoctorHelper();
		doctor = doctorHelper.updateDoctor(doctor);
		LOGGER.traceExit();
		return doctor;
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
		boolean result = false;
		doctorHelper = new DoctorHelper();
		result = doctorHelper.deletePatient(id);
		LOGGER.traceExit();
		return result;
	}

	/**
	 * Find patients of doctor.
	 *
	 * @param id the id
	 * @return the response body
	 * @throws SystemException   the system exception
	 * @throws BusinessException the business exception
	 */
	public List<DoctorPatientMapper> findPatientsOfDoctor(String id) throws SystemException, BusinessException {
		LOGGER.traceEntry();
		List<DoctorPatientMapper> doctorForPatients = null;
		doctorHelper = new DoctorHelper();
		doctorForPatients = doctorHelper.findPatientsOfDoctor(id);
		LOGGER.traceExit();
		return doctorForPatients;
	}

	/**
	 * Find all patients of doctor.
	 *
	 * @return the response body
	 * @throws SystemException   the system exception
	 * @throws BusinessException the business exception
	 */
	public Map<Integer, List<DoctorPatientMapper>> findPatientsOfAllDoctors() throws SystemException, BusinessException {
		LOGGER.traceEntry();
		Map<Integer, List<DoctorPatientMapper>> allDoctorsForAllPatients = null;
		doctorHelper = new DoctorHelper();
		allDoctorsForAllPatients = doctorHelper.findPatientsOfAllDoctors();
		LOGGER.traceExit();
		return allDoctorsForAllPatients;
	}

}
