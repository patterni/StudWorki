package com.example.studworki_demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;


public class EmployerWindowController implements Initializable {

        @FXML
        private TextField CityTxt;

        @FXML
        private TextField descTxt;

        @FXML
        private ChoiceBox<String> eploymentChoice;

        @FXML
        private ChoiceBox<String> expChoice;

        private final String[] eploymentOptions = {"Повна", "Часткова", "Віддалено"};
        private final String[] expOptions = {"Без досвіду", "до 1 року"};

        @FXML
        private TextField salaryTxt;

        @FXML
        private TextField titleTxt;

        @FXML
        private Label successLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        eploymentChoice.getItems().addAll(eploymentOptions);
        expChoice.getItems().addAll(expOptions);
    }

    public void donePressed(ActionEvent ignoredEvent) {
        Vacancy vacancy = new Vacancy(titleTxt.getText(),expChoice.getValue(),eploymentChoice.getValue(),salaryTxt.getText(),descTxt.getText(),CityTxt.getText());
        Main.allVacancies.add(vacancy);
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(Main.fileVacancies))) {
            outputStream.writeObject(Main.allVacancies);
        }catch (IOException e){
            e.printStackTrace();
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Main.primaryStage.setScene(Main.scene);
        System.out.println(Main.allVacancies);

    }

    public void backPressed(ActionEvent ignoredEvent){
        Main.primaryStage.setScene(Main.scene);
    }
}


