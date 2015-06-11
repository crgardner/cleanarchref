package cleanarch.app.users;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import cleanarch.appbound.users.*;
import cleanarch.domain.users.*;

@RunWith(MockitoJUnitRunner.class)
public class UserSearchApplicationTest {
    @Mock
    private UserRoster userRoster;

    private UserSearchBoundary userSearchApplication;
    private User user;
    private Optional<UserData> actualUserData;
    private UserData expectedUserData;

    @Before
    public void setUp() {
        userSearchApplication = new UserSearchApplication(userRoster);
    }

    @Test
    public void findsSpecificUser() {
        havingAUserWithName("name");
        when(userRoster.findById("123")).thenReturn(Optional.of(user));
        expectingUserDataWithName("name");

        userSearchApplication.handle(new UserSearchRequest("123"), user -> {
            actualUserData = user;
        });

        assertThat(actualUserData.get()).isEqualTo(expectedUserData);
    }

    @Test
    public void indicatesUserCannotBeFound() {
        when(userRoster.findById(anyString())).thenReturn(Optional.empty());

        userSearchApplication.handle(new UserSearchRequest("123"), user -> {
            actualUserData = user;
        });

        assertThat(actualUserData.isPresent()).isFalse();
    }

    private void havingAUserWithName(String name) {
        user = new User(name);
    }

    private void expectingUserDataWithName(String name) {
        expectedUserData = new UserData();
        expectedUserData.setName(name);
    }

}