package com.example.studentapp.Controller;

import com.example.studentapp.Util.FileHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatsController {

    @FXML
    private BarChart<String, Number> barChart;

    @FXML
    public void initialize() {
        List<String> lines = FileHandler.readLines("database/registeredCourse.txt");

        Map<String, Integer> studentCourseCount = new HashMap<>();

        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts.length == 2) {
                String student = parts[0].trim();
                studentCourseCount.put(student, studentCourseCount.getOrDefault(student, 0) + 1);
            }
        }

        XYChart.Series<String, Number> dataSeries = new XYChart.Series<>();
        dataSeries.setName("Courses Registered");

        for (Map.Entry<String, Integer> entry : studentCourseCount.entrySet()) {
            dataSeries.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
        }

        barChart.getData().add(dataSeries);
    }

    @FXML
    void handleBack(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/studentapp/view/dashboard.fxml"));
        Stage stage = (Stage) barChart.getScene().getWindow();
        stage.setScene(new Scene(loader.load()));
    }
}