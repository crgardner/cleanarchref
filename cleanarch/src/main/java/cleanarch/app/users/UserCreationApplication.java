package cleanarch.app.users;

import java.util.function.Consumer;

import cleanarch.appbound.users.*;
import cleanarch.domain.users.User;
import cleanarch.domain.users.UserRoster;

public class UserCreationApplication implements UserCreationBoundary {
    private final UserRoster userRoster;
    private final UserFactory userFactory;
    private final UserDataFactory userDataFactory;

    public UserCreationApplication(UserRoster userRoster, UserFactory userFactory, UserDataFactory userDataFactory) {
        this.userRoster = userRoster;
        this.userFactory = userFactory;
        this.userDataFactory = userDataFactory;
    }

    @Override
    public void handle(UserData userData, Consumer<UserData> receiver) {
        User user = userFactory.createFrom(userData);
        userRoster.add(user);

        receiver.accept(userDataFactory.createFrom(user));
    }

}
