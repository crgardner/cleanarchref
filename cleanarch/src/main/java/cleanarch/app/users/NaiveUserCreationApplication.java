package cleanarch.app.users;

import java.util.function.Consumer;

import cleanarch.appbound.users.*;

public class NaiveUserCreationApplication implements UserCreationBoundary {

    @Override
    public void handle(UserData userData, Consumer<UserData> receiver) {
        UserData createdUserData = new UserData();
        createdUserData.setName(userData.getName());

        receiver.accept(createdUserData);
    }

}
