/*
 *
 */
package global.coda.hmsbackend.models;

import java.sql.Date;
// TODO: Auto-generated Javadoc
//TODO: Auto-generated Javadoc

/**
 * Patient entity.
 *
 * @author Vc
 */
public class Patient extends User {

	/** The patient id. */
	private int patientId;

	/** The patient height. */
	private int patientHeight;

	/** The patient weight. */
	private int patientWeight;

	/** The blood group. */
	private String bloodGroup;

	/** The isactive. */
	private int isActive;

	/** The created on. */
	private Date createdOn;

	/** The updated on. */
	private Date updatedOn;

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "Patient [patientId=" + patientId + ", patientHeight=" + patientHeight + ", patientWeight="
				+ patientWeight + ", bloodGroup=" + bloodGroup + ", isactive=" + isActive + ", createdOn=" + createdOn
				+ ", updatedOn=" + updatedOn + ", getUserId()=" + getUserId() + ", getUsername()=" + getUsername()
				+ ", getPassword()=" + getPassword() + ", getEmail()=" + getEmail() + ", getPhonenumber()="
				+ getPhonenumber() + ", getFirstname()=" + getFirstname() + ", getLastname()=" + getLastname()
				+ ", getCity()=" + getCity() + ", getState()=" + getState() + ", getStreet()=" + getStreet()
				+ ", getRoleId()=" + getRoleId() + ", getAge()=" + getAge() + "]";
	}

	/**
	 * Gets the patient id.
	 *
	 * @return the patient id
	 */
	public int getPatientId() {
		return patientId;
	}

	/**
	 * Sets the patient id.
	 *
	 * @param patientId the new patient id
	 */
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	/**
	 * Gets the patient height.
	 *
	 * @return the patient height
	 */
	public int getPatientHeight() {
		return patientHeight;
	}

	/**
	 * Sets the patient height.
	 *
	 * @param patientHeight the new patient height
	 */
	public void setPatientHeight(int patientHeight) {
		this.patientHeight = patientHeight;
	}

	/**
	 * Gets the patient weight.
	 *
	 * @return the patient weight
	 */
	public int getPatientWeight() {
		return patientWeight;
	}

	/**
	 * Sets the patient weight.
	 *
	 * @param patientWeight the new patient weight
	 */
	public void setPatientWeight(int patientWeight) {
		this.patientWeight = patientWeight;
	}

	/**
	 * Gets the blood group.
	 *
	 * @return the blood group
	 */
	public String getBloodGroup() {
		return bloodGroup;
	}

	/**
	 * Sets the blood group.
	 *
	 * @param bloodGroup the new blood group
	 */
	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	/**
	 * Gets the isactive.
	 *
	 * @return the isactive
	 */
	public int getIsactive() {
		return isActive;
	}

	/**
	 * Sets the isactive.
	 *
	 * @param isactive the new isactive
	 */
	public void setIsactive(int isactive) {
		this.isActive = isactive;
	}

	/**
	 * Gets the created on.
	 *
	 * @return the created on
	 */
	@Override
	public Date getCreatedOn() {
		return createdOn;
	}

	/**
	 * Sets the created on.
	 *
	 * @param createdOn the new created on
	 */
	@Override
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	/**
	 * Gets the updated on.
	 *
	 * @return the updated on
	 */
	@Override
	public Date getUpdatedOn() {
		return updatedOn;
	}

	/**
	 * Sets the updated on.
	 *
	 * @param updatedOn the new updated on
	 */
	@Override
	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}
}
