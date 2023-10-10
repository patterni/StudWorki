package com.example.studworki_demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class VacancyDetailsController {
    @FXML
    private Label firstName;

    @FXML
    private Button offerButton;

    @FXML
    private Button saveButton;

    @FXML
    private Label secondName;

    @FXML
    private Label vacancyCity;

    @FXML
    private Label vacancyDesc;

    @FXML
    private Label vacancyExp;

    @FXML
    private Label vacancySalary;

    @FXML
    private Label vacancyTitle;

    @FXML
    private Label vacancyType;

    @FXML
    void openSaved(ActionEvent event) {

    }

    public void setUser(Account account) {
        firstName.setText(account.getFirstname());
        secondName.setText(account.getLastname());
    }

    public void setVacancyInfo(Vacancy vacancy){
        vacancyTitle.setText(vacancy.getJobTitle());
        vacancyCity.setText("Місто: " + vacancy.getCity());
        vacancyDesc.setText(vacancy.getDescription());
        vacancySalary.setText("Зарплатня: " + vacancy.getSalary() + " грн/міс");
        vacancyType.setText("Зайнятість: " + vacancy.getEmploymentType());
        vacancyExp.setText("Досвід: " + vacancy.getExperience());
    }
}
