package cleanarch.app.users;

import java.util.Optional;
import java.util.function.Consumer;

import cleanarch.appbound.users.*;
import cleanarch.domain.users.*;

public class UserSearchApplication implements UserSearchBoundary {

    private final UserRoster userRoster;

    public UserSearchApplication(UserRoster userRoster) {
        this.userRoster = userRoster;
    }

    @Override
    public void handle(UserSearchRequest searchRequest, Consumer<Optional<UserData>> receiver) {
        receiver.accept(userDataMatching(searchRequest));
    }

    private Optional<UserData> userDataMatching(UserSearchRequest userSearchRequest) {
        return userRoster.findById(userSearchRequest.getUserId()).map(user -> dataFrom(user));
    }

    private UserData dataFrom(User user) {
        UserData userData = new UserData();
        userData.setId(user.getId());
        userData.setName(user.getName());
        return userData;
    }

}
