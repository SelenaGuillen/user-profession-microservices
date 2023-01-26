package com.selenaguillen.directory.service;

import com.selenaguillen.directory.entities.User;
import com.selenaguillen.directory.repositories.UserRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface ServiceLayer extends UserRepository {
      List<User> findByProfession(String profession);
      List<User> findByCountry(String country);
      @Query(value="SELECT * FROM User WHERE date_created between :start AND :end", nativeQuery = true)
      List<User> findByDateRange(Date start, Date end);

      List<User> findAllByOrderByDateCreatedAsc();
}
