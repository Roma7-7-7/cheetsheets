package cheetsheets.dk.core;

import cheetsheets.dk.api.ErrorDetails;
import cheetsheets.dk.api.GenericResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import java.util.Collections;
import java.util.List;

public class GeneralExceptionMapper implements ExceptionMapper<Exception> {

    private static final Logger logger = LoggerFactory.getLogger(GeneralExceptionMapper.class);

    private static final String MESSAGE = "Unexpected server error.";

    public Response toResponse(Exception e) {
        logger.error(MESSAGE, e);
        final List<ErrorDetails> details = Collections.singletonList(
                new ErrorDetails(ErrorDetails.Code.INTERNAL_SERVER_ERROR, MESSAGE));
        return Response
                .serverError()
                .type(MediaType.APPLICATION_JSON_TYPE)
                .entity(GenericResponse.error(details))
                .build();
    }

}
