import org.example.userservice.entity.User;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
    @Test
    void testUserConstructorAndGetters() {
        User user = new User("Ivan", "ivan@example.com", 30);
        assertEquals("Ivan", user.getName());
        assertEquals("ivan@example.com", user.getEmail());
        assertEquals(30, user.getAge());
        assertNotNull(user.getCreatedAt());
    }
}

