package cleanarch.ia.api.users;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.Optional;
import java.util.function.Consumer;

import javax.ws.rs.core.Response;

import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import cleanarch.appbound.users.*;


@RunWith(MockitoJUnitRunner.class)
public class UsersListControllerTest {
    @Mock
    private UserSearchBoundary userSearchBoundary;
    
    private UsersListController controller;
    private UserSearchRequest userSearchRequest;
    private UserData userData;
    
    @Before
    public void setUp() {
        controller = new UsersListController(userSearchBoundary);
        userSearchRequest = new UserSearchRequest("123");
        userData = new UserData();
        userData.setId("123");
        userData.setName("user");
    }
    
    @Test
    @SuppressWarnings("unchecked")
    public void findsUserWithGivenId() throws Exception {
        doAnswer(byCallingReceiver()).when(userSearchBoundary).handle(refEq(userSearchRequest), any(Consumer.class));
        
        Response response = controller.handle("123");
        
        assertThat(response.getEntity()).isEqualTo("user");
    }

    @SuppressWarnings("unchecked")
    private Answer<?> byCallingReceiver() {
        return invocation -> {
            invocation.getArgumentAt(1, Consumer.class).accept(Optional.of(userData));
            return null;
        };
    }
}
