package com.example.studworki_demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SavedController implements Initializable {

    @FXML
    private Label firstName;

    @FXML
    private GridPane grid;


    @FXML
    private Label secondName;

    public static boolean savedSet = true;

    public void setUser(Account account) {
        firstName.setText(account.getFirstname());
        secondName.setText(account.getLastname());
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<VBox> savedVacancies = new ArrayList<>();
        MainVacancyController mainVacancyController;
        for(VBox vBox : ApplicationController.nodes) {
            FXMLLoader fxmlLoader = (FXMLLoader) vBox.getProperties().get("fxmlLoader");
            if (fxmlLoader != null) {
                mainVacancyController = fxmlLoader.getController();
                Vacancy vacancy = mainVacancyController.getVacancy();
                if(vacancy.isSaved()){
                    savedVacancies.add(mainVacancyController.cloneVBox());
                    }
                }
            }
        grid.getChildren().clear();
        int column = 0;
        int row = 1;
        for (VBox mainVacancyBox : savedVacancies) {
            if (row ==3 ) {
                row = 1;
                column++;
            }
            grid.add(mainVacancyBox, column, row++);
            GridPane.setMargin(mainVacancyBox, new Insets(10));
        }
    }

    public void allVacanciesPressed(ActionEvent ignoredEvent) {
        Main.primaryStage.setScene(Application.mainScene);
        savedSet=false;
    }}



