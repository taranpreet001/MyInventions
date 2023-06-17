module com.example.flightbooking {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.flightbooking to javafx.fxml;
    exports com.example.flightbooking;
}