package com.selenaguillen.directory.controller;

import com.selenaguillen.directory.entities.User;
import com.selenaguillen.directory.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//@CrossOrigin(origins = "http://localhost:8081")
@RestController
@Profile("prod")
@RequestMapping("/api")
public class MainController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/users")
    public List<User> getAllUsers() {
        //add try catch later
        List<User> users = new ArrayList<>(userRepository.findAll());
        return users;
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") int id) {
        Optional<User> user = userRepository.findById(id);

        //add if not found logic
        return new ResponseEntity<>(user.get(), HttpStatus.OK);

    }

    @GetMapping("/users/profession/{profession}")
    public List<User> getByProfession(@PathVariable("profession") String profession) {
        List<User> users = userRepository.findByProfession(profession);
        return users;
    }

    @GetMapping("/users/date/{start}/{end}")
    public List<User> getByDateRange(@PathVariable("start") Date start, @PathVariable("end") Date end) {
        List<User> users = userRepository.findByDateRange(start, end);
        return users;
    }

    @GetMapping("/users/country/{country}")
    public List<User> getByCountry(@PathVariable("country") String country) {
        List<User> users = userRepository.findByCountry(country);
        return users;
    }
}
