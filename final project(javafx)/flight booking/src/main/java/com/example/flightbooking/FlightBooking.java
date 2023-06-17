package com.example.flightbooking;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;

import java.sql.*;

public class FlightBooking {

  @FXML
  private TextField Arrival;

  @FXML
  private TextField Departure;

  @FXML
  private Text errorMessage;

  @FXML
  private TextField noOfSeats;

  @FXML
  private TextField totalAmount;
  @FXML
  private RadioButton radioButton1;

  @FXML
  private RadioButton radioButton2;

  @FXML
  private RadioButton radioButton3;

    String selectedClass = null;
    @FXML
    private void initialize() {
        ToggleGroup toggleGroup = new ToggleGroup();
        radioButton1.setToggleGroup(toggleGroup);
        radioButton2.setToggleGroup(toggleGroup);
        radioButton3.setToggleGroup(toggleGroup);

        radioButton1.setOnAction(e -> {
            if (radioButton1.isSelected()) {
                selectedClass = "Economy";
            }
        });

        radioButton2.setOnAction(e -> {
            if (radioButton2.isSelected()) {
                selectedClass = "Premium";
            }
        });

        radioButton3.setOnAction(e -> {
            if (radioButton3.isSelected()) {
                selectedClass = "bussiness";
            }
        });
    }

    @FXML
   public void calculatePrice() {
        String seatsStr = noOfSeats.getText();

        // Validate that the number of seats is a valid number
        double seats;
        try {
            seats = Double.parseDouble(seatsStr);
        } catch (NumberFormatException e) {
            // Show error message and return if the number of seats is not a valid number
            errorMessage.setText("Please enter a valid number of seats");
            errorMessage.setVisible(true);
            totalAmount.setText("0.00");
            return;
        }
    String SQL_SELECT = "Select price from flightprice where Arrival=? and Departure=? and class=?";
    double price=0;
    double final_price=0;
    try (Connection conn = DriverManager.getConnection(
            "jdbc:mysql://127.0.0.1:3306/flightdetails", "root", "taran@1211");
         PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT)) {
      preparedStatement.setString(1,Arrival.getText());
      preparedStatement.setString(2,Departure.getText());
      preparedStatement.setString(3,selectedClass);
      ResultSet resultSet = preparedStatement.executeQuery();



      while (resultSet.next()) {

        price = resultSet.getDouble("price");
      }


      if(price==0){
        errorMessage.setVisible(true);
        errorMessage.setText("Please enter correct information");
        totalAmount.setText("0.00");
      }else{
        errorMessage.setVisible(false);
        final_price = Double.parseDouble(noOfSeats.getText()) * price;
        totalAmount.setText("pay "+final_price);


      }


      System.out.println("Total price is  "+price);


    } catch (SQLException e) {
      System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

}
