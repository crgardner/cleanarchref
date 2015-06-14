package cleanarch.ia.api.users;

import java.net.URI;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import cleanarch.appbound.users.*;

@Path("/v1/users")
public class UserCreationController {

    private final UserCreationBoundary userCreationBoundary;

    public UserCreationController(UserCreationBoundary userCreationBoundary) {
        this.userCreationBoundary = userCreationBoundary;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response handle(UserData userData, @Context UriInfo uriInfo) {
        ResponseHolder holder = new ResponseHolder();
        
        userCreationBoundary.handle(userData, createdUser -> {
            URI location = uriInfo.getAbsolutePathBuilder().clone().path(createdUser.getId()).build();
            holder.response = Response.created(location).entity(createdUser).build();
        });

        return holder.response;
    }
}
