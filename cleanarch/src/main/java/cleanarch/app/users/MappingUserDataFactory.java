package cleanarch.app.users;

import cleanarch.appbound.users.UserData;
import cleanarch.domain.users.User;

class MappingUserDataFactory implements UserDataFactory {

    @Override
    public UserData createFrom(User user) {
        UserData userData = new UserData();
        userData.setId(user.getId());
        userData.setName(user.getName());
        return userData;
    }

}
