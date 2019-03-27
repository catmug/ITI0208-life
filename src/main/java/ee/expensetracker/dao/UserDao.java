package ee.expensetracker.dao;

import ee.expensetracker.model.User;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Primary
@Repository
public class UserDao {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void save(User user) {
        if (user.getUserId() == null) {
            em.persist(user);
        } else {
            em.merge(user);
        }
    }

    public List<User> findAll() {
        return em.createQuery(
                "select u from User u",
                User.class).getResultList();
    }

    public User getUserById(Long id) {
        return em.createQuery(
                "select u from  User u where u.userId = :id",
                User.class).setParameter("id", id).getSingleResult();
    }

    public User findUserByUsername(String username) {
        return em.createQuery(
                "select u from User u where u.username = :username",
                User.class).setParameter("username", username).getSingleResult();
    }
}
