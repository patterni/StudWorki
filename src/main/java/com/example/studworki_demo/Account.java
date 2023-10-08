package com.example.studworki_demo;

import java.io.Serializable;
import java.time.LocalDate;

public class Account implements Serializable {
    String firstname;
    String lastname;
    String username;
    LocalDate dateOfBirth;
    String password;
    public Account() {
        // Default constructor
    }

    public Account(String firstname, String lastname, LocalDate dateOfBirth, String password, String username) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.dateOfBirth = dateOfBirth;
        this.password = password;
        this.username=username;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

}
