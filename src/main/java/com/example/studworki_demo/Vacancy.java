package com.example.studworki_demo;

public class Vacancy {
    private String jobTitle;
    private String experience;
    private String employmentType;
    private String salary;
    private String description;
    private String city;

    private boolean isSaved = false;
    public Vacancy(String jobTitle, String experience, String employmentType, String salary, String description, String city) {
        this.jobTitle = jobTitle;
        this.experience = experience;
        this.employmentType = employmentType;
        this.salary = salary;
        this.description = description;
        this.city=city;
    }
    public boolean isSaved(){
        return isSaved;
    }

    public void setSaved(boolean state){
        this.isSaved=state;
    }

    @Override
    public String toString() {
        return "Job Title: " + jobTitle + "\nDescription: " + description +
                "\nExperience Required: " + experience + "\nEmployment Type: " + employmentType +
                "\nSalary: " + salary + "\nCity: " +city+ "\n/";
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
