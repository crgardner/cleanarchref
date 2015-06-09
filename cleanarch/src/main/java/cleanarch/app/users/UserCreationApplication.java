package cleanarch.app.users;

import java.util.function.Consumer;

import cleanarch.appbound.users.*;
import cleanarch.domain.users.User;
import cleanarch.domain.users.UserRoster;

public class UserCreationApplication implements UserCreationBoundary {
	private UserRoster userRoster;
	
    public UserCreationApplication(UserRoster userRoster) {
		this.userRoster = userRoster;
	}

	@Override
    public void handle(UserData userData, Consumer<UserData> receiver) {
    	User user = createUserFrom(userData);
        
    	userRoster.add(user);
    	
    	UserData createdUserData = createResponseFrom(user);
    	
        receiver.accept(createdUserData);
    }

	private UserData createResponseFrom(User user) {
		UserData createdUserData = new UserData();
    	createdUserData.setName(user.getName());
    	createdUserData.setId(user.getId());
		return createdUserData;
	}

	private User createUserFrom(UserData userData) {
		return new User(userData.getName());
	}

}
