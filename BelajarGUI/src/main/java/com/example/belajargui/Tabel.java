package com.example.belajargui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.ArrayList;

public class Tabel extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Text text1 = new Text();
        text1.setText("Masukkan nama:");
        Text text2 = new Text();
        text2.setText("Masukkan nim:");
        TextField textField1 = new TextField();
        TextField textField2 = new TextField();
        PasswordField passwordField = new PasswordField();
        Button btn = new Button("Submit");

        // ArrayList untuk menampung data mahasiswa
        ArrayList<Mahasiswa> dataMahasiswa = new ArrayList<>();

        TableView<Mahasiswa> table = new TableView<>();
        TableColumn<Mahasiswa, String> kolom1 = new TableColumn<>("Nama");
        TableColumn<Mahasiswa, String> kolom2 = new TableColumn<>("NIM");
        kolom1.setCellValueFactory(
                new PropertyValueFactory<>("nama")
        );
        kolom2.setCellValueFactory(
                new PropertyValueFactory<>("nim")
        );
        table.getColumns().addAll(kolom1, kolom2);

        // Mengambil data dari ArrayList dan menambahkannya ke dalam TableView
        btn.setOnAction(e -> {
            String nama = textField1.getText();
            String nim = textField2.getText();
            Mahasiswa mhs = new Mahasiswa(nama, nim);
            dataMahasiswa.add(mhs);
            table.getItems().setAll(dataMahasiswa);
            // Membersihkan TextField setelah ditambahkan ke tabel
            textField1.clear();
            textField2.clear();
        });

        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(
                text1,
                textField1,
                text2,
                textField2,
                btn
        );

        HBox hbox = new HBox();
        hbox.getChildren().addAll(vbox, table);
        Scene scene = new Scene(hbox);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}