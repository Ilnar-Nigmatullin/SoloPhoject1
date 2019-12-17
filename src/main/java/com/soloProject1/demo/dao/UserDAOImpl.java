package com.soloProject1.demo.dao;

import com.soloProject1.demo.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public List<User> readUsers() {
        return entityManager.createQuery("FROM User").getResultList();
    }

    public void addUser(User user) {
        entityManager.persist(user);
    }

    public void editUser(User user) {
        entityManager.merge(user);
    }

    public void deleteUser(User user) {
        entityManager.remove(user);
    }

    public User findUserById(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public User findByUserName(String userName) {
        User user = null;
        try {
            user = (User) entityManager.createQuery("from User where username= :username")
                    .setParameter("username", userName).getSingleResult();
        } catch (NoResultException nre) {
            nre.printStackTrace();
        }
        return user;
    }
}
