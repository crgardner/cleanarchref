package cleanarch.db.jpa.users;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import cleanarch.domain.users.User;
import cleanarch.domain.users.UserRoster;

public class JpaUserRoster implements UserRoster {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void add(User user) {
		entityManager.persist(user);
	}

}
