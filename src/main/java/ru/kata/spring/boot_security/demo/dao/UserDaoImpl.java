package ru.kata.spring.boot_security.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDaoImpl implements UserDao{
    private final EntityManager entityManager;

    @Autowired
    public UserDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<User> showUsers() {
        return entityManager.createQuery("FROM User").getResultList();
    }

    @Override
    public User showById(long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void saveUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void update(User user, Long id) {
        entityManager.merge(user);
    }

    @Override
    public void delete(Long id) {
        entityManager.remove(entityManager.find(User.class, id));
    }

    @Override
    public Optional<User> findByUserName(String username) {
        return  entityManager.createQuery("Select u from User u left join fetch u.roles where u.username=:username")
                .setParameter("username", username).getResultStream().findFirst();
    }
}
