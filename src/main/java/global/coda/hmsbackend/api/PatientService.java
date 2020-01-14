/*
 *
 */
package global.coda.hmsbackend.api;

import java.util.List;

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
import global.coda.hmsbackend.delegate.PatientDelegate;
import global.coda.hmsbackend.exception.BusinessException;
import global.coda.hmsbackend.exception.SystemException;
import global.coda.hmsbackend.models.Patient;
import global.coda.hmsbackend.models.ResponseBody;

// TODO: Auto-generated Javadoc
/**
 * The Class PatientService.
 */
@Path("/patient")
public class PatientService {

	/** The logger. */
	private final Logger LOGGER = LogManager.getLogger(PatientService.class);

	/** The patientdelegate. */
	private PatientDelegate patientdelegate;

	/** The response. */
	private ResponseBody response;

	private Patient patient;

	/**
	 * Gets the patient.
	 *
	 * @param patientId the PATHPARAM patient id
	 * @return the patient
	 * @throws SystemException   the system exception
	 * @throws BusinessException the business exception
	 */
	@GET
	@Path("/getPatient/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseBody getPatient(@PathParam("id") String patientId) throws SystemException, BusinessException {
		patient = null;
		LOGGER.traceEntry(patientId);
		response = new ResponseBody();
		patientdelegate = new PatientDelegate();
		patient = patientdelegate.getPatient(patientId);
		response.setData(patient);
		response.setStatusCode(HttpStatusConstants.OK);
		LOGGER.traceExit(response);
		return response;
	}

	/**
	 * Gets the all patients.
	 *
	 * @return the all patients
	 * @throws BusinessException the business exception
	 * @throws SystemException   the system exception
	 */
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseBody getAllPatients() throws BusinessException, SystemException {
		LOGGER.traceEntry();
		response = new ResponseBody();
		List<Patient> allPatients = null;
		patientdelegate = new PatientDelegate();
		allPatients = patientdelegate.getAllPatients();
		response.setStatusCode(HttpStatusConstants.OK);
		response.setData(allPatients);
		LOGGER.traceExit(allPatients);
		return response;
	}

	/**
	 * Creates the patient.
	 *
	 * @param patient the patient
	 * @return the response body
	 * @throws SystemException the system exception
	 */
	@POST
	@Path("/create")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseBody createPatient(Patient patient) throws SystemException {
		LOGGER.traceEntry();
		patient = null;
		response = new ResponseBody();
		patientdelegate = new PatientDelegate();
		patient = patientdelegate.createPatient(patient);
		response.setStatusCode(HttpStatusConstants.OK);
		response.setData(patient);
		LOGGER.traceExit();
		return response;
	}

	/**
	 * Update patient.
	 *
	 * @param patient the patient
	 * @return the response body
	 * @throws SystemException   the system exception
	 * @throws BusinessException the business exception
	 */
	@PUT
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseBody updatePatient(Patient patient) throws SystemException, BusinessException {
		LOGGER.traceEntry(patient.toString());
		patient = null;
		response = new ResponseBody();
		patientdelegate = new PatientDelegate();
		patient = patientdelegate.updatePatient(patient);
		response.setStatusCode(HttpStatusConstants.OK);
		response.setData(patient);
		LOGGER.traceExit(response);
		return response;
	}

	/**
	 * Delete patient.
	 *
	 * @param patientId the PATHPARAM patient id
	 * @return the response body
	 * @throws SystemException   the system exception
	 * @throws BusinessException the business exception
	 */
	@DELETE
	@Path("/delete/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseBody deletePatient(@PathParam("id") String patientId) throws SystemException, BusinessException {
		boolean result = false;
		LOGGER.traceEntry(patientId);
		response = new ResponseBody();
		patientdelegate = new PatientDelegate();
		result = patientdelegate.deletePatient(patientId);
		if (result) {
			response.setStatusCode(HttpStatusConstants.NO_CONTENT);
			response.setData("Patient is deleted successfully");
		} else {
			response.setStatusCode(HttpStatusConstants.INTERNAL_SERVER_ERROR);
			response.setData("Patient is not deleted successfully");
		}
		LOGGER.traceExit(response);
		return response;
	}
}
