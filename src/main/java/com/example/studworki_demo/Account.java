package com.example.studworki_demo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Account implements Serializable {
    private String firstname;
    private String lastname;
    private String username;
    private LocalDate dateOfBirth;
    private String password;

    private ArrayList<Vacancy> usersSavedVacancies;
    private ArrayList<Request> usersRequests;

    public Account() {
        // Default constructor
    }

    public Account(String firstname, String lastname, LocalDate dateOfBirth, String password, String username) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.dateOfBirth = dateOfBirth;
        this.password = password;
        this.username=username;
        this.usersSavedVacancies=new ArrayList<>();
        this.usersRequests = new ArrayList<>();
    }

    public void addToUserRequests(Request request){ this.usersRequests.add(request);}

    public void addToUserSavedList(Vacancy vacancy){
        this.usersSavedVacancies.add(vacancy);
    }

    public void removeOutUserSavedList(Vacancy vacancy){
        this.usersSavedVacancies.remove(vacancy);
    }
    public ArrayList<Request> getUsersRequests(){return  usersRequests;}

    public ArrayList<Vacancy> getUsersSavedVacancies(){
        return usersSavedVacancies;
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

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
