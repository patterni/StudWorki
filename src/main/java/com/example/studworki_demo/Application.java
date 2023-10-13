package com.example.studworki_demo;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;


public class Application {
    public static Scene mainScene;
    public static Scene saveScene;
    public static void open(Account account) throws IOException {
        FXMLLoader loader = new FXMLLoader(Application.class.getResource("application.fxml"));
        Parent root = loader.load();
        // Access the controller of the loaded FXML
        ApplicationController controller = loader.getController();
        mainScene = new Scene(root);
        // Set the user data in the controller
        controller.setUser(account);
        Main.primaryStage.setScene(mainScene);
        Main.primaryStage.setMaximized(true);
    }


    public static void openSaved() throws IOException {
        FXMLLoader loader = new FXMLLoader(Application.class.getResource("savedVacancies.fxml"));
        Parent root = loader.load();
        saveScene = new Scene(root);
        SavedController controller = loader.getController();
        controller.setUser(LoginController.getLoggedIn());
        Main.primaryStage.setScene(saveScene);
    }

    public static void openVacancyDetails(Vacancy vacancy) throws IOException {
        FXMLLoader loader = new FXMLLoader(Application.class.getResource("vacancyDetails.fxml"));
        Parent root = loader.load();
        VacancyDetailsController controller = loader.getController();
        controller.setUser(LoginController.getLoggedIn());
        controller.setVacancyInfo(vacancy);
        Main.primaryStage.setScene(new Scene(root));
    }

    public static void openRequestScene(Vacancy vacancy) throws IOException{
        FXMLLoader loader = new FXMLLoader(Application.class.getResource("addRequest.fxml"));
        Parent root = loader.load();
        AddRequestController controller = loader.getController();
        controller.setUser(LoginController.getLoggedIn());
        controller.setVacancyInfo(vacancy);
        Main.primaryStage.setScene(new Scene(root));
    }

    public static void openRequests() throws IOException {
        FXMLLoader loader = new FXMLLoader(Application.class.getResource("requestsList.fxml"));
        Parent root = loader.load();
        RequestListController controller = loader.getController();
        controller.setUser(LoginController.getLoggedIn());
        Main.primaryStage.setScene(new Scene(root));
    }


    public static void openProfile() throws IOException {
        FXMLLoader loader = new FXMLLoader(Application.class.getResource("editProfile.fxml"));
        Parent root = loader.load();
        EditProfileController controller = loader.getController();
        controller.setCurrentProfileInfo(LoginController.getLoggedIn());
        Main.primaryStage.setScene(new Scene(root));
    }

}




