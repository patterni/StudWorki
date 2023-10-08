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
    @Override
    public void start(Stage stage) throws IOException {
        System.out.println("start");
        primaryStage =stage;
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login.fxml"));
        scene = new Scene(fxmlLoader.load(), 520, 400);
        primaryStage.setTitle("StudWork");
        primaryStage.setScene(scene);
        Image icon = new Image("JStudent.png");
        primaryStage.getIcons().add(icon);
        primaryStage.show();


        if (RegisterController.file.exists() && RegisterController.file.length() > 0) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(RegisterController.file))) {
            Object object = inputStream.readObject();
            if(object!=null){
            if (object instanceof List) {
                List<Account> objectList = (List<Account>) object;
                for (Object obj : objectList) {
                    if (obj instanceof Account) {
                        accounts.add((Account) obj);
                    }
                }
            }}
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }}
        System.out.println(accounts);

//        try(BufferedWriter writer = new BufferedWriter(new FileWriter("VACANCIES.txt"))){
//            Vacancy vacancy = new Vacancy("Junior Web Developer", "Не обов'язковий", "Повна зайнятість", "30000 грн/міс", "Розвивайте веб-сайти та додатки.", "Вінниця");
//            writer.write(vacancy.toString());
//        }catch (IOException e){
//            e.printStackTrace();
//        }

        try (BufferedReader reader = new BufferedReader(new FileReader("VACANCIES.txt"))) {
            String line;
            StringBuilder vacancyData = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                if (line.equals("/")) {
                    // Вакансія закінчилася, додайте її до списку
                    allVacancies.add(parseVacancyData(vacancyData.toString()));
                    vacancyData = new StringBuilder();
                } else {
                    vacancyData.append(line).append("\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Vacancy parseVacancyData(String data) {
        String[] lines = data.split("\n");
        String jobTitle = lines[0].replace("Job Title: ", "");
        String description = lines[1].replace("Description: ", "");
        String experience = lines[2].replace("Experience Required: ", "");
        String employmentType = lines[3].replace("Employment Type: ", "");
        String salary = lines[4].replace("Salary: ", "");
        String city = lines[5].replace("City: ", "");
        return new Vacancy(jobTitle, experience, employmentType, salary, description, city);
    }


    public static void main(String[] args) {
        launch();
    }
}