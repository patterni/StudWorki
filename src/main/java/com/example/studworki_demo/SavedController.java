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
import java.util.ResourceBundle;

public class SavedController implements Initializable {

    @FXML
    private Label firstName;

    @FXML
    private GridPane grid;

    @FXML
    private Label secondName;


    public void setUser(Account account) {
        firstName.setText(account.getFirstname());
        secondName.setText(account.getLastname());
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
            int column = 0;
            int row = 1;
        for(Vacancy vacancy: LoginController.getLoggedIn().getUsersSavedVacancies()) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("mainVacancy.fxml"));
            VBox mainVacancyBox;
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


    @FXML
    public void allVacanciesPressed() throws IOException {
        Application.open(LoginController.getLoggedIn());
    }
    @FXML
    public void requestsPressed() throws IOException {
        Application.openRequests();
    }

    @FXML
    public void editProfilePressed() throws IOException {
        Application.openProfile();
    }

    @FXML
    public void openCourses() throws IOException {
        Application.openCourses();
    }
}



