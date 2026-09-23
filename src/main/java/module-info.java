module com.example.javaquiz744972 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.example.javaquiz744972 to javafx.fxml;
    exports com.example.javaquiz744972;
}