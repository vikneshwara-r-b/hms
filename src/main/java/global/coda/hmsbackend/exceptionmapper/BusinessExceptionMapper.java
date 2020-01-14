/*
 *
 */
package global.coda.hmsbackend.exceptionmapper;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import global.coda.hmsbackend.constants.HttpStatusConstants;
import global.coda.hmsbackend.exception.BusinessException;
import global.coda.hmsbackend.models.ResponseBody;

// TODO: Auto-generated Javadoc
/**
 * The Class BusinessExceptionMapper.
 */
@Provider
public class BusinessExceptionMapper extends Exception implements ExceptionMapper<BusinessException> {

	private final Logger LOGGER = LogManager.getLogger(BusinessException.class);
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2917800489848608230L;

	/**
	 * To response.
	 *
	 * @param exception the exception
	 * @return the response
	 */
	@Override
	public Response toResponse(BusinessException exception) {
		LOGGER.error(exception.getMessage());
		ResponseBody responseBody = new ResponseBody();
		responseBody.setStatusCode(HttpStatusConstants.CLIENT_ERROR);
		responseBody.setData(exception.getMessage());
		return Response.status(HttpStatusConstants.CLIENT_ERROR).type(MediaType.APPLICATION_JSON).entity(responseBody)
				.build();
	}

}
