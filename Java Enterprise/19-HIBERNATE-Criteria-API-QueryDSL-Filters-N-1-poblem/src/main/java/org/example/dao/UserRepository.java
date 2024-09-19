package org.example.dao;

import jakarta.persistence.EntityManager;
import org.example.entities.User;
import org.hibernate.SessionFactory;

public class UserRepository extends RepositoryBase<Integer, User>{
    public UserRepository(EntityManager entityManager) {
        super(User.class, entityManager);
    }
}
