package com.selenaguillen.directory.controller;

import com.selenaguillen.directory.entities.User;
import com.selenaguillen.directory.service.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

//TODO: possible implementation: keep track of current list to make it more efficient to filter through, make filters toggleable
//TODO: add remove and create pages & functionality
@Controller
@RequestMapping("/api")
public class MainController {
    @Autowired
    ServiceLayer service;
    Set<String> professions;
    Set<String> countries;
    @ModelAttribute
    public void preLoad(Model model){
        professions = new HashSet<>();
        List<User> users = service.findAll();

        for(User user: users) {
            professions.add(user.getProfession());
        }

        countries = new HashSet<>();

        for(User user: users) {
            countries.add(user.getCountry());
        }
    }

    @GetMapping("/users")
    public String getAllUsers(Model model) {

        try {
            List<User> users = new ArrayList<>(service.findAll());
            model.addAttribute("users", users);
            model.addAttribute("professions", professions);
            model.addAttribute("countries", countries);
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

    //TODO: this should be able to be accomplish on every page after being filtered
    @GetMapping("/users/sort")
    public String searchByDocAndDev(Model model) {

        List<User> users = service.findAllByOrderByDateCreatedAsc();

        model.addAttribute("users", users);
        return "users";
    }


}
