package org.example.dao;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.example.entities.PersonalInfo_;
import org.example.entities.User;
import org.example.entities.User_;
import org.hibernate.Session;

import java.util.Collections;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserDao {
    private final static UserDao INSTANCE = new UserDao();

    public List<User> findAll(Session session){
        var builder = session.getCriteriaBuilder();
        var criteria = builder.createQuery(User.class);
        var user = criteria.from(User.class);

        criteria.select(user);

        return session.createQuery(criteria).list();
    }

    public List<User> findAllByFirstName(Session session, String firstName){
        var builder = session.getCriteriaBuilder();
        var criteria = builder.createQuery(User.class);
        var user = criteria.from(User.class);

        criteria.select(user).where(builder.equal(user.get(User_.personalInfo).get(PersonalInfo_.firstname),firstName));

        return session.createQuery(criteria).list();
    }

    public static UserDao getInstance(){
        return INSTANCE;
    }
}
