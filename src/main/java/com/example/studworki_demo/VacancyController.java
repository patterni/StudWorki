package com.example.studworki_demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class VacancyController {
    @FXML
    private Label city;

    @FXML
    private Label salary;

    @FXML
    private Label title;
    @FXML
    private VBox box;

    private Vacancy vacancy;
    public void setData(Vacancy vacancy){
        this.vacancy=vacancy;
        title.setText(vacancy.getJobTitle());
        if(!vacancy.getSalary().equals("Договірна")){
            salary.setText(vacancy.getSalary() + " грн/міс");
        }else
            salary.setText(vacancy.getSalary());
        city.setText(vacancy.getCity());
        box.setStyle("-fx-background-color: #8ECDDD;"+"-fx-background-radius: 15;"
                +"-fx-effect: dropShadow(three-pass-box,rgba(0,0,0,0.1),10,0,0,10);");

    }

    @FXML
    public void detailsPressed(ActionEvent event) throws IOException {
        Application.openVacancyDetails(vacancy);
    }


}
