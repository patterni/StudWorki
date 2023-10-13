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

    private Vacancy vacancy;

    @FXML
    void openSaved(ActionEvent event) throws IOException {
        Application.openSaved();
        SavedController.savedSet=true;
    }


    public void setUser(Account account) {
        firstName.setText(account.getFirstname());
        secondName.setText(account.getLastname());
    }

    @FXML
    public void backPressed(ActionEvent event){
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

    public void savePressed(ActionEvent ignoredEvent){
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

    public void allVacanciesPressed(ActionEvent ignoredEvent) {
        Main.primaryStage.setScene(Application.mainScene);
        SavedController.savedSet=false;
    }

    public void requestPressed(ActionEvent event) throws IOException {
        Application.openRequestScene(vacancy);
    }

    public void requests(ActionEvent event) throws IOException {
        Application.openRequests();
    }

}
