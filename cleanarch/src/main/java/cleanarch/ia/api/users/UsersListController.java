package cleanarch.ia.api.users;

import static javax.ws.rs.core.Response.*;

import java.util.Arrays;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.core.Response.Status;

import cleanarch.appbound.users.*;
import cleanarch.ia.api.response.ResponseHolder;

@Path("/v1/users")
public class UsersListController {
    private final UserSearchBoundary userSearchBoundary;

    public UsersListController(UserSearchBoundary userSearchBoundary) {
        this.userSearchBoundary = userSearchBoundary;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response handle(@PathParam("id") String id) {
        UserSearchRequest userSearchRequest = new UserSearchRequest(id);
        ResponseHolder responseHolder = new ResponseHolder();

        userSearchBoundary.handle(userSearchRequest, possibleUser -> {
            ResponseBuilder builder = (possibleUser.isPresent()) ? ok().entity(possibleUser.get())
                                                                 : status(Status.NOT_FOUND);
            responseHolder.response = builder.build();
        });
        return responseHolder.response;
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response handle() {
        return Response.ok().entity(Arrays.asList(new UserData())).build();
    }
}