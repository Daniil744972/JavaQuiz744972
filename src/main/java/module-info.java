module com.example.javaquiz744972 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires com.fasterxml.jackson.annotation;
    requires tools.jackson.databind;
    requires java.desktop;
    requires java.logging;
    requires javafx.base;
    requires javafx.web;

    opens com.example.javaquiz744972 to javafx.fxml;
    opens com.example.javaquiz744972.entities to
            tools.jackson.databind;
    exports com.example.javaquiz744972;
    exports com.example.javaquiz744972.controllers;
    opens com.example.javaquiz744972.controllers to javafx.fxml;
}