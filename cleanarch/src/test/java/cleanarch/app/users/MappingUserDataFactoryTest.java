package cleanarch.app.users;

import static org.assertj.core.api.Assertions.*;

import org.junit.*;

import cleanarch.appbound.users.UserData;
import cleanarch.domain.users.User;


public class MappingUserDataFactoryTest {
    private UserDataFactory userDataFactory;
    private User user;
    private UserData userData;

    @Before
    public void setUp() {
        userDataFactory = new MappingUserDataFactory();
    }
    
    @Test
    public void createsUserDataFromUser() throws Exception {
        user = new User("name");
        
        userData = userDataFactory.createFrom(user);
        
        assertThat(userData.getName()).isEqualTo("name");
    }
}
