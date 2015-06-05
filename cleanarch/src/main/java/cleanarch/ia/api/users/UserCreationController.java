package cleanarch.ia.api.users;

import java.util.function.Consumer;
import javax.ws.rs.core.Response;
import cleanarch.appbound.users.*;

public class UserCreationController {

    private final UserCreationBoundary userCreationBoundary;

    public UserCreationController(UserCreationBoundary userCreationBoundary) {
        this.userCreationBoundary = userCreationBoundary;
    }

    public Response handle(String userName) {
        UserData userData = createRequest(userName);
        
        ResponsePresenter presenter = new ResponsePresenter();
        userCreationBoundary.handle(userData, presenter);
        
        return presenter.response;
    }

    private UserData createRequest(String userName) {
        UserData userData = new UserData();
        userData.setName(userName);
        return userData;
    }
    
    class ResponsePresenter implements Consumer<UserData> {

        private Response response;

        @Override
        public void accept(UserData applicationResponse) {
            response = Response.ok().entity(applicationResponse.getName()).build();
        }
        
    }

}
