package cleanarch.appbound.users;

import java.util.function.Consumer;

public interface UserCreationBoundary {

    void handle(UserData userData, Consumer<UserData> receiver);

}
