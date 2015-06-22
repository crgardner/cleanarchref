package cleanarch.db.jpa.users;

import static org.assertj.core.api.Assertions.*;

import java.util.Optional;

import javax.persistence.*;

import org.junit.*;

import cleanarch.domain.users.User;

public class JpaUserRosterTest {

    private User user;
    private EntityManagerFactory emf;
    private EntityManager em;
    private JpaUserRoster jpaUserRoster;
    private Optional<User> possibleUser;

    @Before
    public void setUp() throws Exception {
        emf = Persistence.createEntityManagerFactory("cleanarch");
        em = emf.createEntityManager();
        
        jpaUserRoster = new JpaUserRoster();
        jpaUserRoster.set(em);
        
        user = new User("user");
        user.changePassword("foobar");
    }
    
    @Test
    public void addsUserToDatabase() {
        em.getTransaction().begin();
        
        jpaUserRoster.add(user);
        
        em.getTransaction().commit();
        
        assertThatUserHasBeenAdded();
        
    }

    private void assertThatUserHasBeenAdded() {
        possibleUser = jpaUserRoster.findById(user.getId());
        
        assertThat(possibleUser.get()).isEqualToComparingFieldByField(user);
    }

}
