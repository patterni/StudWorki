package com.example.studworki_demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;


public class EmployerWindowController implements Initializable {

        @FXML
        private TextField CityTxt;

        @FXML
        private Button backButton;

        @FXML
        private TextField descTxt;

        @FXML
        private Button doneButton;

        @FXML
        private ChoiceBox<String> eploymentChoice;

        @FXML
        private ChoiceBox<String> expChoice;

        private  String[] eploymentOptions = {"Повна", "Часткова", "Віддалено"};
        private  String[] expOptions = {"Без досвіду", "до 1 року"};


        @FXML
        private TextField salaryTxt;

        @FXML
        private TextField titleTxt;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        eploymentChoice.getItems().addAll(eploymentOptions);
        expChoice.getItems().addAll(expOptions);
    }


    public void donePressed(ActionEvent event) {
        Vacancy vacancy = new Vacancy(titleTxt.getText(),expChoice.getValue(),eploymentChoice.getValue(),salaryTxt.getText(),descTxt.getText(),CityTxt.getText());
        Main.allVacancies.add(vacancy);
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(Main.fileVacancies))) {
            outputStream.writeObject(Main.allVacancies);
        }catch (IOException e){
            e.printStackTrace();
        }
        System.out.println(Main.allVacancies);
    }
}


