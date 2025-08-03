package com.example.studentapp.Controller;

import com.example.studentapp.Models.Student;
import com.example.studentapp.Util.FileHandler;
import com.example.studentapp.Util.SessionManager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.List;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    void handleLogin(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        List<String> users = FileHandler.readLines("database/student.txt");
        for (String line : users) {
            String[] parts = line.split(",");
            if (parts.length >= 6 && parts[0].equals(username) && parts[1].equals(password)) {
                Student student = new Student(parts[0], parts[2], parts[3], parts[4], parts[5]);
                SessionManager.setLoggedInStudent(student);
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/studentapp/view/dashboard.fxml"));
                    Stage stage = (Stage) usernameField.getScene().getWindow();
                    stage.setScene(new Scene(loader.load()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return;
            }
        }
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Login Failed");
        alert.setHeaderText(null);
        alert.setContentText("Invalid username or password.");
        alert.showAndWait();
    }

    @FXML
    void handleSignup(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/studentapp/view/signup.fxml"));
            Stage stage = (Stage) usernameField.getScene().getWindow();
            stage.setScene(new Scene(loader.load()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
