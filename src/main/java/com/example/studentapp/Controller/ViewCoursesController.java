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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.util.List;
import java.util.stream.Collectors;

public class ViewCoursesController {
    @FXML private TableView<Course> courseTable;
    @FXML private TableColumn<Course, String> codeColumn;
    @FXML private TableColumn<Course, String> nameColumn;

    private final ObservableList<Course> registeredCourses = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        codeColumn.setCellValueFactory(new PropertyValueFactory<>("courseCode"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("courseName"));

        String username = SessionManager.getLoggedInStudent().getUsername();

        System.out.println("Logged in user: " + username);

        List<String> allLines = FileHandler.readLines("database/registeredCourse.txt");
        System.out.println("All lines in registeredCourse.txt: " + allLines);
        List<String> userCourseCodes = allLines.stream()
                .filter(line -> line.startsWith(username + ","))
                .map(line -> line.split(",")[1])
                .collect(Collectors.toList());
        System.out.println("Course codes for user " + username + ": " + userCourseCodes);

        List<String> courseLines = FileHandler.readLines("database/course.txt");
        System.out.println("All course lines: " + courseLines);
        for (String line : courseLines) {
            System.out.println("Line: [" + line + "]");
            String[] parts = line.split(",");
            System.out.println("Parts length: " + parts.length);

            if (parts.length == 2) {
                String courseName = parts[0].trim();
                String courseCode = parts[1].trim();

                if (userCourseCodes.contains(courseCode)) {
                    registeredCourses.add(new Course(courseCode, courseName));
                }
            } else {
                System.out.println("⚠️ Invalid line format, skipping: " + line);
            }
        }
        courseTable.setItems(registeredCourses);
    }

    @FXML
    void handleDelete(ActionEvent event) {
        Course selected = courseTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            String username = SessionManager.getLoggedInStudent().getUsername();
            List<String> allLines = FileHandler.readLines("database/registeredCourse.txt");
            List<String> updated = allLines.stream()
                    .filter(line -> !(line.equals(username + "," + selected.getCourseCode())))
                    .collect(Collectors.toList());
            FileHandler.writeLines("database/registeredCourse.txt", updated);
            registeredCourses.remove(selected);
        }
    }

    @FXML
    void handleBack(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/studentapp/view/dashboard.fxml"));
        Stage stage = (Stage) courseTable.getScene().getWindow();
        stage.setScene(new Scene(loader.load()));
    }
}