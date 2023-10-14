package com.example.studworki_demo;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.io.IOException;


public class MainVacancyController {
    @FXML
    private VBox box;

    @FXML
    private Label city;

    @FXML
    private Label salary;

    @FXML
    private Button saveButton;

    @FXML
    private Label title;
    @FXML
    private Label jobType;

    public Vacancy vacancy;

    public void setData(Vacancy vacancy) {
        title.setText(vacancy.getJobTitle());
        city.setText(vacancy.getCity());
        if (!vacancy.getSalary().equals("Договірна")) {
            salary.setText("З/п: " + vacancy.getSalary() + " грн/міс");
        } else
            salary.setText("З/п: " + vacancy.getSalary());

        jobType.setText(vacancy.getEmploymentType());
        box.setStyle("-fx-background-color: #fff;" + "-fx-background-radius: 15;"
                + "-fx-effect: dropShadow(three-pass-box,rgba(0,0,0,0.1),10,0,0,10);");
        this.vacancy = vacancy;
        boolean isSaved = false;
        for (Vacancy vac : LoginController.getLoggedIn().getUsersSavedVacancies()){
            if (vacancy.equals(vac)) {
                saveButton.setText("Збережено");
                saveButton.setTextFill(Color.DARKRED);
                isSaved =true;
                break;
            }
        }
        if(!isSaved) {
            saveButton.setText("Зберегти");
            saveButton.setTextFill(Color.web("#549fd9"));
        }
    }

    public Vacancy  getVacancy() {
        return vacancy;
    }

    public int getIntSalary(){
        if(!vacancy.getSalary().equals("Договірна"))
            return Integer.parseInt(vacancy.getSalary());
        return -1;
    }

    public int getIntExp(){
        if (vacancy.getExperience().equals("Без досвіду"))
            return 0;
        return 1;
    }

    public String getName(){
        return vacancy.getJobTitle();
    }

    @FXML
    public void savePressed(){
        if(saveButton.getText().equals("Зберегти")){
            LoginController.getLoggedIn().addToUserSavedList(vacancy);
            RegisterController.updateUsersInFile();
            saveButton.setText("Збережено");
            saveButton.setTextFill(Color.DARKRED);
        }else {
            LoginController.getLoggedIn().removeOutUserSavedList(vacancy);
            RegisterController.updateUsersInFile();
            saveButton.setText("Зберегти");
            saveButton.setTextFill(Color.web("#549fd9"));
        }
        System.out.println(LoginController.getLoggedIn().getUsersSavedVacancies());
    }

    @FXML
    public void detailsPressed() throws IOException {
        Application.openVacancyDetails(vacancy);
    }


}
