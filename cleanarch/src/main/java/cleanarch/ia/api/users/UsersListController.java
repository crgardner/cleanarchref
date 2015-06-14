package cleanarch.ia.api.users;

import static javax.ws.rs.core.Response.*;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.core.Response.Status;

import cleanarch.appbound.users.*;

@Path("/v1/users/{id}")
public class UsersListController {
    private final UserSearchBoundary userSearchBoundary;

    public UsersListController(UserSearchBoundary userSearchBoundary) {
        this.userSearchBoundary = userSearchBoundary;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response handle(@PathParam(value = "id") String id) {
        UserSearchRequest userSearchRequest = new UserSearchRequest(id);
        ResponseHolder responseHolder = new ResponseHolder();

        userSearchBoundary.handle(userSearchRequest, possibleUser -> {
            ResponseBuilder builder = (possibleUser.isPresent()) ? ok().entity(possibleUser.get())
                                                                 : status(Status.NOT_FOUND);
            responseHolder.response = builder.build();
        });
        return responseHolder.response;
    }

}