package com.example.studworki_demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import java.io.IOException;

public class VacancyDetailsController {
    @FXML
    private Label firstName;

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

    private Vacancy vacancy;

    @FXML
    void openSaved() throws IOException {
        Application.openSaved();
    }


    public void setUser(Account account) {
        firstName.setText(account.getFirstname());
        secondName.setText(account.getLastname());
    }

    @FXML
    public void backPressed(){
        Main.primaryStage.setScene(Application.mainScene);
    }

    public void setVacancyInfo(Vacancy vacancy){
        this.vacancy=vacancy;
        vacancyTitle.setText(vacancy.getJobTitle());
        vacancyCity.setText("Місто: " + vacancy.getCity());
        vacancyDesc.setText(vacancy.getDescription());
        vacancySalary.setText("Зарплатня: " + vacancy.getSalary() + " грн/міс");
        vacancyType.setText("Зайнятість: " + vacancy.getEmploymentType());
        vacancyExp.setText("Досвід: " + vacancy.getExperience());
        boolean isSaved = false;
        for (Vacancy vac : LoginController.getLoggedIn().getUsersSavedVacancies()){
            if (vacancy.equals(vac)) {
                saveButton.setText("Збережено");
                saveButton.setTextFill(Color.DARKRED);
                isSaved =true;
                break;
            }
        }
        if(!isSaved) {
            saveButton.setText("Зберегти");
            saveButton.setTextFill(Color.web("#549fd9"));
        }
    }

    @FXML
    public void savePressed(){
        if(saveButton.getText().equals("Зберегти")){
            LoginController.getLoggedIn().addToUserSavedList(vacancy);
            RegisterController.updateUsersInFile();
            saveButton.setText("Збережено");
            saveButton.setTextFill(Color.DARKRED);
        }else {
            LoginController.getLoggedIn().removeOutUserSavedList(vacancy);
            RegisterController.updateUsersInFile();
            saveButton.setText("Зберегти");
            saveButton.setTextFill(Color.web("#549fd9"));
        }
        System.out.println(LoginController.getLoggedIn().getUsersSavedVacancies());
    }

    @FXML
    public void allVacanciesPressed() throws IOException {
        Application.open(LoginController.getLoggedIn());
    }
    @FXML
    public void requestPressed() throws IOException {
        Application.openRequestScene(vacancy);
    }

    @FXML
    public void openCourses() throws IOException {
        Application.openCourses();
    }

    @FXML
    public void requests() throws IOException {
        Application.openRequests();
    }

}
