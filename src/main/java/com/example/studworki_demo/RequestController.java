package com.example.studworki_demo;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class RequestController {

    @FXML
    private Label requestText;

    @FXML
    private Label typeVacancy;

    @FXML
    private Label vacancySalary;

    @FXML
    private Label vacancyTitle;
    @FXML
    private AnchorPane pane;

    public void setData(Request request){
        requestText.setText("Текст: "+ request.getText());
        typeVacancy.setText("Зайнятість: " +request.getVacancy().getEmploymentType());
        vacancySalary.setText("Зарплатня: " + request.getVacancy().getSalary() + "грн/міс");
        vacancyTitle.setText(request.getVacancy().getJobTitle());
        pane.setStyle("-fx-background-color: #fff;" + "-fx-background-radius: 15;"
                + "-fx-effect: dropShadow(three-pass-box,rgba(0,0,0,0.1),10,0,0,10);");
    }
}