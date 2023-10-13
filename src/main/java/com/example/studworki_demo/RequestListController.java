package com.example.studworki_demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RequestListController implements Initializable {

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
        for (Request request : LoginController.getLoggedIn().getUsersRequests()) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("request.fxml"));
            AnchorPane requestPane;
            try {
                 requestPane = fxmlLoader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            RequestController requestController = fxmlLoader.getController();
            requestController.setData(request);
            requestPane.getProperties().put("fxmlLoader", fxmlLoader);
            if (column == 1) {
                column = 0;
                row++;
            }
            grid.add(requestPane, column++, row);
            GridPane.setMargin(requestPane, new Insets(10));

        }
    }

    public void AllVacancies(ActionEvent event){
        Main.primaryStage.setScene(Application.mainScene);
    }
    public void savedVacancies(ActionEvent event) throws IOException {
        Application.openSaved();
    }

    @FXML
    public void editProfilePressed(ActionEvent event) throws IOException {
        Application.openProfile();
    }
}
