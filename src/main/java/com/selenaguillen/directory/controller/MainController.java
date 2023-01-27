package com.selenaguillen.directory.controller;

import com.selenaguillen.directory.entities.User;
import com.selenaguillen.directory.service.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//TODO: possible implementation: keep track of current list to make it more efficient to filter through, make filters toggleable
//TODO: add remove and create pages & functionality
@Controller
@RequestMapping("/api")
public class MainController {
    @Autowired
    ServiceLayer service;
    List<User> users;
    Set<String> professions;
    Set<String> countries;
    List<String> sortingMethods;
    @ModelAttribute
    public void preLoad(Model model){
        professions = new HashSet<>();
        users = service.findAll();

        for(User user: users) {
            professions.add(user.getProfession());
        }

        countries = new HashSet<>();

        for(User user: users) {
            countries.add(user.getCountry());
        }

        sortingMethods = new ArrayList<>();
        sortingMethods.add("First Name");
        sortingMethods.add("Last Name");
        sortingMethods.add("Date - Ascending");
        sortingMethods.add("Date - Descending");
        sortingMethods.add("ID");
    }


    @GetMapping("/users")
    public String getAllUsers(Model model) {

        try {
            model.addAttribute("users", users);
            model.addAttribute("professions", professions);
            model.addAttribute("countries", countries);
            model.addAttribute("sortingMethods", sortingMethods);
            return "users";
        } catch (Exception e) {
            return "error";
        }
    }

//    REQUIRED ENDPOINTS w/ ResponseBody and status codes
    @GetMapping("users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") int id) {
        try {
            User user = service.findById(id).get();
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/users/country/{country}")
    public ResponseEntity<List<User>> getUsersByCountry(@PathVariable("country") String country) {
        try {
            users = service.findByCountry(country);
            if (users.size() == 0) {
                throw new Exception();
            }
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/users/date/{start}/{end}")
    public ResponseEntity<List<User>> getUsersByDateRange(@PathVariable("start") Date start, @PathVariable("end") Date end) {
        try {
            if (end.before(start)) {
                throw new Exception();
            }
            users = service.findByDateRange(start, end);
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            //422 Unprocessable Entity response status code indicates that
            // the server understands the content type of the request entity,
            // and the syntax of the request entity is correct, but it was unable to process the contained instructions.
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @GetMapping("/users/profession/{profession}")
    public ResponseEntity<List<User>> getUsersByProfession(@PathVariable(required = false, name="profession") String profession) {
        try {
            users = service.findByProfession(profession);
            if (users.size() == 0) {
                throw new Exception();
            }
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //REQUIRED ENDPOINTS W/ REQUEST PARAMS FOR UI//
    @GetMapping("/users/id")
    public String searchById(@RequestParam(name="id", defaultValue = "9999") int id, Model model) {
        try {
            User user = service.findById(id).get();
            model.addAttribute("user", user);
            return "user";
        } catch (Exception e) {
            return "error";
        }

    }

    @GetMapping("/users/profession")
    public String dropDownProfession(@RequestParam(required = false, name="profession") String profession, Model model) {
        try {
            users = service.findByProfession(profession);
            if (users.size() == 0) {
                throw new Exception();
            }
            model.addAttribute("users", users);
            return "users-by-profession";
        } catch (Exception e) {
            return "error";
        }
    }

    @GetMapping("/users/country")
    public String dropDownCountry(@RequestParam(required = false, name="country") String country, Model model) {
        try {
            users = service.findByCountry(country);
            if (users.size() == 0) {
                throw new Exception();
            }
            model.addAttribute("users", users);
            return "users-by-country";
        } catch (Exception e) {
            return "error";
        }
    }

    //SORTING METHODS//
    //TODO: GET /users?sort_by=asc(date) and GET /users?sort_by=desc(date) use this format
    @GetMapping("/users/sort/date/asc")
    public String sortByDateAsc(Model model) {
            users = service.findAllByOrderByDateCreatedAsc();
            model.addAttribute("users", users);
            return "sorted-users";
    }

    @GetMapping("/users/sort/date/desc")
    public String sortByDateDesc(Model model) {
        users = service.findAllByOrderByDateCreatedDesc();
        model.addAttribute("users", users);
        return "sorted-users";
    }

    @GetMapping("/users/sort/name/first")
    public String sortByFirstName(Model model) {
        users = service.findAllByOrderByFirstNameAsc();
        model.addAttribute("users", users);
        return "sorted-users";
    }
    @GetMapping("/users/sort/name/last")
    public String sortByLastName(Model model) {
        users = service.findAllByOrderByLastNameAsc();
        model.addAttribute("users", users);
        return "sorted-users";
    }
    @GetMapping("/users/date")
    public String filterByDateRange(@RequestParam("start") Date start,
                                    @RequestParam("end") Date end, Model model)
    {
        try {
            if (end.before(start)) {
                throw new Exception();
            }
            users = service.findByDateRange(start, end);
            model.addAttribute("users", users);
            return "users-in-date-range";
        } catch (Exception e) {
            return "invalid-date-range";
        }
    }

}
