package cleanarch.ia.api.users;

import java.net.URI;
import java.util.function.Consumer;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import cleanarch.appbound.users.*;

public class UserCreationController {

    private final UserCreationBoundary userCreationBoundary;

    public UserCreationController(UserCreationBoundary userCreationBoundary) {
        this.userCreationBoundary = userCreationBoundary;
    }

    public Response handle(String userName, UriBuilder uriBuilder) {
        UserData userData = createRequest(userName);

        ResponsePresenter presenter = new ResponsePresenter(uriBuilder);
        userCreationBoundary.handle(userData, presenter);

        return presenter.response;
    }

    private UserData createRequest(String userName) {
        UserData userData = new UserData();
        userData.setName(userName);
        return userData;
    }

    class ResponsePresenter implements Consumer<UserData> {
        private final UriBuilder uriBuilder;
        private Response response;

        ResponsePresenter(UriBuilder uriBuilder) {
            this.uriBuilder = uriBuilder;
        }

        @Override
        public void accept(UserData applicationResponse) {
            URI location = uriBuilder.clone().path(applicationResponse.getId()).build();
            response = Response.created(location).entity(applicationResponse.getName()).build();
        }

    }

}
