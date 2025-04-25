package org.example.userservice.dao;

import org.example.userservice.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class UserDaoImpl implements UserDao {
    private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    private final SessionFactory sessionFactory;

    public UserDaoImpl() {
        this.sessionFactory = org.example.userservice.util.HibernateUtil.getSessionFactory();
    }

    public UserDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }



    @Override
    public void save(User user) {
        try (Session session = sessionFactory.openSession()){
            session.beginTransaction().commit();
            session.save(user);
        } catch (Exception e) {
            logger.error("User creation failed.", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public User findById(long id) {
        try (Session session = sessionFactory.openSession()){
            return session.get(User.class, id);
        }
    }

    @Override
    public List<User> findAll() {
        try (Session session = sessionFactory.openSession()){
            return session.createQuery("from User", User.class).list();
        }
    }

    @Override
    public void update(User user) {
        try (Session session = sessionFactory.openSession()){
            session.beginTransaction().commit();
            session.update(user);
        }catch (Exception e) {
            logger.error("User update failed.", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(long id) {
        try (Session session = sessionFactory.openSession()){
           User user = session.get(User.class, id);
           if (user == null) return;
           session.beginTransaction().commit();
           session.delete(user);
        }catch (Exception e) {
            logger.error("Error deleting user.", e);
            throw new RuntimeException(e);
        }
    }
}
