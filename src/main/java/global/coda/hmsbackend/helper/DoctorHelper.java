/*
 *
 */
package global.coda.hmsbackend.helper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import global.coda.hmsbackend.dao.DoctorDAO;
import global.coda.hmsbackend.dao.PatientDAO;
import global.coda.hmsbackend.exception.BusinessException;
import global.coda.hmsbackend.exception.DoctorNotFound;
import global.coda.hmsbackend.exception.EmptyValueException;
import global.coda.hmsbackend.exception.SystemException;
import global.coda.hmsbackend.models.Doctor;
import global.coda.hmsbackend.models.DoctorPatientMapper;
import global.coda.hmsbackend.utils.InputValidator;

// TODO: Auto-generated Javadoc
/**
 * The Class DoctorHelper.
 */
public class DoctorHelper {

	/** The logger. */
	private final Logger LOGGER = LogManager.getLogger(DoctorHelper.class);

	/** The inputchecker. */
	private InputValidator inputchecker;

	/** The doctordao. */
	private DoctorDAO doctordao;

	/**
	 * Creates the doctor.
	 *
	 * @param doctor the doctor
	 * @return the response body
	 * @throws SystemException the system exception
	 */
	public Doctor createDoctor(Doctor doctor) throws SystemException {
		LOGGER.traceEntry(doctor.toString());
		Doctor createdDoctor = null;
		try {
			doctordao = new DoctorDAO();
			createdDoctor = doctordao.createDoctor(doctor);
		} catch (SQLException | ClassNotFoundException e) {
			LOGGER.info("Exception while creating a patient");
			throw new SystemException(e);
		} catch (Exception e) {
			throw new SystemException(e);
		}
		LOGGER.traceExit(createdDoctor);
		return createdDoctor;
	}

	/**
	 * Gets the doctor.
	 *
	 * @param id the id
	 * @return the doctor
	 * @throws SystemException   the system exception
	 * @throws BusinessException the business exception
	 */
	public Doctor getDoctor(String id) throws SystemException, BusinessException {
		LOGGER.traceEntry(id);
		Doctor doctor = null;
		try {
			inputchecker = new InputValidator();
			doctordao = new DoctorDAO();
			boolean validQuery = inputchecker.validateInteger(id);
			if (!validQuery) {
				throw new NumberFormatException("Please enter a number");
			}
			boolean nullvalidator = inputchecker.validateNullValue(id);
			if (nullvalidator) {
				throw new EmptyValueException("Please enter a value");
			}
			int doctorId = Integer.parseInt(id);
			doctor = doctordao.readDoctor(doctorId);
			if (doctor == null) {
				throw new DoctorNotFound("Doctor details are not found");
			}
		} catch (SQLException | ClassNotFoundException e) {
			LOGGER.info("Exception occured while reading a doctor");
			throw new SystemException(e);
		} catch (DoctorNotFound e) {
			LOGGER.info("Doctor details are not found while reading a doctor");
			throw new BusinessException(e);
		} catch (NumberFormatException | EmptyValueException e) {
			throw new BusinessException(e);
		} catch (Exception e) {
			throw new SystemException(e);
		}
		LOGGER.traceExit(doctor.toString());
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
		try {
			doctordao = new DoctorDAO();
			alldoctors = doctordao.readAllDoctors();
			if (alldoctors.size() < 1) {
				throw new DoctorNotFound("Doctor details are not found");
			}
		} catch (DoctorNotFound e) {
			LOGGER.info("Doctor details are not found");
			throw new BusinessException(e);
		} catch (ClassNotFoundException | SQLException e) {
			LOGGER.info("Exception while reading all doctors");
			throw new SystemException(e);
		} catch (Exception e) {
			throw new SystemException(e);
		}
		LOGGER.traceExit(alldoctors.toString());
		return alldoctors;
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
		LOGGER.traceEntry(doctor.toString());
		Doctor updatedDoctor = null;
		try {
			doctordao = new DoctorDAO();
			updatedDoctor = doctordao.updateDoctor(doctor.getUserId(), doctor);
		} catch (SQLException | ClassNotFoundException e) {
			LOGGER.info("Exception while updating a Doctor");
			throw new SystemException(e);
		} catch (DoctorNotFound e) {
			LOGGER.info("Doctor not found while updating a Doctor");
			throw new BusinessException(e);
		} catch (Exception e) {
			throw new SystemException(e);
		}
		LOGGER.traceExit(updatedDoctor.toString());
		return updatedDoctor;
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
		LOGGER.traceEntry(id);
		boolean isdeleted = false;
		try {
			Doctor doctor = null;
			inputchecker = new InputValidator();
			doctordao = new DoctorDAO();
			boolean validQuery = inputchecker.validateInteger(id);
			if (!validQuery) {
				throw new NumberFormatException("Please enter a number");
			}
			boolean nullvalidator = inputchecker.validateNullValue(id);
			if (nullvalidator) {
				throw new EmptyValueException("Please enter a value");
			}
			int doctorId = Integer.parseInt(id);
			doctor = doctordao.readDoctor(doctorId);
			if (doctor != null) {
				isdeleted = doctordao.deleteDoctor(doctorId);
			} else {
				throw new DoctorNotFound("Doctor details are not found");
			}
		} catch (SQLException | ClassNotFoundException e) {
			LOGGER.info("Exception while Deleting a patient");
			throw new SystemException(e);
		} catch (DoctorNotFound | EmptyValueException e) {
			LOGGER.info("Doctor not found while Deleting a doctor");
			throw new BusinessException(e);
		} catch (Exception e) {
			throw new SystemException(e);
		}
		LOGGER.traceExit(Boolean.toString(isdeleted));
		return isdeleted;
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
		LOGGER.traceEntry(id);
		List<DoctorPatientMapper> doctorpatientMappers = new ArrayList<DoctorPatientMapper>();
		try {
			inputchecker = new InputValidator();
			doctordao = new DoctorDAO();
			boolean validQuery = inputchecker.validateInteger(id);
			if (!validQuery) {
				throw new NumberFormatException("Please enter a number");
			}
			boolean nullvalidator = inputchecker.validateNullValue(id);
			if (nullvalidator) {
				throw new EmptyValueException("Please enter a value");
			}
			Doctor doctor = null;
			int doctorId = Integer.parseInt(id);
			doctordao = new DoctorDAO();
			doctor = doctordao.readDoctor(doctorId);
			if (doctor == null) {
				throw new DoctorNotFound("Doctor details are not available");
			}
			PatientDAO patientDao = new PatientDAO();
			doctorpatientMappers = patientDao.findPatientsByDoctorId(doctorId);
		} catch (ClassNotFoundException | SQLException e) {
			throw new SystemException(e);
		} catch (NumberFormatException | EmptyValueException | DoctorNotFound e) {
			throw new BusinessException(e);
		} catch (Exception e) {
			throw new SystemException(e);
		}
		LOGGER.traceExit(doctorpatientMappers);
		return doctorpatientMappers;
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
		Map<Integer, List<DoctorPatientMapper>> patientsOfAllDoctors = new HashMap<Integer, List<DoctorPatientMapper>>();
		try {
			doctordao = new DoctorDAO();
			List<Integer> doctorsId = new ArrayList<Integer>();
			doctorsId = doctordao.findAllDistinctDoctorId();
			PatientDAO patientDao = new PatientDAO();
			List<DoctorPatientMapper> doctorPatientMappers = new ArrayList<DoctorPatientMapper>();
			for (Integer doctorId : doctorsId) {
				doctorPatientMappers = patientDao.findPatientsByDoctorId(doctorId);
				patientsOfAllDoctors.put(doctorId, doctorPatientMappers);
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new SystemException(e);
		} catch (Exception e) {
			throw new SystemException(e);
		}
		LOGGER.traceExit(patientsOfAllDoctors.toString());
		return patientsOfAllDoctors;
	}
}
