package com.example.studworki_demo;


import javafx.animation.AnimationTimer;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class ApplicationController implements Initializable {

    @FXML
    private Label firstName;
    @FXML
    private Label secondName;
    @FXML
    private HBox popularOffersLayout;

    @FXML
    private GridPane grid;

    @FXML
    private ChoiceBox<String> sortChoiceBox;

    @FXML
    private Button cityOkButton;

    @FXML
    private TextField cityTextField;

    @FXML
    private CheckBox distanceCheck;
    @FXML
    private CheckBox fullCheck;
    @FXML
    private TextField maxSalary;

    @FXML
    private TextField minSalary;

    @FXML
    private CheckBox noExpCheck;

    @FXML
    private CheckBox partlyCheck;
    @FXML
    private Button salatyOkButton;

    @FXML
    private TextField searchBar;

    @FXML
    private CheckBox yearExpCheck;

    private final Set<String> selectedEmploymentTypes = new HashSet<>();

    private final String[] sortOptions = {"Не сортувати", "Найвища зарплатня", "Спочатку без досвіду"};

    public static ArrayList<VBox> nodes = new ArrayList<>();

    public void setUser(Account account) {
        firstName.setText(account.getFirstname());
        secondName.setText(account.getLastname());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sortChoiceBox.getItems().addAll(sortOptions);
        sortChoiceBox.setValue(sortOptions[0]);

        sortChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if ("Найвища зарплатня".equals(newValue)) {
                sortBySalary();
            }
            if("Не сортувати".equals(newValue)){
                ArrayList<VBox> currentNodes = getCurrentNodes();
                Collections.shuffle(currentNodes);
                showElements(currentNodes);
            }
            if("Спочатку без досвіду".equals(newValue)){
                sortByExperience();
            }
        });

        List<Vacancy> popularVacancies = new ArrayList<>();
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < Main.allVacancies.size(); i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);
        if(Main.allVacancies.size()>=5){
        for (int i = 0; i < 5; i++) {
            int randomNumber = numbers.get(i);
            popularVacancies.add(Main.allVacancies.get(randomNumber));
        }}else {
            popularVacancies.addAll(Main.allVacancies);
        }

        int column = 0;
        int row = 1;
        try {
            for (Vacancy vacancy : popularVacancies) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("vacancy.fxml"));
                VBox vacancyBox = fxmlLoader.load();
                VacancyController vacancyController = fxmlLoader.getController();
                vacancyController.setData(vacancy);
                popularOffersLayout.getChildren().add(vacancyBox);
            }


            for (Vacancy vacancy : Main.allVacancies) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("mainVacancy.fxml"));
                VBox mainVacancyBox = fxmlLoader.load();
                MainVacancyController mainVacancyController = fxmlLoader.getController();
                mainVacancyController.setData(vacancy);
                mainVacancyBox.getProperties().put("fxmlLoader", fxmlLoader);
                if (column == 2) {
                    column = 0;
                    row++;
                }
                grid.add(mainVacancyBox, column++, row);
                nodes.add(mainVacancyBox);
                GridPane.setMargin(mainVacancyBox, new Insets(10));

            }
            partlyCheck.setOnAction(event -> updateFilter() );
            distanceCheck.setOnAction(event -> updateFilter() );
            fullCheck.setOnAction(event ->updateFilter() );
            yearExpCheck.setOnAction(event -> updateFilter());
            noExpCheck.setOnAction(event -> updateFilter());
            cityOkButton.setOnAction(event -> updateFilter());
            salatyOkButton.setOnAction(event -> updateFilter());
        } catch (IOException e) {
            e.printStackTrace();
        }

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
               if(!SavedController.savedSet) {
                   updateVacancies();
                   SavedController.savedSet = true;
               }
            }
        };
        timer.start();

    }


    public void updateVacancies(){
            for(VBox vBox : nodes) {
                MainVacancyController mainVacancyController = getVacancyControllerOutVBox(vBox);
                mainVacancyController.setData(mainVacancyController.getVacancy());
            }
            showElements(getCurrentNodes());
    }



    private void updateFilter() {
        selectedEmploymentTypes.clear();

        if (partlyCheck.isSelected()) {
            selectedEmploymentTypes.add("Часткова");
        }
        if (fullCheck.isSelected()) {
            selectedEmploymentTypes.add("Повна");
        }
        if (distanceCheck.isSelected()) {
            selectedEmploymentTypes.add("Віддалено");
        }

        String cityFilter = cityTextField.getText().trim().toLowerCase();

        double minSalaryValue = parseSalary(minSalary.getText());
        double maxSalaryValue = parseSalary(maxSalary.getText());
        grid.getChildren().clear();

        boolean noExpSelected = noExpCheck.isSelected();
        boolean yearExpSelected = yearExpCheck.isSelected();

        int column = 0;
        int row = 1;

        for (VBox mainVacancyBox : nodes) {
            Vacancy vacancy = getVacancyOutVBox(mainVacancyBox);
            boolean show = selectedEmploymentTypes.isEmpty() || selectedEmploymentTypes.contains(vacancy.getEmploymentType());
                if (!cityFilter.isEmpty()) {
                    show = show && vacancy.getCity().toLowerCase().equals(cityFilter);
                }

                double vacancySalary = parseSalary(vacancy.getSalary());
                if ((minSalaryValue > 0 && vacancySalary < minSalaryValue) || (maxSalaryValue > 0 && vacancySalary > maxSalaryValue)) {
                    show = false;
                }

                if (noExpSelected && yearExpSelected) {
                    show = show && (vacancy.getExperience().equals("Без досвіду") || vacancy.getExperience().equals("До 1 року"));
                } else if (noExpSelected) {
                    show = show && vacancy.getExperience().equals("Без досвіду");
                } else if (yearExpSelected) {
                    show = show && vacancy.getExperience().equals("До 1 року");
                }
                if (show) {
                    if (column == 2) {
                        column = 0;
                        row++;
                    }
                    grid.add(mainVacancyBox, column++, row);
                }


        }
    }

    public void showElements(ArrayList<VBox> nodes){
        grid.getChildren().clear();
        int column = 0;
        int row = 1;
        for (VBox mainVacancyBox : nodes) {
            if (column == 2) {
                column = 0;
                row++;
            }
            grid.add(mainVacancyBox, column++, row);
        }
    }

    public static Vacancy getVacancyOutVBox(VBox vBox){
        FXMLLoader fxmlLoader = (FXMLLoader) vBox.getProperties().get("fxmlLoader");
        MainVacancyController mainVacancyController = fxmlLoader.getController();
        return mainVacancyController.getVacancy();
    }

    public static MainVacancyController getVacancyControllerOutVBox(VBox vBox){
        FXMLLoader fxmlLoader = (FXMLLoader) vBox.getProperties().get("fxmlLoader");
        return fxmlLoader.getController();
    }

    private List<VBox> searchVacanciesByName(String searchName) {
        List<VBox> matchingVacancies = new ArrayList<>();
        for (VBox vbox : nodes) {
            MainVacancyController controller = getVacancyControllerOutVBox(vbox);
            if (controller != null && controller.getName().toLowerCase().contains(searchName.toLowerCase())) {
                matchingVacancies.add(vbox);
            }
        }
        return matchingVacancies;
    }



    public static <T> void insertionSort(ArrayList<T> list, Comparator<? super T> comparator) {
        for (int i = 1; i < list.size(); i++) {
            T current = list.get(i);
            int j = i - 1;

            while (j >= 0 && comparator.compare(list.get(j), current) > 0) {
                list.set(j + 1, list.get(j));
                j--;
            }

            list.set(j + 1, current);
        }
    }

    private void sortBySalary(){
        Comparator<VBox> salaryComparator = (vbox1, vbox2) -> {
            MainVacancyController mainVacancyController1 = getVacancyControllerOutVBox(vbox1);
            MainVacancyController mainVacancyController2 = getVacancyControllerOutVBox(vbox2);
            if (mainVacancyController1 != null && mainVacancyController2 != null) {
                int salary1 = mainVacancyController1.getIntSalary();
                int salary2 = mainVacancyController2.getIntSalary();
                return Integer.compare(salary2, salary1);
            }
            return -1;
        };
        ArrayList<VBox> currentNodes = getCurrentNodes();
        insertionSort(currentNodes, salaryComparator);
        showElements(currentNodes);
    }

    private void sortByExperience(){
        Comparator<VBox> experienceComparator=(vbox1,vbox2)->{
            MainVacancyController mainVacancyController1 = getVacancyControllerOutVBox(vbox1);
            MainVacancyController mainVacancyController2 = getVacancyControllerOutVBox(vbox2);
            if (mainVacancyController1 != null && mainVacancyController2 != null) {
                int exp1 = mainVacancyController1.getIntExp();
                int exp2 = mainVacancyController2.getIntExp();
                return Integer.compare(exp1, exp2);
            }
            return -1;
        };
        ArrayList<VBox> currentNodes = getCurrentNodes();
        insertionSort(currentNodes, experienceComparator);
        showElements(currentNodes);
    }

    public void searchPressed(ActionEvent ignoredEvent){
        ArrayList<VBox> foundVacancies = (ArrayList<VBox>) searchVacanciesByName(searchBar.getText());
        showElements(foundVacancies);
    }



public ArrayList<VBox> getCurrentNodes(){
    ObservableList<Node> children = grid.getChildren();
    ArrayList<VBox> vboxList = new ArrayList<>();
    for (Node node : children) {
        if (node instanceof VBox vbox)
            vboxList.add(vbox);
    }
    return vboxList;
}

public void openSaved(ActionEvent ignoredEvent) throws IOException {
      Application.openSaved();
      SavedController.savedSet=true;
}

public void openRequestsScene(ActionEvent event) throws IOException {
        Application.openRequests();
}

public void openProfileInfo(ActionEvent event) throws IOException {
        Application.openProfile();
}

    private double parseSalary(String salary) {
        try {
            return Double.parseDouble(salary);
        } catch (NumberFormatException e) {
            return -1; // Handle invalid input gracefully
        }
    }
}