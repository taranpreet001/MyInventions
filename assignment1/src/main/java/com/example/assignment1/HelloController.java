package com.example.assignment1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
//fxml elements created
    @FXML
    private TableView<countryRecords> Table;

    @FXML
    private TableColumn<countryRecords, Integer> Deaths;

    @FXML
    private TableColumn<countryRecords, Integer> SNO;

    @FXML
    private TableColumn<countryRecords, Integer> affectedCase;

    @FXML
    private TableColumn<countryRecords, String> countryName;

    @FXML
    private TableColumn<countryRecords, Integer> recoveredCase;



    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SNO.setCellValueFactory(new PropertyValueFactory<>("country_Id"));
        countryName.setCellValueFactory(new PropertyValueFactory<>("country_Name"));
        affectedCase.setCellValueFactory(new PropertyValueFactory<>("affected"));
        recoveredCase.setCellValueFactory(new PropertyValueFactory<>("recovered"));
        Deaths.setCellValueFactory(new PropertyValueFactory<>("deaths"));

        DatabaseConnector dbConnector = new DatabaseConnector();
        Connection connection = dbConnector.connect();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM country_records");
            while (resultSet.next()) {
                int country_Id = resultSet.getInt("country_Id");
                String country_Name = resultSet.getString("country_Name");
                int Affected = resultSet.getInt("affected");
                int recovered = resultSet.getInt("recovered");
                int deaths = resultSet.getInt("deaths");

                // Create a new instance of countryRecords and add it to the table
                countryRecords record = new countryRecords(country_Id, country_Name, Affected, recovered, deaths);
                Table.getItems().add(record);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void ShowBarChart(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("barChart.fxml"));
        Parent root;
        try {
            root = loader.load();
            Stage stage =(Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Bar Chart");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}








