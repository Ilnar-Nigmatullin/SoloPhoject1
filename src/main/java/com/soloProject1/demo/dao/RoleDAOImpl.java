package com.soloProject1.demo.dao;


import com.soloProject1.demo.model.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class RoleDAOImpl implements RoleDAO {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Role getRoleByName(String roleName) {
        return (Role) entityManager.createQuery("FROM Role where name= :name")
                .setParameter("name", roleName).getSingleResult();
    }
}
