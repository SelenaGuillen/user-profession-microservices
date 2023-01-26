package com.selenaguillen.directory.controller;

import com.selenaguillen.directory.entities.User;
import com.selenaguillen.directory.service.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/api")
public class MainController {
    @Autowired
    ServiceLayer service;

    @GetMapping("/users")
    public String getAllUsers(Model model) {

        try {
            List<User> users = new ArrayList<>(service.findAll());
            model.addAttribute("users", users);
            return "users";
        } catch (Exception e) {
            return "error"; //return error page and type of error
        }
    }

    @GetMapping("users/{id}")
    public String getUserById(@PathVariable("id") int id, Model model) {
        try {
            User user = service.findById(id).get();
            model.addAttribute("user", user);
            return "user";
        } catch (Exception e) {
            return "error";
        }
    }

    @GetMapping("/users/country/{country}")
    public String getByCountry(@PathVariable("country") String country, Model model) {
        try {
            List<User> users = service.findByCountry(country);
            model.addAttribute("users", users);
            return "users-by-country";
        } catch (Exception e) {
            return "error";
        }
    }

    @GetMapping("/users/date/{start}/{end}")
    public String getByDateRange(@PathVariable("start") Date start, @PathVariable("end") Date end, Model model) {
        try {
            List<User> users = service.findByDateRange(start, end);
            model.addAttribute("users", users);
            return "users-in-date-range";
        } catch (Exception e) {
            return "error";
        }
    }

    @GetMapping("/users/profession/{profession}")
    public String getByProfession(@PathVariable(required = false, name="profession") String profession, Model model) {
        try {
            List<User> users = service.findByProfession(profession);
            model.addAttribute("users", users);
            return "users-by-profession";
        } catch (Exception e) {
            return "error";
        }
    }
    @GetMapping("/users/id")
    public String searchById(@RequestParam(name="id", defaultValue = "9999") int id, Model model) {
        User user = service.findById(id).get();
        model.addAttribute("user", user);
        return "user";
    }
    @GetMapping("search/profession")
    public String searchById(@RequestParam(name="profession", defaultValue = "worker") String profession, Model model) {
      List<User> users = service.findByProfession(profession);
      model.addAttribute("users", users);
      return "users-by-profession";
    }



    //TEST FILTER AND SORT

    @GetMapping("/users/search/onlydoctorsanddevs/{profession}/{profession1}")
    public String searchByDocAndDev(@PathVariable(required = false, name="profession") String profession,
                                    @PathVariable(required = false, name="profession1") String profession1,
                                    Model model) {
        List<User> allUsers = service.findAll();
        List<User> users = allUsers.stream()
                .filter(user -> user.getProfession().equalsIgnoreCase(profession) || user.getProfession().equalsIgnoreCase(profession1))
                .collect(Collectors.toList());

        model.addAttribute("users", users);
        return "users-by-profession";
    }

    //SORT BY DATE
    @GetMapping("/users/sort")
    public String searchByDocAndDev(Model model) {

        List<User> users = service.findAllByOrderByDateCreatedAsc();

        model.addAttribute("users", users);
        return "users";
    }



}
