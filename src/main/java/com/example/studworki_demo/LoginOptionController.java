package com.example.studworki_demo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

public class LoginOptionController {

    @FXML
    public void studentPressed() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 520, 400);
        Main.primaryStage.setScene(scene);
    }

    @FXML
    public void employerPressed() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("employerWindow.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Main.primaryStage.setScene(scene);
    }
}
