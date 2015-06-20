package cleanarch.app.users;

import cleanarch.appbound.users.UserData;
import cleanarch.domain.users.*;

class MappingUserFactory implements UserFactory {

    private PasswordPolicy passwordPolicy;

    public MappingUserFactory(PasswordPolicy passwordPolicy) {
        this.passwordPolicy = passwordPolicy;
    }

    @Override
    public User createFrom(UserData userData) {
        passwordPolicy.verify(userData.getPassword());
        
        User user = new User(userData.getName());
        user.changePassword(userData.getPassword());
        
        return user;
    }

}
