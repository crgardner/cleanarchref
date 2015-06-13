package cleanarch.ia.api.users;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.net.URI;
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
public class UserCreationControllerTest {
    @Mock
    private UserCreationBoundary userCreationBoundary;
    
    @Mock
    private UriBuilder uriBuilder;
    
    @Mock
    private UriInfo uriInfo;
    
    private UserCreationController userCreationController;
    private UserData userData;

    private Response response;
    
    
    @Before
    public void setUp() {
        userCreationController = new UserCreationController(userCreationBoundary);
        
        userData = new UserData();
        userData.setName("user");
    }
    
    @Test
    @SuppressWarnings("unchecked")
    public void handlesCreationRequests() throws Exception {
        doAnswer(byCallingReceiver()).when(userCreationBoundary).handle(refEq(userData), any(Consumer.class));
        when(uriInfo.getAbsolutePathBuilder()).thenReturn(uriBuilder);
        when(uriBuilder.clone()).thenReturn(uriBuilder);
        when(uriBuilder.path(any())).thenReturn(uriBuilder);
        when(uriBuilder.build()).thenReturn(new URI("http:/mysite.com/users"));
        
        response = userCreationController.handle("user", uriInfo);
        
        assertThat(response.getEntity()).isEqualTo("user");
        assertThat(response.getStatus()).isEqualTo(Status.CREATED.getStatusCode());
    }
    

    @SuppressWarnings("unchecked")
    private Answer<?> byCallingReceiver() {
        return (invocation) -> {
            invocation.getArgumentAt(1, Consumer.class).accept(userData);
            return null;
        };
    }

}
