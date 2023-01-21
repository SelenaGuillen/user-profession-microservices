package com.selenaguillen.directory.repositories;

import com.selenaguillen.directory.entities.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @Test
    public void findAll() {
        List<User> users = userRepository.findAll();

        assertEquals(1000, users.size(), "The repository should contain 1000 records.");
    }

    @Test
    public void findById() {
        User expectedUser = new User(222, "Tierney", "Carolin", "Tierney.Carolin@gmail.com",
                "firefighter", Date.valueOf("2020-04-20"), "Equatorial Guinea", "Koror");

        User actualUser = userRepository.findById(222).get();

        assertEquals(expectedUser, actualUser);
    }
}