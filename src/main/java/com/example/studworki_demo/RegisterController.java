package com.example.studworki_demo;

import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.Duration;


import java.io.*;
import java.time.LocalDate;
import java.time.Period;


import static com.example.studworki_demo.Main.*;

public class RegisterController {

    @FXML
    private Button backButton;
    @FXML
    private TextField confirmPasswordTextField;
    @FXML
    private TextField firstnameTextField;
    @FXML
    private TextField lastnameTextField;
    @FXML
    private DatePicker datePicker;
    @FXML
    private Label passwordMessage;
    @FXML
    private Label dateMessage;
    @FXML
    private TextField passwordTextField;
    @FXML
    private Button registerButton;
    @FXML
    private Label registerMessage;
    @FXML
    private TextField usernameTextField;

    @FXML
    private Label usrnMessage;


    String firstname;
    String lastname;
    LocalDate dateOfBirth;
    String password;
    String username;
    public void registerButtonOnAction(ActionEvent event) {
        firstname = firstnameTextField.getText();
        lastname = lastnameTextField.getText();
        dateOfBirth = datePicker.getValue();
        password = passwordTextField.getText();
        username = usernameTextField.getText();
        if(passwordTextField.getText().equals(confirmPasswordTextField.getText())){
            LocalDate currentDate = LocalDate.now();
            Period age = Period.between(dateOfBirth,currentDate);
            boolean uniqeUsername = true;
            if(age.getYears()>=16) {
                for(Account account:accounts){
                    if(username.equals(account.getUsername())){
                        uniqeUsername = false;
                        usrnMessage.setText("This username is already in use");
                        break;
                    }
                }
                if(uniqeUsername) {
                    registerUser(file);
                    registerMessage.setText("Success, now you can login!");
                    Timeline timeline = new Timeline(new KeyFrame(
                            Duration.seconds(2),
                            e -> {
                                Main.primaryStage.setScene(scene);
                            }
                    ));
                    timeline.play();
                }}else
                dateMessage.setText("You too young!");
         }else {
            passwordMessage.setText("Password doesn't match!");
        }
    }
    public void registerUser(File file) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file))) {
            Account account = new Account(firstname, lastname, dateOfBirth, password, username);
            accounts.add(account);
            outputStream.writeObject(accounts);
            System.out.println("Account registered successfully.");
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    public void backButtonOnAction(ActionEvent e){
        Main.primaryStage.setScene(scene);
    }
}