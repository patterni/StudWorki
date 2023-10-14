package com.example.studworki_demo;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.io.IOException;

public class AddRequestController {

    @FXML
    private Label firstName;

    @FXML
    private TextArea requestText;

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
    private Label warningText;

    @FXML
    void allVacanciesPressed() throws IOException {
        Application.open(LoginController.getLoggedIn());
    }

    @FXML
    void savedVacanciesPressed() throws IOException {
        Application.openSaved();
    }
    @FXML
    public void openCourses() throws IOException {
        Application.openCourses();
    }

    @FXML
    void requestsPressed() throws IOException {
        Application.openRequests();
    }

    @FXML
    void backPressed() throws IOException {
        Application.openVacancyDetails(vacancy);
    }



    public void setUser(Account account) {
        firstName.setText(account.getFirstname());
        secondName.setText(account.getLastname());
    }

    public void setVacancyInfo(Vacancy vacancy) {
        this.vacancy = vacancy;
        vacancyTitle.setText(vacancy.getJobTitle());
        vacancyCity.setText("Місто: " + vacancy.getCity());
        vacancyDesc.setText(vacancy.getDescription());
        vacancySalary.setText("Зарплатня: " + vacancy.getSalary() + " грн/міс");
        vacancyType.setText("Зайнятість: " + vacancy.getEmploymentType());
        vacancyExp.setText("Досвід: " + vacancy.getExperience());
    }

    @FXML
    public void confirmPressed(){
        warningText.setText("");
        if(!requestText.getText().isEmpty()) {
            Request request = new Request(vacancy, requestText.getText());
            LoginController.getLoggedIn().addToUserRequests(request);
            RegisterController.updateUsersInFile();
            System.out.println(LoginController.getLoggedIn().getUsersRequests());
            Main.primaryStage.setScene(Application.mainScene);
        }else
            warningText.setText("Заявка порожня");
    }


}