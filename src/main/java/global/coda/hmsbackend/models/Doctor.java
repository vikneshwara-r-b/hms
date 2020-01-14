/*
 *
 */
package global.coda.hmsbackend.models;

import java.sql.Date;

// TODO: Auto-generated Javadoc
//TODO: Auto-generated Javadoc
/**
 * Doctor entity.
 *
 * @author Vc
 *
 */
public class Doctor extends User {

	/** The doctor id. */
	private int doctorId;

	/** The doctorexperience. */
	private int doctorexperience;

	/** The doctorspeciality. */
	private String doctorspeciality;

	/** The isactive. */
	private int isactive;

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
		return "Doctor [doctorId=" + doctorId + ", doctorexperience=" + doctorexperience + ", doctorspeciality="
				+ doctorspeciality + ", isactive=" + isactive + ", createdOn=" + createdOn + ", updatedOn=" + updatedOn
				+ ", toString()=" + super.toString() + ", getUserId()=" + getUserId() + ", getUsername()="
				+ getUsername() + ", getPassword()=" + getPassword() + ", getEmail()=" + getEmail()
				+ ", getPhonenumber()=" + getPhonenumber() + ", getFirstname()=" + getFirstname() + ", getLastname()="
				+ getLastname() + ", getCity()=" + getCity() + ", getState()=" + getState() + ", getStreet()="
				+ getStreet() + ", getRoleId()=" + getRoleId() + ", getAge()=" + getAge() + "]";
	}

	/**
	 * Gets the isactive.
	 *
	 * @return the isactive
	 */
	public int getIsactive() {
		return isactive;
	}

	/**
	 * Sets the isactive.
	 *
	 * @param isactive the new isactive
	 */
	public void setIsactive(int isactive) {
		this.isactive = isactive;
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

	/**
	 * Gets the doctorexperience.
	 *
	 * @return the doctorexperience
	 */
	public int getDoctorexperience() {
		return doctorexperience;
	}

	/**
	 * Sets the doctorexperience.
	 *
	 * @param doctorexperience the new doctorexperience
	 */
	public void setDoctorexperience(int doctorexperience) {
		this.doctorexperience = doctorexperience;
	}

	/**
	 * Gets the doctorspeciality.
	 *
	 * @return the doctorspeciality
	 */
	public String getDoctorspeciality() {
		return doctorspeciality;
	}

	/**
	 * Sets the doctorspeciality.
	 *
	 * @param doctorspeciality the new doctorspeciality
	 */
	public void setDoctorspeciality(String doctorspeciality) {
		this.doctorspeciality = doctorspeciality;
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
