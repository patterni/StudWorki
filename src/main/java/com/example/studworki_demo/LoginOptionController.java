package com.example.studworki_demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

public class LoginOptionController {

    public void studentPressed(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 520, 400);
        Main.primaryStage.setScene(scene);
    }

    public void employerPressed(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("employerWindow.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Main.primaryStage.setScene(scene);
    }
}
