/*
 *
 */
package global.coda.hmsbackend.constants;

// TODO: Auto-generated Javadoc
/**
 * Used in SQL Queries.
 *
 * @author Vc
 */
public final class QueryConstants {
	private QueryConstants() {
		// Query Constants
	}

	/** The Constant USER_ID. */
	// User field Constants
	public static final String USER_ID = "pk_user_id";

	/** The Constant USER_NAME. */
	public static final String USER_NAME = "user_username";

	/** The Constant USER_PASSWORD. */
	public static final String USER_PASSWORD = "user_password";

	/** The Constant USER_EMAIL. */
	public static final String USER_EMAIL = "user_email";

	/** The Constant USER_PHONENUMBER. */
	public static final String USER_PHONENUMBER = "user_phonenumber";

	/** The Constant USER_FIRSTNAME. */
	public static final String USER_FIRSTNAME = "user_firstname";

	/** The Constant USER_LASTNAME. */
	public static final String USER_LASTNAME = "user_lastname";

	/** The Constant USER_AGE. */
	public static final String USER_AGE = "user_age";

	/** The Constant USER_CITY. */
	public static final String USER_CITY = "user_city";

	/** The Constant USER_STATE. */
	public static final String USER_STATE = "user_state";

	/** The Constant USER_STREET. */
	public static final String USER_STREET = "user_street";

	/** The Constant USER_ROLE_ID. */
	public static final String USER_ROLE_ID = "fk_role_id";

	/** The Constant USER_ISACTIVE. */
	public static final String USER_ISACTIVE = "user_isactive";

	/** The Constant USER_CREATEDON. */
	public static final String USER_CREATEDON = "user_created_on";

	/** The Constant USER_UPDATEDON. */
	public static final String USER_UPDATEDON = "user_updated_on";

	/** The Constant PATIENT_ID. */
	public static final String PATIENT_ID = "pk_patient_id";

	/** The Constant PATIENT_HEIGHT. */
	// Patient Field Constants
	public static final String PATIENT_HEIGHT = "patient_height";

	/** The Constant PATIENT_WEIGHT. */
	public static final String PATIENT_WEIGHT = "patient_weight";

	/** The Constant PATIENT_BLOODGROUP. */
	public static final String PATIENT_BLOODGROUP = "patient_blood_group";

	/** The Constant PATIENT_ISACTIVE. */
	public static final String PATIENT_ISACTIVE = "patient_isactive";

	/** The Constant PATIENT_CREATEDON. */
	public static final String PATIENT_CREATEDON = "patient_created_on";

	/** The Constant PATIENT_UPDATEDON. */
	public static final String PATIENT_UPDATEDON = "patient_updated_on";

	/** The Constant PATIENT_DISEASE. */
	public static final String PATIENT_DISEASE = "patient_disease";

	/** The Constant DOCTOR_ID. */
	public static final String DOCTOR_ID = "pk_doctor_id";
	/** The Constant DOCTOR_EXPERIENCE. */
	// Doctor Field Constants
	public static final String DOCTOR_EXPERIENCE = "doctor_experience";

	/** The Constant DOCTOR_SPECIALITY. */
	public static final String DOCTOR_SPECIALITY = "doctor_speciality";

	/** The Constant DOCTOR_ISACTIVE. */
	public static final String DOCTOR_ISACTIVE = "doctor_isactive";

	/** The Constant DOCTOR_CREATEDON. */
	public static final String DOCTOR_CREATEDON = "doctor_created_on";

	/** The Constant DOCTOR_UPDATEDON. */
	public static final String DOCTOR_UPDATEDON = "doctor_updated_on";

	/** The Constant DOCTOR_INSERT. */
	// Doctor Queries
	public static final String DOCTOR_INSERT = "INSERT INTO" + " `hospital_management_system`." + "`t_doctor_details` "
			+ "(`doctor_experience`, `doctor_speciality`,`fk_user_id`)" + " VALUES (?,?,?)";

	/** The Constant DOCTOR_READ_ALL. */
	public static final String DOCTOR_READ_ALL = "select user.pk_user_id,user.user_username"
			+ ",user.user_password,user.user_email,user.user_phonenumber,"
			+ "user.user_firstname,user.user_lastname,user.user_age," + "user.user_street,user.user_city,"
			+ "user.user_state,user.fk_role_id," + "doctor.doctor_experience,doctor.doctor_speciality,"
			+ "user.user_isactive,doctor.doctor_isactive,"
			+ "doctor.pk_doctor_id,user.user_created_on,user.user_updated_on "
			+ "from t_user_details as user INNER JOIN t_doctor_details as doctor"
			+ " on user.pk_user_id = doctor.fk_user_id where user.user_isactive=1 "
			+ "and doctor.doctor_isactive=1 and user.fk_role_id=3";

	/** The Constant DOCTOR_READ. */
	public static final String DOCTOR_READ = "select user.pk_user_id,user.user_username,"
			+ "user.user_password,user.user_email,user.user_phonenumber,"
			+ "user.user_firstname,user.user_lastname,user.user_age,user.user_street,"
			+ "user.user_city,user.user_state,user.fk_role_id,doctor.doctor_experience,"
			+ "doctor.doctor_speciality,user.user_isactive,doctor.doctor_isactive,"
			+ "doctor.pk_doctor_id,user.user_created_on,user.user_updated_on from"
			+ " t_user_details as user INNER JOIN t_doctor_details as doctor on user.pk_user_id = doctor.fk_user_id"
			+ " where user.user_isactive=1 and doctor.doctor_isactive=1 and user.fk_role_id=3"
			+ " and user.pk_user_id=?";

	/** The Constant DOCTOR_DELETE. */
	public static final String DOCTOR_DELETE = "update t_user_details as user,t_doctor_details as doctor "
			+ "set user.user_isactive=0,doctor.doctor_isactive=0 where user.pk_user_id=? and "
			+ "user.pk_user_id=doctor.fk_user_id and user.user_isactive=1 and  user.fk_role_id =3";

	/** The Constant DOCTOR_UPDATE. */
	public static final String DOCTOR_UPDATE = "update t_doctor_details set doctor_experience=?,"
			+ "doctor_speciality=? where fk_user_id=?";

	/** The Constant PATIENT_INSERT. */
	// Patient Queries
	public static final String PATIENT_INSERT = "INSERT INTO `hospital_management_system`.`t_patient_details` (`patient_height`, "
			+ "`patient_blood_group`," + "`patient_weight`,`fk_userid`) VALUES (?,?,?,?)";

	/** The Constant PATIENT_READ_ALL. */
	public static final String PATIENT_READ_ALL = "select user.pk_user_id,user.user_username,user.user_password,"
			+ "user.user_email,user.user_phonenumber,user.user_firstname,user.user_lastname,"
			+ "user.user_age,user.user_street,user.user_city,user.user_state,"
			+ "user.fk_role_id,patient.patient_height,patient.patient_blood_group,"
			+ "patient.patient_weight,user.user_isactive,user.user_created_on,"
			+ "user.user_updated_on,patient.patient_isactive,patient.pk_patient_id "
			+ "from t_user_details as user INNER JOIN t_patient_details as patient "
			+ "on user.pk_user_id = patient.fk_userid where user.user_isactive=1 "
			+ "and patient.patient_isactive=1 and user.fk_role_id=2";

	/** The Constant PATIENT_READ. */
	public static final String PATIENT_READ = "select user.pk_user_id,user.user_username,user.user_password,user.user_email,"
			+ "user.user_phonenumber,user.user_firstname,user.user_lastname,user.user_age,"
			+ "user.user_street,user.user_city,user.user_state,user.fk_role_id,"
			+ "patient.patient_height,patient.patient_blood_group,patient.patient_weight,"
			+ "user.user_isactive,user.user_created_on,user.user_updated_on,"
			+ "patient.patient_isactive,patient.pk_patient_id from t_user_details as user "
			+ "INNER JOIN t_patient_details as patient on user.pk_user_id = patient.fk_userid "
			+ "where user.user_isactive=1 and patient.patient_isactive=1 "
			+ "and user.fk_role_id=2 and user.pk_user_id=?";

	/** The Constant PATIENT_DELETE. */
	public static final String PATIENT_DELETE = "update t_user_details as user,t_patient_details as"
			+ " patient set user.user_isactive=0,patient.patient_isactive=0 where user.pk_user_id=? "
			+ "and user.pk_user_id=patient.fk_userid and user.user_isactive=1 and  " + "user.fk_role_id =2";

	/** The Constant PATIENT_UPDATE. */
	public static final String PATIENT_UPDATE = "update t_patient_details set "
			+ " patient_height=?,patient_blood_group=?,patient_weight=? where fk_userid=?";

	/** The Constant DOCTOR_FK_USER_ID. */
	public static final String DOCTOR_FK_USER_ID = "fk_doctor_id";

	/** The Constant USER_SELECT_WITH_ID. */
	// User Queries
	public static final String USER_SELECT_WITH_ID = "select * from t_user_details where "
			+ "user_username=? and user_password=?";

	/** The Constant USER_INSERT. */
	public static final String USER_INSERT = "INSERT INTO `hospital_management_system`."
			+ "`t_user_details` (`user_username`, `user_password`, `user_email`,"
			+ " `user_phonenumber`, `user_firstname`, `user_lastname`, `user_age`, `user_city`,"
			+ " `user_state`, `user_street`, `fk_role_id`) VALUES (?, ?, ?, ?, ?, ?," + " ?, ?, ?, ?, ?)";

	/** The Constant USER_UPDATE. */
	public static final String USER_UPDATE = "update t_user_details set user_username=?,"
			+ "user_password=?,user_email=?,user_phonenumber=?,user_firstname=?,"
			+ "user_lastname=?,user_age=?,user_city=?,user_state=?,user_street=?" + " where pk_user_id=?";

	/** The Constant FIND_PATIENTS_BY_DOCTOR_ID. */
	public static final String FIND_PATIENTS_BY_DOCTOR_ID = "select user.*,patient.patient_height,"
			+ "patient.patient_blood_group,patient.patient_weight,record.patient_disease,"
			+ "patient.pk_patient_id,patient.patient_isactive from \r\n" + "t_user_details user INNER JOIN \r\n"
			+ "t_patient_details patient ON user.pk_user_id=patient.fk_userid \r\n"
			+ "INNER JOIN t_record record ON record.fk_patient_id=patient.fk_userid \r\n"
			+ "WHERE user.user_isactive=1 and patient.patient_isactive=1 and record.doctor_patient_mapping_isactive=1  \r\n"
			+ "and record.fk_doctor_id=? group by patient.pk_patient_id";

	/** The Constant SELECT_ALL_DOCTOR_ID. */
	public static final String SELECT_ALL_DOCTOR_ID = "select pk_user_id from t_user_details " + "where fk_role_id=3";
}
