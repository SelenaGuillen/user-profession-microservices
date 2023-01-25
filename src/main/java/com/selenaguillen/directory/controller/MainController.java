package com.selenaguillen.directory.controller;

import com.selenaguillen.directory.entities.User;
import com.selenaguillen.directory.service.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/api")
public class MainController {
    @Autowired
    ServiceLayer service;

    @GetMapping("/users/all")
    public String getAllUsers(Model model) {

        try {
            List<User> users = new ArrayList<>(service.findAll());
            model.addAttribute("users", users);
            return "users";
        } catch (Exception e) {
            return "error"; //return error page and type of error
        }
    }

    @GetMapping("/id")
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
    @GetMapping("search/profession")
    public String searchById(@RequestParam(name="profession", defaultValue = "worker") String profession, Model model) {
      List<User> users = service.findByProfession(profession);
      model.addAttribute("users", users);
      return "users-by-profession";
    }


}
