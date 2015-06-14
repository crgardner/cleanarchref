package cleanarch.app.users;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;


import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;


import cleanarch.appbound.users.*;
import cleanarch.domain.users.*;


@RunWith(MockitoJUnitRunner.class)
public class UserCreationApplicationTest {
    @Mock
    private UserRoster userRoster;
    
    @Mock
    private UserDataFactory userDataFactory;

    private UserData userData;
    private User user;
    private UserCreationBoundary userCreationApplication;

    
    @Before
    public void setUp() {
        userCreationApplication = new UserCreationApplication(userRoster, userDataFactory);
    }
    
    @Test
    public void createsUser() throws Exception {
        userData = new UserData();
        userData.setName("user");
        user = new User("user");
        
        when(userDataFactory.createFrom(refEq(user))).thenReturn(userData);

        userCreationApplication.handle(userData, createdUserData -> {
            assertThat(createdUserData.getName()).isEqualTo("user");
        });
        
        verify(userRoster).add(refEq(user));
    }
}
