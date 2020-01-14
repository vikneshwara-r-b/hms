/*
 *
 */
package global.coda.hmsbackend.api;

import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import global.coda.hmsbackend.constants.HttpStatusConstants;
import global.coda.hmsbackend.delegate.DoctorDelegate;
import global.coda.hmsbackend.exception.BusinessException;
import global.coda.hmsbackend.exception.SystemException;
import global.coda.hmsbackend.models.Doctor;
import global.coda.hmsbackend.models.DoctorPatientMapper;
import global.coda.hmsbackend.models.ResponseBody;

// TODO: Auto-generated Javadoc
/**
 * The Class DoctorService.
 */
@Path("/doctor")
public class DoctorService {

	/** The logger. */
	private final Logger LOGGER = LogManager.getLogger(DoctorService.class);

	/** The doctor delegate. */
	private DoctorDelegate doctorDelegate;

	private Doctor doctor;

	private ResponseBody response;

	/**
	 * Gets the doctor.
	 *
	 * @param patientId the PATHPARAM patient id
	 * @return the doctor
	 * @throws SystemException   the system exception
	 * @throws BusinessException the business exception
	 */
	@GET
	@Path("/getDoctor/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseBody getDoctor(@PathParam("id") String patientId) throws SystemException, BusinessException {
		LOGGER.traceEntry(patientId);
		doctor = null;
		response = new ResponseBody();
		doctorDelegate = new DoctorDelegate();
		doctor = doctorDelegate.getDoctor(patientId);
		response.setData(doctor);
		response.setStatusCode(HttpStatusConstants.OK);
		LOGGER.traceExit(doctor.toString());
		return response;
	}

	/**
	 * Gets the all doctors.
	 *
	 * @return the all doctors
	 * @throws BusinessException the business exception
	 * @throws SystemException   the system exception
	 */
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseBody getAllDoctors() throws BusinessException, SystemException {
		LOGGER.traceEntry();
		List<Doctor> alldoctors = null;
		response = new ResponseBody();
		doctorDelegate = new DoctorDelegate();
		alldoctors = doctorDelegate.getAllDoctors();
		response.setData(alldoctors);
		response.setStatusCode(HttpStatusConstants.OK);
		LOGGER.traceExit(alldoctors);
		return response;
	}

	/**
	 * Creates the doctor.
	 *
	 * @param doctor the doctor
	 * @return the response body
	 * @throws SystemException the system exception
	 */
	@POST
	@Path("/create")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseBody createDoctor(Doctor doctor) throws SystemException {
		LOGGER.traceEntry(doctor.toString());
		doctor = null;
		response = new ResponseBody();
		doctorDelegate = new DoctorDelegate();
		doctor = doctorDelegate.createDoctor(doctor);
		response.setData(doctor);
		response.setStatusCode(HttpStatusConstants.OK);
		LOGGER.traceExit(doctor.toString());
		return response;
	}

	/**
	 * Update doctor.
	 *
	 * @param doctor the doctor
	 * @return the response body
	 * @throws SystemException   the system exception
	 * @throws BusinessException the business exception
	 */
	@PUT
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseBody updateDoctor(Doctor doctor) throws SystemException, BusinessException {
		LOGGER.traceExit(doctor.toString());
		doctor = null;
		response = new ResponseBody();
		doctorDelegate = new DoctorDelegate();
		doctor = doctorDelegate.updateDoctor(doctor);
		response.setData(doctor);
		response.setStatusCode(HttpStatusConstants.OK);
		LOGGER.traceExit(doctor.toString());
		return response;
	}

	/**
	 * Delete doctor.
	 *
	 * @param patientId the patient id
	 * @return the response body
	 * @throws SystemException   the system exception
	 * @throws BusinessException the business exception
	 */
	@DELETE
	@Path("/delete/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseBody deleteDoctor(@PathParam("id") String patientId) throws SystemException, BusinessException {
		LOGGER.traceEntry(patientId);
		boolean result = false;
		response = new ResponseBody();
		doctorDelegate = new DoctorDelegate();
		result = doctorDelegate.deletePatient(patientId);
		if (result) {
			response.setData("Doctor is deleted successfully");
			response.setStatusCode(HttpStatusConstants.NO_CONTENT);
		} else {
			response.setStatusCode(HttpStatusConstants.INTERNAL_SERVER_ERROR);
			response.setData("Doctor is not deleted successfully");
		}
		LOGGER.traceExit(Boolean.toString(result));
		return response;
	}

	/**
	 * Find patients of doctor.
	 *
	 * @param doctorId the PATHPARAM doctor id
	 * @return the response body
	 * @throws SystemException   the system exception
	 * @throws BusinessException the business exception
	 */
	@GET
	@Path("/{id}/patients")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseBody findPatientsOfDoctor(@PathParam("id") String doctorId)
			throws SystemException, BusinessException {
		List<DoctorPatientMapper> doctorPatientMappers = null;
		LOGGER.traceEntry(doctorId);
		response = new ResponseBody();
		doctorDelegate = new DoctorDelegate();
		doctorPatientMappers = doctorDelegate.findPatientsOfDoctor(doctorId);
		if (doctorPatientMappers == null) {
			response.setData("No patients are available for the doctor");
			response.setStatusCode(HttpStatusConstants.OK);
		} else {
			response.setData(doctorPatientMappers);
			response.setStatusCode(HttpStatusConstants.OK);
		}
		LOGGER.traceExit(response);
		return response;
	}

	/**
	 * Find all patients of doctor.
	 *
	 * @return the response body
	 * @throws SystemException   the system exception
	 * @throws BusinessException the business exception
	 */
	@GET
	@Path("/patients")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseBody findPatientsOfAllDoctors() throws SystemException, BusinessException {
		LOGGER.traceEntry();
		Map<Integer, List<DoctorPatientMapper>> allPatientsForAllDoctors = null;
		response = new ResponseBody();
		doctorDelegate = new DoctorDelegate();
		allPatientsForAllDoctors = doctorDelegate.findPatientsOfAllDoctors();
		response.setData(allPatientsForAllDoctors);
		response.setStatusCode(HttpStatusConstants.OK);
		LOGGER.traceExit();
		return response;
	}

}
