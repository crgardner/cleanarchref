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
    @Produces(MediaType.TEXT_PLAIN)
    public Response handle(String userName, @Context UriInfo uriInfo) {
        ResponseHolder holder = new ResponseHolder();
        
        UserData userData = createRequest(userName);

        userCreationBoundary.handle(userData, createdUser -> {
            URI location = uriInfo.getAbsolutePathBuilder().clone().path(createdUser.getId()).build();
            holder.response = Response.created(location).entity(createdUser.getName()).build();
        });

        return holder.response;
    }

    private UserData createRequest(String userName) {
        UserData userData = new UserData();
        userData.setName(userName);
        return userData;
    }

}
