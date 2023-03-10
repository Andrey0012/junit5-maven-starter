package com.dmdv.junet.service;

import com.dmdv.junet.dto.User;
import org.junit.jupiter.api.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
 class UserServiceTest {
     private UserService userService;
    User IVAN = User.of(1, "Ivan", "123");
    User PETR = User.of(2, "Petr", "456");



    @BeforeAll
     void init () {
         System.out.println("Before all: "+this);
     }

     @BeforeEach
     void prepare () {
         System.out.println("Before each: " + this);
         userService = new UserService();

     }
    @Test
    void userEmptyIfNoUsersAdded () {
        System.out.println("Test 1: " + this);
        List<User> all = userService.getAll();
        assertTrue(all.isEmpty());
    }
    @Test
     void usersSizeIfUserAdded () {
        System.out.println("Test 2: " + this);
        userService.add(IVAN);
        userService.add(PETR);
        List<User> all = userService.getAll();
        assertEquals(2, all.size());

    }
    @Test
    void loginSuccessIfUserExists () {
        userService.add(IVAN);
        Optional<User> user = userService.login(IVAN.getUsername(), IVAN.getPassword());
        assertTrue(user.isPresent());
        user.ifPresent(user1 -> assertEquals(IVAN, user1));
    }
    @Test
    void loginPasswordNotCorrect () {
        userService.add(IVAN);
        Optional<User> optionalUser = userService.login(IVAN.getUsername(), "oooooo");
        assertTrue(optionalUser.isEmpty());
    }
    @Test
    void loginNameNotCorrect () {
        userService.add(IVAN);
        Optional<User> optionalUser = userService.login("ppppp", IVAN.getPassword());
        assertTrue(optionalUser.isEmpty());
    }

    @AfterEach
     void deleteBase () {
        System.out.println("After each: " + this);
    }
    @AfterAll
    void closeEnd () {
        System.out.println("After all: "+this);

    }
}
