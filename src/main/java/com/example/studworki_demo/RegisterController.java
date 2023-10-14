package com.example.studworki_demo;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
    private TextField confirmPasswordTextField;
    @FXML
    private TextField firstnameTextField;
    @FXML
    private TextField lastnameTextField;
    @FXML
    private DatePicker datePicker;
    @FXML
    private TextField passwordTextField;
    @FXML
    private Label registerMessage;
    @FXML
    private TextField usernameTextField;


    private String firstname;
    private String lastname;
    private LocalDate dateOfBirth;
    private String password;
    private String username;

    @FXML
    public void registerButtonOnAction() {
        firstname = firstnameTextField.getText();
        lastname = lastnameTextField.getText();
        dateOfBirth = datePicker.getValue();
        password = passwordTextField.getText();
        username = usernameTextField.getText();

        LocalDate currentDate = LocalDate.now();
        Period age = null;
        if(datePicker.getValue()!=null)
            age = Period.between(dateOfBirth,currentDate);

        boolean uniqueUsername = true;
        for(Account account:accounts){
            if(account.getUsername().equals(username)) {
                uniqueUsername = false;
                break;
            }
        }

        if(firstnameTextField.getText().isEmpty() || lastnameTextField.getText().isEmpty() ||
                usernameTextField.getText().isEmpty() || passwordTextField.getText().isEmpty()
                || datePicker.getValue()==null || confirmPasswordTextField.getText().isEmpty())     {
            registerMessage.setText("Не всі поля заповнені!");
        }else if(age.getYears()<16) {
            registerMessage.setText("Ви занадто молоді!");
        }else if(!passwordTextField.getText().equals(confirmPasswordTextField.getText())) {
            registerMessage.setText("Паролі не співпадають!");
        } else if (!uniqueUsername) {
            registerMessage.setText("Username вже використовується");
        }else {
            registerUser(file);
            registerMessage.setText("Success, now you can login!");
            Timeline timeline = new Timeline(new KeyFrame(
                    Duration.seconds(2),
                    e -> {
                        Main.primaryStage.setScene(scene);
                    }
            ));
            timeline.play();
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

    public static void updateUsersInFile(){
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file))) {
            outputStream.writeObject(accounts);
            System.out.println("Account updated successfully.");
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    @FXML
    public void backButtonOnAction(){
        Main.primaryStage.setScene(scene);
    }
}