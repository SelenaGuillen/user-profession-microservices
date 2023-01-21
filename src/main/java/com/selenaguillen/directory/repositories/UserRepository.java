package com.selenaguillen.directory.repositories;

import com.selenaguillen.directory.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
      List<User> findByProfession(String profession);
      @Query(value="SELECT * FROM User WHERE dateCreated between :start AND :end", nativeQuery = true)
      List<User> findByDateRange(Date start, Date end);
      List<User> findByCountry(String country);
}
