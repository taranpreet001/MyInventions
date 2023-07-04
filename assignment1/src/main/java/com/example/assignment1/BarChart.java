package com.example.assignment1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class BarChart implements Initializable {
    @FXML
    private AnchorPane chartPane;
    @FXML
    private javafx.scene.chart.BarChart<String, Number> barChart;
    @FXML
    private void SwitchToTableView(ActionEvent event){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Parent root;
        try {
            root = loader.load();
            Stage stage =(Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("covid19 details");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        barChart = new javafx.scene.chart.BarChart<>(xAxis, yAxis);

        try {
            // Create a connection to the database
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/covid19Details", "root", "taran@1211");

            // Create a statement
            Statement statement = connection.createStatement();

            // Execute the query
            ResultSet resultSet = statement.executeQuery("SELECT * FROM country_records");

            // Create series for the bar chart
            XYChart.Series<String, Number> affectedSeries = new XYChart.Series<>();
            affectedSeries.setName("Affected");

            XYChart.Series<String, Number> recoveredSeries = new XYChart.Series<>();
            recoveredSeries.setName("Recovered");

            XYChart.Series<String, Number> deathsSeries = new XYChart.Series<>();
            deathsSeries.setName("Deaths");

            // Process the result set
            while (resultSet.next()) {
                int country_Id = resultSet.getInt("country_Id");
                String country_Name = resultSet.getString("country_Name");
                int affected = resultSet.getInt("affected");
                int recovered = resultSet.getInt("recovered");
                int deaths = resultSet.getInt("deaths");

                // Add data to the series
                affectedSeries.getData().add(new XYChart.Data<>(country_Name, affected));
                recoveredSeries.getData().add(new XYChart.Data<>(country_Name, recovered));
                deathsSeries.getData().add(new XYChart.Data<>(country_Name, deaths));
            }

            // Close the resources
            resultSet.close();
            statement.close();
            connection.close();

            // Add series to the bar chart
            barChart.getData().addAll(affectedSeries, recoveredSeries, deathsSeries);

            // Add the bar chart to the anchor pane
            chartPane.getChildren().add(barChart);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
