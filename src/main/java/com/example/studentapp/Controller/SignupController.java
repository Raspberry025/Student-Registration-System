package com.example.studentapp.Controller;

import com.example.studentapp.Util.FileHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class SignupController {

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private TextField nameField;
    @FXML private TextField emailField;
    @FXML private TextField programField;
    @FXML private TextField semesterField;

    @FXML
    void handleSignup(ActionEvent event) {
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();
        String name = nameField.getText().trim();
        String email = emailField.getText().trim();
        String program = programField.getText().trim();
        String semester = semesterField.getText().trim();

        if (username.isEmpty() || password.isEmpty() || name.isEmpty() || email.isEmpty() || program.isEmpty() || semester.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "All fields are required.");
            return;
        }

        // Check if user already exists
        for (String line : FileHandler.readLines("database/student.txt")) {
            if (line.startsWith(username + ",")) {
                showAlert(Alert.AlertType.ERROR, "Username already exists.");
                return;
            }
        }

        // Save to file
        String record = String.join(",", username, password, name, email, program, semester);
        FileHandler.appendLine("database/student.txt", record);
        showAlert(Alert.AlertType.INFORMATION, "Signup successful. Please login.");

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/studentapp/view/login.fxml"));
            Stage stage = (Stage) usernameField.getScene().getWindow();
            stage.setScene(new Scene(loader.load()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void handleBackToLogin(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/studentapp/view/login.fxml"));
            Stage stage = (Stage) usernameField.getScene().getWindow();
            stage.setScene(new Scene(loader.load()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showAlert(Alert.AlertType type, String message) {
        Alert alert = new Alert(type);
        alert.setTitle("Signup");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}