package com.example.studworki_demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;

public class EditProfileController {

    @FXML
    private Label firstName;

    @FXML
    private TextField name;

    @FXML
    private DatePicker newDate;

    @FXML
    private PasswordField newPassword;

    @FXML
    private PasswordField newPasswordConfirm;

    @FXML
    private PasswordField oldPassword;

    @FXML
    private Label secondName;

    @FXML
    private Label warningText;

    @FXML
    private TextField surname;

    @FXML
    private TextField username;
    @FXML
    private Label welcomeTxt;

    @FXML
    void allVacanciesPressed(ActionEvent event) throws IOException {
        Application.open(LoginController.getLoggedIn());
    }

    @FXML
    void openSaved(ActionEvent event) throws IOException {
        Application.openSaved();
    }

    @FXML
    void requests(ActionEvent event) throws IOException {
        Application.openRequests();
    }

    public void setCurrentProfileInfo(Account account){
        firstName.setText(account.getFirstname());
        secondName.setText(account.getLastname());
        name.setText(account.getFirstname());
        surname.setText(account.getLastname());
        username.setText(account.getUsername());
        welcomeTxt.setText("Вітаємо, "+ LoginController.getLoggedIn().getFirstname());
    }

    @FXML
    public void savePressed(ActionEvent event){
        warningText.setText("");
        boolean success = true;
        for(Account account : Main.accounts) {
            if(account.getUsername().equals(username.getText())){
                if(account.equals(LoginController.getLoggedIn()))
                    break;
                warningText.setTextFill(Color.RED);
                warningText.setText("Username already used");
                success =false;
                break;
            }
        }
        if(username.getText().equals(LoginController.getLoggedIn().getUsername())){
            success=true;
        }
        LocalDate currentDate = LocalDate.now();

        if(newDate.getValue()!=null) {
            Period age = Period.between(newDate.getValue(), currentDate);
            if (age.getYears() < 16) {
                success = false;
                warningText.setTextFill(Color.RED);
                warningText.setText("Ви занадто молоді");
            }
        }

        if(success) {
            Account currentProfile = LoginController.getLoggedIn();
            currentProfile.setFirstname(name.getText());
            currentProfile.setLastname(surname.getText());
            if(newDate.getValue()!=null) {
                currentProfile.setDateOfBirth(newDate.getValue());
            }
            currentProfile.setUsername(username.getText());
            RegisterController.updateUsersInFile();
            firstName.setText(currentProfile.getFirstname());
            secondName.setText(currentProfile.getLastname());
            warningText.setTextFill(Color.GREEN);
            welcomeTxt.setText("Вітаємо, "+ LoginController.getLoggedIn().getFirstname());
            warningText.setText("Зміни успішно збережені");
        }
    }

    @FXML
    public void changePasswordPressed(ActionEvent event){
        warningText.setText("");
        Account currentProfile = LoginController.getLoggedIn();

        if(!oldPassword.getText().equals(currentProfile.getPassword())){
            warningText.setTextFill(Color.RED);
            warningText.setText("Хибний поточний пароль");
        }else if(!newPassword.getText().equals(newPasswordConfirm.getText())){
            warningText.setTextFill(Color.RED);
            warningText.setText("Пароль непідтверджено");
        }else if (newPassword.getText().equals(newPasswordConfirm.getText()) && !newPassword.getText().isEmpty()){
            warningText.setTextFill(Color.GREEN);
            warningText.setText("Пароль успішно змінено");
            currentProfile.setPassword(newPassword.getText());
            RegisterController.updateUsersInFile();
        }
    }
}
