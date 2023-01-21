package com.selenaguillen.directory.controller;

import com.selenaguillen.directory.entities.User;
import com.selenaguillen.directory.service.ServiceLayer;
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

@RestController
@Profile("prod")
@RequestMapping("/api")
public class MainController {
    @Autowired
    ServiceLayer service;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        try {
            List<User> users = new ArrayList<>(service.findAll());
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") int id) {
        try {
            Optional<User> user = service.findById(id);
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

    }


    @GetMapping("/users/profession/{profession}")
    public ResponseEntity<List<User>> getByProfession(@PathVariable("profession") String profession) {
        try {
            List<User> users = new ArrayList<>(service.findByProfession(profession));
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/users/date/{start}/{end}")
    public ResponseEntity<List<User>> getByDateRange(@PathVariable("start") Date start, @PathVariable("end") Date end) {
        try {
            List<User> users = service.findByDateRange(start, end);
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/users/country/{country}")
    public ResponseEntity<List<User>> getByCountry(@PathVariable("country") String country) {
        try {
            List<User> users = service.findByCountry(country);
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

    }

}
