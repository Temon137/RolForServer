package rolfor.rest;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.ArrayList;
import java.util.List;


@Provider
public class ViolationExceptionToJSONMapper implements ExceptionMapper<ConstraintViolationException> {
	@Override
	public Response toResponse(final ConstraintViolationException exception) {
		return Response.status(Response.Status.BAD_REQUEST)
		               .entity(prepareMessage(exception))
		               .type(MediaType.APPLICATION_JSON_TYPE)
		               .build();
	}
	
	private String prepareMessage(ConstraintViolationException exception) {
		ObjectMapper mapper    = new ObjectMapper();
		ErrorList    errorList = new ErrorList();
		for (ConstraintViolation<?> cv : exception.getConstraintViolations()) {
			errorList.addError(cv.getMessage());
		}
		try {
			return mapper.writeValueAsString(errorList);
		} catch (JsonProcessingException e) {
			return "{\"errors\":[\"Этого не должно было произойти, но всё-таки: " + e.getMessage() + "\"]}";
		}
	}
	
	private class ErrorList {
		@SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
		@JsonProperty("errors")
		private final List<String> errors;
		
		ErrorList() {
			errors = new ArrayList<>();
		}
		
		void addError(String error) {
			errors.add(error);
		}
	}
}
