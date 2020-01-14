/*
 *
 */
package global.coda.hmsbackend.models;

// TODO: Auto-generated Javadoc
/**
 * The Class DoctorPatientMapper.
 */
public class DoctorPatientMapper {

	/** The disease. */
	private String disease;

	/** The patient. */
	private Patient patient;

	/** The doctor id. */
	private int doctorId;

	/**
	 * Gets the disease.
	 *
	 * @return the disease
	 */
	public String getDisease() {
		return disease;
	}

	/**
	 * Sets the disease.
	 *
	 * @param disease the new disease
	 */
	public void setDisease(String disease) {
		this.disease = disease;
	}

	/**
	 * Gets the patient.
	 *
	 * @return the patient
	 */
	public Patient getPatient() {
		return patient;
	}

	/**
	 * Sets the patient.
	 *
	 * @param patient the new patient
	 */
	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	/**
	 * Gets the doctor id.
	 *
	 * @return the doctor id
	 */
	public int getDoctorId() {
		return doctorId;
	}

	/**
	 * Sets the doctor id.
	 *
	 * @param doctorId the new doctor id
	 */
	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}
}
