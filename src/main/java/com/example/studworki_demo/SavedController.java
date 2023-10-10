package com.example.studworki_demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
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
//        ArrayList<VBox> savedVacancies = new ArrayList<>();
//        for(VBox vBox : ApplicationController.nodes) {
//            MainVacancyController mainVacancyController= ApplicationController.getVacancyControllerOutVBox(vBox);
//            if(mainVacancyController.getVacancy().isSaved()){
//                savedVacancies.add(mainVacancyController.cloneVBox());
//            }
//        }
            int column = 0;
            int row = 1;
        for(Vacancy vacancy: LoginController.getLoggedIn().getUsersSavedVacancies()) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("mainVacancy.fxml"));
            VBox mainVacancyBox = null;
            try {
                mainVacancyBox = fxmlLoader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            MainVacancyController mainVacancyController = fxmlLoader.getController();
            mainVacancyController.setData(vacancy);
            mainVacancyBox.getProperties().put("fxmlLoader", fxmlLoader);
            if (row == 3) {
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



