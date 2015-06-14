package cleanarch.ia.api.users;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.Optional;
import java.util.function.Consumer;

import javax.ws.rs.core.*;
import javax.ws.rs.core.Response.Status;

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
    private Response response;
    private String userId;
    
    @Before
    public void setUp() {
        controller = new UsersListController(userSearchBoundary);
        userId = "123";
        
        userSearchRequest = new UserSearchRequest("123");
        userData = new UserData();
        userData.setId(userId);
        userData.setName("user");
    }
    
    @Test
    @SuppressWarnings("unchecked")
    public void providesUserWithGivenId() throws Exception {
        doAnswer(byProviding(userData)).when(userSearchBoundary).handle(refEq(userSearchRequest), any(Consumer.class));
        
        response = controller.handle(userId);
        
        assertThat(response.getStatus()).isEqualTo(Status.OK.getStatusCode());
        assertThat(response.getEntity()).isEqualToComparingFieldByField(userData);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void returnsNotFoundWhenNoMatchingUser() throws Exception {
        doAnswer(byProvidingNoUser()).when(userSearchBoundary).handle(refEq(userSearchRequest), any(Consumer.class));
        
        response = controller.handle("123");
        
        assertThat(response.getStatus()).isEqualTo(Status.NOT_FOUND.getStatusCode());
        assertThat(response.getEntity()).isNull();
    }
    
    private Answer<?> byProvidingNoUser() {
        return byProviding(null);
    }

    @SuppressWarnings("unchecked")
    private Answer<?> byProviding(UserData userData) {
        return invocation -> {
            invocation.getArgumentAt(1, Consumer.class).accept(Optional.ofNullable(userData));
            return null;
        };
    }
}
