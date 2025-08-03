module com.example.studentapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;

    opens com.example.studentapp to javafx.fxml;
    opens com.example.studentapp.Models to javafx.base;
    opens com.example.studentapp.Controller to javafx.fxml;
    exports com.example.studentapp;
    exports com.example.studentapp.Controller;
    exports com.example.studentapp.Models;
}