package com.selenaguillen.directory.service;

import com.selenaguillen.directory.entities.User;
import com.selenaguillen.directory.repositories.UserRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.util.List;

public interface ServiceLayer extends UserRepository {
      List<User> findByProfession(String profession);
      @Query(value="SELECT * FROM User WHERE dateCreated between :start AND :end", nativeQuery = true)
      List<User> findByDateRange(Date start, Date end);
      List<User> findByCountry(String country);
}
