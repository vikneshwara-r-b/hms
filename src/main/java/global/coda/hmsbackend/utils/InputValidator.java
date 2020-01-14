/*
 *
 */
package global.coda.hmsbackend.utils;

import static global.coda.hmsbackend.constants.ApplicationConstants.NUMERIC_REGEX;

// TODO: Auto-generated Javadoc
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
		if (value.matches(NUMERIC_REGEX)) {
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
