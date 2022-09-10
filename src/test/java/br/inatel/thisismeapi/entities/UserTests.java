package br.inatel.thisismeapi.entities;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserTests {

    @Test
    public void testIfTwoUsersWithTheSameEmailIsTheSameUser(){
        String email = "test@email.com";
        User user1 = new User();
        user1.setEmail(email);

        User user2 = new User();
        user2.setEmail(email);
        boolean result = user1.equals(user2);
        assertTrue(result);
    }


    @Test
    public void testIfTwoUsersWithDifferentEmailIsNotTheSameUser(){
        String email = "test@email.com";
        User user1 = new User();
        user1.setEmail(email);

        User user2 = new User();
        user2.setEmail("test2@email.com");
        boolean result = user1.equals(user2);
        assertFalse(result);
    }

    @Test
    public void testIfIstheSameObjectUser(){
        String email = "test@email.com";
        User user1 = new User();
        user1.setEmail(email);

        boolean result = user1.equals(user1);

        assertTrue(result);
    }

    @Test
    public void testUserIsNotEqualNull(){
        String email = "test@email.com";
        User user1 = new User();
        user1.setEmail(email);

        boolean result = user1.equals(null);

        assertFalse(result);
    }

    @Test
    public void testUserIsNotEqualADifferentObject(){
        String email = "test@email.com";
        User user1 = new User();
        user1.setEmail(email);

        String anotherClass = new String("Test");


        boolean result = user1.equals(anotherClass);

        assertFalse(result);
    }

    @Test
    public void testUserGetId(){
        User user1 = new User();

        assertNull(user1.getId());
    }
}
