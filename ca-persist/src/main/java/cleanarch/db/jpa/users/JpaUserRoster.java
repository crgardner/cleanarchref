package cleanarch.db.jpa.users;

import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import cleanarch.domain.users.User;
import cleanarch.domain.users.UserRoster;

public class JpaUserRoster implements UserRoster {

    private EntityManager entityManager;

    @Override
    public void add(User user) {
        entityManager.persist(user);
    }

    @Override
    public List<User> findAll() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public Optional<User> findById(String id) {
        return Optional.ofNullable(entityManager.find(User.class, id));
    }
    
    @PersistenceContext
    public void set(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

}
