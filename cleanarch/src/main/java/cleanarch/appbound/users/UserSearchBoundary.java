package cleanarch.appbound.users;

import java.util.Optional;
import java.util.function.Consumer;

public interface UserSearchBoundary {

    void handle(UserSearchRequest userSearchRequest, Consumer<Optional<UserData>> receiver);

}
