package com.example.studworki_demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.*;

import static com.example.studworki_demo.Main.accounts;

public class LoginController {

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private TextField userNameTextField;

    @FXML
    private Label loginMessageLabel;

    private static Account loggedIn;

    public void loginButtonOnAction(ActionEvent event) throws IOException {
        if(!userNameTextField.getText().isBlank() && !passwordTextField.getText().isBlank()){
            validateLogin();
        }else {
            loginMessageLabel.setText("Неправильно введено логін або пароль,\nспробуйте ще раз");
        }
    }

    public void validateLogin() throws IOException {
        boolean successLogIn = false;
            for(Account account:accounts){
                if(account.getUsername().equals(userNameTextField.getText())&&account.getPassword().equals(passwordTextField.getText())){
                    loggedIn=account;
                    Application.open(loggedIn);
                    System.out.println(LoginController.getLoggedIn().getUsersSavedVacancies());
                    System.out.println();
                    System.out.println(LoginController.getLoggedIn().getUsersRequests());
                    successLogIn =true;
                    break;
                }
            }
            if(!successLogIn){
                loginMessageLabel.setText("Неправильно введено логін або пароль,\nспробуйте ще раз");
            }
    }

    public static Account getLoggedIn() {
        return loggedIn;
    }

    public void createAccountForm() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("register.fxml"));
            Main.primaryStage.setScene(new Scene(root,520,400));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}