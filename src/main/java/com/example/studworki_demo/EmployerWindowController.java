package com.example.studworki_demo;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;


public class EmployerWindowController implements Initializable {

        @FXML
        private TextField CityTxt;

        @FXML
        private TextArea descTxt;

        @FXML
        private ChoiceBox<String> eploymentChoice;

        @FXML
        private ChoiceBox<String> expChoice;

        private final String[] eploymentOptions = {"Повна", "Часткова", "Віддалено"};
        private final String[] expOptions = {"Без досвіду", "До 1 року"};

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

    @FXML
    public void donePressed() {
        if(eploymentChoice.getValue() == null || expChoice.getValue()==null || titleTxt.getText().isEmpty()) {
            successLabel.setTextFill(Color.RED);
            successLabel.setText("Не всі поля заповнені");
        }else if(!salaryTxt.getText().matches("\\d+")){
            successLabel.setTextFill(Color.RED);
            successLabel.setText("Некоректна зарплатня");
        }else if(!CityTxt.getText().matches("\\D+")){
            successLabel.setTextFill(Color.RED);
            successLabel.setText("Некоректне місто");
        } else if (descTxt.getText().isEmpty()) {
            successLabel.setTextFill(Color.RED);
            successLabel.setText("Порожній опис вакансії");
        }else {
            Vacancy vacancy = new Vacancy(titleTxt.getText(), expChoice.getValue(), eploymentChoice.getValue(), salaryTxt.getText(), descTxt.getText(), CityTxt.getText());
            Main.allVacancies.add(vacancy);
            try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(Main.fileVacancies))) {
                outputStream.writeObject(Main.allVacancies);
            } catch (IOException e) {
                e.printStackTrace();
            }
            successLabel.setTextFill(Color.GREEN);
            successLabel.setText("Вакансію успішно опубліковано!");
            Timeline timeline = new Timeline(new KeyFrame(
                    Duration.seconds(2),
                    e -> Main.primaryStage.setScene(Main.scene)
            ));
            timeline.play();
            System.out.println(Main.allVacancies);
        }
    }

    @FXML
    public void backPressed(){
        Main.primaryStage.setScene(Main.scene);
    }
}


