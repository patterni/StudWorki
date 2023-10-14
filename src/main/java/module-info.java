module com.example.studworki_demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;
    requires java.desktop;
    requires org.apache.commons.lang3;
    requires javafx.web;


    opens com.example.studworki_demo to javafx.fxml;
    exports com.example.studworki_demo;
}