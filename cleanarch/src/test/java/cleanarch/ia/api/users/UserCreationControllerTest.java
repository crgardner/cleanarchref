package cleanarch.ia.api.users;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.function.Consumer;

import javax.ws.rs.core.Response;

import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import cleanarch.appbound.users.*;

@RunWith(MockitoJUnitRunner.class)
public class UserCreationControllerTest {
    @Mock
    private UserCreationBoundary userCreationBoundary;
    
    private UserCreationController controller;
    private String name;
    private Response response;

    @Before
    public void setUp() {
        controller = new UserCreationController(userCreationBoundary);
        name = "John Doe";
    }
    
    @Test
    @SuppressWarnings("unchecked")
    public void initiatesUserCreation() {
        doAnswer(byCallingConsumer()).when(userCreationBoundary).handle(any(UserData.class), any(Consumer.class));
        
        response = controller.handle(name);
        
        assertThat(response.getEntity()).isEqualTo(name);
    }

    @SuppressWarnings("unchecked")
    private Answer<?> byCallingConsumer() {
        return (invocation) -> {
            UserData userData = invocation.getArgumentAt(0, UserData.class);
            invocation.getArgumentAt(1, Consumer.class).accept(userData);
            return null;
        };
    }

}
