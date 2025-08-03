package com.example.studentapp.Controller;

import com.example.studentapp.Models.Course;
import com.example.studentapp.Util.FileHandler;
import com.example.studentapp.Util.SessionManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.util.List;

public class RegisterCourseController {
    @FXML private ListView<Course> courseListView;
    private final ObservableList<Course> courseList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        List<String> lines = FileHandler.readLines("database/course.txt");
        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts.length == 2) {
                courseList.add(new Course(parts[0], parts[1]));
            }
        }
        courseListView.setItems(courseList);
    }

    @FXML
    void handleRegister(ActionEvent event) {
        Course selected = courseListView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            String username = SessionManager.getLoggedInStudent().getUsername();
            String registrationEntry = username + "," + selected.getCourseCode();

            FileHandler.appendLine("database/registeredCourse.txt", registrationEntry);  // ⬅️ write to root folder instead

            System.out.println("Registered: " + registrationEntry);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Course Registered");
            alert.setContentText("You have successfully registered for: " + selected.getCourseName());
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Course Selected");
            alert.setContentText("Please select a course to register.");
            alert.showAndWait();
        }
    }


    @FXML
    void handleBack(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/studentapp/view/dashboard.fxml"));
        Stage stage = (Stage) courseListView.getScene().getWindow();
        stage.setScene(new Scene(loader.load()));
    }
}
