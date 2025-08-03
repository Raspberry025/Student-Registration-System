package com.example.studentapp.Controller;

import com.example.studentapp.Models.Student;
import com.example.studentapp.Util.SessionManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class DashboardController {

    @FXML
    private Label welcomeLabel;

    @FXML
    public void initialize() {
        Student student = SessionManager.getLoggedInStudent();
        if (student != null) {
            welcomeLabel.setText("Welcome, " + student.getName() + "!");
        }
    }

    @FXML
    void goToProfile(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/studentapp/view/profile.fxml"));
        Stage stage = (Stage) welcomeLabel.getScene().getWindow();
        stage.setScene(new Scene(loader.load()));
    }

    @FXML
    void goToRegisterCourse(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/studentapp/view/registercourse.fxml"));
        Stage stage = (Stage) welcomeLabel.getScene().getWindow();
        stage.setScene(new Scene(loader.load()));
    }

    @FXML
    void goToViewCourses(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/studentapp/view/viewcourse.fxml"));
        Stage stage = (Stage) welcomeLabel.getScene().getWindow();
        stage.setScene(new Scene(loader.load()));
    }

    @FXML
    void handleLogout(ActionEvent event) throws Exception {
        SessionManager.clear();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/studentapp/view/login.fxml"));
        Stage stage = (Stage) welcomeLabel.getScene().getWindow();
        stage.setScene(new Scene(loader.load()));
    }
}
