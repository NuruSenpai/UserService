import org.example.userservice.dao.UserDao;
import org.example.userservice.dao.UserDaoImpl;
import org.example.userservice.entity.User;
import org.example.userservice.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestUserDaoImpl {
    private SessionFactory testSessionFactory;
    private UserDao userDao;

    @BeforeAll
    void setup() {
        testSessionFactory = HibernateUtil.getTestSessionFactory();
        userDao = new UserDaoImpl(testSessionFactory);
    }


    @Test
    void testSaveAndFind() {
        User user = new User("Alice", "alice@mail.com", 22);
        userDao.save(user);

        List<User> users = userDao.findAll();
        assertEquals(1, users.size());

        User retrieved = users.get(0);
        assertEquals("Alice", retrieved.getName());
    }

    @Test
    void testUpdate() {
        User user = new User("Bob", "bob@mail.com", 30);
        userDao.save(user);

        user.setAge(31);
        userDao.update(user);

        User updated = userDao.findById(user.getId());
        assertEquals(31, updated.getAge());
    }

    @Test
    void testDelete() {
        User user = new User("Charlie", "charlie@mail.com", 28);
        userDao.save(user);
        userDao.delete(user.getId());

        assertNull(userDao.findById(user.getId()));
    }

    @AfterEach
    void clearDatabase() {
        try (Session session = testSessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.createQuery("delete from User").executeUpdate();
            tx.commit();
        }
    }
}
