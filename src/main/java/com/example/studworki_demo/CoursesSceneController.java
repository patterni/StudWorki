package com.example.studworki_demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CoursesSceneController implements Initializable {

    @FXML
    private Label firstName;

    @FXML
    private Label secondName;

    @FXML
    private WebView webView;

    private WebEngine engine;

    @FXML
    void openAllVacancies() throws IOException {
        Application.open(LoginController.getLoggedIn());
    }

    @FXML
    void openProfileInfo() throws IOException {
        Application.openProfile();
    }

    @FXML
    void openRequestsScene() throws IOException {
        Application.openRequests();
    }

    @FXML
    void openSaved() throws IOException {
        Application.openSaved();
    }


    public void setUser(Account account) {
        firstName.setText(account.getFirstname());
        secondName.setText(account.getLastname());
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        engine= webView.getEngine();
        engine.load("https://ed-era.com/courses/");
    }



}
