package com.example.studentapp.Controller;

import com.example.studentapp.Models.Student;
import com.example.studentapp.Util.FileHandler;
import com.example.studentapp.Util.SessionManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class ProfileController {
    @FXML private TextField nameField;
    @FXML private TextField emailField;
    @FXML private TextField programField;
    @FXML private TextField semesterField;

    @FXML
    public void initialize() {
        Student student = SessionManager.getLoggedInStudent();
        if (student != null) {
            nameField.setText(student.getName());
            emailField.setText(student.getEmail());
            programField.setText(student.getProgram());
            semesterField.setText(student.getSemester());
        }
    }

    @FXML
    void handleSave(ActionEvent event) {
        Student student = SessionManager.getLoggedInStudent();
        student.setName(nameField.getText());
        student.setEmail(emailField.getText());
        student.setProgram(programField.getText());
        student.setSemester(semesterField.getText());

        // Update student.txt
        List<String> lines = FileHandler.readLines("database/student.txt");
        List<String> updated = new ArrayList<>();
        for (String line : lines) {
            if (line.startsWith(student.getUsername() + ",")) {
                updated.add(String.join(",", student.getUsername(), "dummy", student.getName(), student.getEmail(), student.getProgram(), student.getSemester()));
            } else {
                updated.add(line);
            }
        }
        FileHandler.writeLines("database/student.txt", updated);
    }

    @FXML
    void handleBack(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/studentapp/view/dashboard.fxml"));
        Stage stage = (Stage) nameField.getScene().getWindow();
        stage.setScene(new Scene(loader.load()));
    }
}