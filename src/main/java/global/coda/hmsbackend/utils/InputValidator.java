/*
 *
 */
package global.coda.hmsbackend.utils;

import global.coda.hmsbackend.constants.ApplicationConstants;

/**
 * The Class InputValidator.
 */
public class InputValidator {

	/**
	 * Validate integer.
	 *
	 * @param value the value
	 * @return true, if successful
	 */
	public boolean validateInteger(String value) {
		boolean result = false;
		if (value.matches(ApplicationConstants.NUMERIC_REGEX)) {
			result = true;
		}
		return result;
	}

	/**
	 * Validate null value.
	 *
	 * @param value the value
	 * @return true, if successful
	 */
	public boolean validateNullValue(String value) {
		boolean result = false;
		if ((value == null) || (value == "")) {
			result = true;
		}
		return result;
	}
}
