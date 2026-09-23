module com.example.javaquiz744972 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires com.fasterxml.jackson.annotation;
    requires tools.jackson.databind;
    requires java.desktop;
    requires java.logging;

    opens com.example.javaquiz744972 to javafx.fxml;
    opens com.example.javaquiz744972.entities to
            tools.jackson.databind;
    exports com.example.javaquiz744972;
}