package cleanarch.ia.api.users;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Path("/v1/users")
public class UsersResource {
    private final UserCreationController creationController;

    public UsersResource(UserCreationController creationController) {
        this.creationController = creationController;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String get() {
        return "UsersResource";
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public Response post(String name, @Context UriInfo uriInfo) {
        return creationController.handle(name, uriInfo.getAbsolutePathBuilder());
    }
}