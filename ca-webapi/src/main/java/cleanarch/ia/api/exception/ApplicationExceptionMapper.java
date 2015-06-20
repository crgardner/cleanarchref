package cleanarch.ia.api.exception;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;

import cleanarch.common.exception.*;
import cleanarch.common.message.*;

public class ApplicationExceptionMapper implements ExceptionMapper<ApplicationException> {
    private Map<Class<? extends ApplicationException>, Response.Status> map = new HashMap<>();
    
    {
        map.put(InvalidException.class, Response.Status.BAD_REQUEST);
    }
    
    
    private final MessageCatalog messageCatalog;

    public ApplicationExceptionMapper(MessageCatalog messageCatalog) {
        this.messageCatalog = messageCatalog;
    }

    @Override
    @Produces(MediaType.APPLICATION_JSON)
    public Response toResponse(ApplicationException exception) {
        Message message = exception.createMessage(messageCatalog);
        
        return Response.status(from(exception)).entity(message).build();
    }

    private Status from(ApplicationException exception) {
        return map.get(exception.getClass());
    }


}
