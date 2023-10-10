package com.example.studworki_demo;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class Vacancy implements Serializable {
    @Serial
    private static final long serialVersionUID = -7569056061404265603L;
    private String jobTitle;
    private String experience;
    private String employmentType;
    private String salary;
    private String description;
    private String city;
    private boolean isSaved;
    public Vacancy(){

    }


    public Vacancy(String jobTitle, String experience, String employmentType, String salary, String description, String city) {
        this.jobTitle = jobTitle;
        this.experience = experience;
        this.employmentType = employmentType;
        this.salary = salary;
        this.description = description;
        this.city=city;
        this.isSaved = false;
    }
    public boolean isSaved(){
        return isSaved;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vacancy vacancy = (Vacancy) o;
        return  Objects.equals(jobTitle, vacancy.jobTitle)
                && Objects.equals(experience, vacancy.experience) && Objects.equals(employmentType, vacancy.employmentType)
                && Objects.equals(salary, vacancy.salary) && Objects.equals(description, vacancy.description)
                && Objects.equals(city, vacancy.city);
    }



    public void setSaved(boolean state){
        this.isSaved=state;
    }

    @Override
    public String toString() {
        return "Job Title: " + jobTitle + "\nDescription: " + description +
                "\nExperience Required: " + experience + "\nEmployment Type: " + employmentType +
                "\nSalary: " + salary + "\nCity: " +city;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getEmploymentType() {
        return employmentType;
    }

    public void setEmploymentType(String employmentType) {
        this.employmentType = employmentType;
    }

    public String getSalary() {
        return salary;
    }



    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
