/*
 *
 */
package global.coda.hmsbackend.exceptionmapper;

import java.util.ResourceBundle;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import global.coda.hmsbackend.constants.ApplicationConstants;
import global.coda.hmsbackend.constants.HttpStatusConstants;
import global.coda.hmsbackend.exception.SystemException;
import global.coda.hmsbackend.models.ResponseBody;

/**
 * The Class SystemExceptionMapper.
 */
@Provider
public class SystemExceptionMapper extends Exception implements ExceptionMapper<SystemException> {

	ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(ApplicationConstants.MESSAGES_BUNDLE);
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2849943181098489635L;

	/** The logger. */
	private final Logger LOGGER = LogManager.getLogger(SystemExceptionMapper.class);

	/**
	 * To response.
	 *
	 * @param exception the exception
	 * @return the response
	 */
	@Override
	public Response toResponse(SystemException exception) {
		LOGGER.error(exception.getMessage());
		ResponseBody responseBody = new ResponseBody();
		responseBody.setStatusCode(HttpStatusConstants.INTERNAL_SERVER_ERROR);
		responseBody.setData(RESOURCE_BUNDLE.getString(ApplicationConstants.HMS3001E));
		return Response.status(HttpStatusConstants.INTERNAL_SERVER_ERROR).type(MediaType.APPLICATION_JSON)
				.entity(responseBody).build();
	}

}
