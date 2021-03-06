package cleanarch.ia.api.users;

import static javax.ws.rs.core.Response.ok;
import static javax.ws.rs.core.Response.status;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import cleanarch.appbound.users.UserSearchBoundary;
import cleanarch.appbound.users.UserSearchRequest;
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
}