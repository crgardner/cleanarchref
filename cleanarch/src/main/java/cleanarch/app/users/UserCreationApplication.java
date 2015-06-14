package cleanarch.app.users;

import java.util.function.Consumer;

import cleanarch.appbound.users.*;
import cleanarch.domain.users.User;
import cleanarch.domain.users.UserRoster;

public class UserCreationApplication implements UserCreationBoundary {
    private final UserRoster userRoster;
    private final UserDataFactory userDataFactory;

    public UserCreationApplication(UserRoster userRoster, UserDataFactory userDataFactory) {
        this.userRoster = userRoster;
        this.userDataFactory = userDataFactory;
    }

    @Override
    public void handle(UserData userData, Consumer<UserData> receiver) {
        User user = createUserFrom(userData);
        userRoster.add(user);

        receiver.accept(userDataFactory.createFrom(user));
    }

    private User createUserFrom(UserData userData) {
        return new User(userData.getName());
    }

}
