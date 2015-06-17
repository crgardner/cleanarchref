package cleanarch.app.users;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import cleanarch.appbound.users.UserData;
import cleanarch.domain.users.*;

@RunWith(MockitoJUnitRunner.class)
public class MappingUserFactoryTest {
    @Mock
    private PasswordPolicy passwordPolicy;
    
    private MappingUserFactory factory;
    private UserData userData;
    private User user;

    @Before
    public void setUp() {
        factory = new MappingUserFactory(passwordPolicy);
        
        userData = new UserData();
        userData.setName("user");
        userData.setPassword("123");
    }
    
    @Test
    public void createsUserFromUserDataValidatingInput() throws Exception {
        
        user = factory.createFrom(userData);
        
        assertThat(user.getName()).isEqualTo("user");
    }
    
    @Test(expected=InvalidPasswordException.class)
    public void preventsCreatingUserWithInvalidPassword() {
        doThrow(InvalidPasswordException.class).when(passwordPolicy).verify("123");
        
        factory.createFrom(userData);
    }
}
