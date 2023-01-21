package com.selenaguillen.directory.service;

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
class ServiceLayerTest {

    @Autowired
    ServiceLayer service;

    @Test
    void findByProfession() {
        List<User> developers = service.findByProfession("developer");

        assertEquals(191, developers.size(), "There should be 191 developers.");

        for (User user: developers) {
            assertTrue(user.getProfession().equalsIgnoreCase("developer"), "This list must only include developers.");
        }
    }

    @Test
    void findByDateRange() {
        Date start = Date.valueOf("2020-01-02");
        Date end = Date.valueOf("2020-02-03");
        List<User> usersInRange = service.findByDateRange(start, end);

        assertEquals(40, usersInRange.size(), "There should be 40 users within this date range.");

        for (User user: usersInRange) {
            assertTrue(start.getTime() <= user.getDatecreated().getTime()
                    && user.getDatecreated().getTime() <= end.getTime(), "The date for each user in this list should be within the established range.");
        }

    }

    @Test
    void findByCountry() {
        List<User> usersFromItaly = service.findByCountry("Italy");

        assertEquals(8, usersFromItaly.size(), "There should be 8 users from Italy");

        for (User user: usersFromItaly) {
            assertTrue(user.getCountry().equalsIgnoreCase("Italy"), "This list only contains users from Italy.");
        }
    }
}