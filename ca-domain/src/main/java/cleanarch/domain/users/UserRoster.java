package cleanarch.domain.users;

import java.util.*;

public interface UserRoster {

    void add(User user);

    List<User> findAll();

    Optional<User> findById(String id);

}
