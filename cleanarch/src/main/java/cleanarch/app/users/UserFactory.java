package cleanarch.app.users;

import cleanarch.appbound.users.UserData;
import cleanarch.domain.users.User;

public interface UserFactory {

    User createFrom(UserData userData);
}
