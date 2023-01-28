package com.selenaguillen.directory.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;


import javax.persistence.*;
import java.sql.Date;

@Entity(name="user")
@Data //implicitly has requiredargsconstructor
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    @Column(name="first_name", nullable = false)
    private String firstName;

    @Column(name="last_name", nullable = false)
    private String lastName;
    @Column
    private String email;
    @Column
    private String profession;
    @Column(name="date_created", nullable = false)
    private Date dateCreated;
    @Column
    private String country;
    @Column
    private String city;

}

