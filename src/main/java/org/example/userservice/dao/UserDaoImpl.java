package org.example.userservice.dao;

import org.example.userservice.entity.User;
import org.example.userservice.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class UserDaoImpl implements UserDao {
    private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);


    @Override
    public void save(User user) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            tx = session.beginTransaction();
            session.save(user);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            logger.error("ошибка при создании пользователя", e);
        }
    }

    @Override
    public User findById(long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            return session.get(User.class, id);
        }
    }

    @Override
    public List<User> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            return session.createQuery("from User", User.class).list();
        }
    }

    @Override
    public void update(User user) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            tx = session.beginTransaction();
            session.update(user);
            tx.commit();
        }catch (Exception e) {
            if (tx != null) tx.rollback();
            logger.error("Ошибка при изменении пользователя", e);
        }
    }

    @Override
    public void delete(long id) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            tx = session.beginTransaction();
            User user = session.get(User.class, id);
            if (user != null) {
                session.delete(user);
            }
            tx.commit();
        }catch (Exception e) {
            if (tx != null) tx.rollback();
            logger.error("Ошибка при удалени пользователя", e);
        }
    }
}
