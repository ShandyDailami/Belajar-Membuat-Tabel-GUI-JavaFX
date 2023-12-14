module com.example.belajargui {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.belajargui to javafx.fxml;
    exports com.example.belajargui;
}