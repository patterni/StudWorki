package com.example.studworki_demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class Main extends Application {
    public static Stage primaryStage;
    public static Scene scene;
    public static ArrayList<Account> accounts = new ArrayList<>();
    public static ArrayList<Vacancy> allVacancies = new ArrayList<>();

    public static File file = new File("E:/java/StudWorki_demo/accountBase.bin");
    public static File fileVacancies = new File("E:/java/StudWorki_demo/VACANCIES.bin");


    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("loginOption.fxml"));
        scene = new Scene(fxmlLoader.load());
        primaryStage.setTitle("StudWork");
        primaryStage.setScene(scene);
        Image icon = new Image("JStudent.png");
        primaryStage.getIcons().add(icon);
        primaryStage.show();


        if (file.exists() && file.length() > 0) {
            try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file))) {
                Object object = inputStream.readObject();
                if (object != null) {
                    if (object instanceof List) {
                        List<Account> objectList = (List<Account>) object;
                        for (Object obj : objectList) {
                            if (obj instanceof Account) {
                                accounts.add((Account) obj);
                            }
                        }
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        System.out.println(accounts);

        readVacancies();
    }

    public static void readVacancies(){
        List<Vacancy> loadedVacancies = new ArrayList<>();
        if (fileVacancies.exists() && fileVacancies.length() > 0) {
            try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileVacancies))) {
                Object object = inputStream.readObject();
                if (object != null) {
                    if (object instanceof List) {
                        List<Vacancy> objectList = (List<Vacancy>) object;
                        for (Object obj : objectList) {
                            if (obj instanceof Vacancy) {
                                loadedVacancies.add((Vacancy) obj);
                            }
                        }
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        allVacancies.clear();
        allVacancies.addAll(loadedVacancies);
        System.out.println(allVacancies);
    }
    public static void main(String[] args) {
        launch();
    }
}