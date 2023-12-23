package com.example.belajargui;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;


public class Form extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Membuat input field nama, nim, email
        Label labelNama = new Label("Nama");
        TextField fieldNama = new TextField();
        Label labelNIM = new Label("NIM");
        TextField fieldNIM = new TextField();
        Label labelEmail = new Label("Email");
        TextField fieldEmail = new TextField();

        // Membuat radio button gender
        Label labelJenisKelamin = new Label("Jenis Kelamin");
        RadioButton genderL = new RadioButton("Laki-laki");
        RadioButton genderP = new RadioButton("Perempuan");
        ToggleGroup radioGroup = new ToggleGroup();
        genderL.setToggleGroup(radioGroup);
        genderP.setToggleGroup(radioGroup);

        // Membuat button untuk submit
        Button addBtn = new Button("Submit");
        Button updateBtn = new Button("Update");
        Button deleteBtn = new Button("Delete");

        // Membuat arraylist data
        ArrayList<Mahasiswa> form = new ArrayList<>();
        ObservableList<Mahasiswa> data = FXCollections.observableArrayList();

        TableView<Mahasiswa> tabel = new TableView<>();
        TableColumn<Mahasiswa, String> kolomNama = new TableColumn<>("Nama");
        TableColumn<Mahasiswa, String> kolomNIM = new TableColumn<>("NIM");
        TableColumn<Mahasiswa, String> kolomEmail = new TableColumn<>("Email");
        TableColumn<Mahasiswa, String> kolomJenisKelamin = new TableColumn<>("Jenis Kelamin");
        kolomNama.setCellValueFactory(
                new PropertyValueFactory<>("nama")
        );
        kolomNIM.setCellValueFactory(
                new PropertyValueFactory<>("nim")
        );
        kolomEmail.setCellValueFactory(
                new PropertyValueFactory<>("email")
        );
        kolomJenisKelamin.setCellValueFactory(
                new PropertyValueFactory<>("jenisKelamin")
        );
        tabel.getColumns().addAll(kolomNama, kolomNIM, kolomEmail, kolomJenisKelamin);
        tabel.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
        tabel.getColumns().forEach(column -> {
            column.setResizable(false); // Memastikan kolom tidak dapat diubah ukurannya oleh pengguna
            column.setReorderable(false); // Optional: Menonaktifkan fitur mengubah urutan kolom
        });
        tabel.autosize();

        addBtn.setOnAction(e -> {
            RadioButton selectedRadioButton = (RadioButton) radioGroup.getSelectedToggle();
            String jenisKelamin = "";
            if(selectedRadioButton != null){
                jenisKelamin = selectedRadioButton.getText();
            }
            data.add(new Mahasiswa(
                    fieldNama.getText(),
                    fieldNIM.getText(),
                    fieldEmail.getText(),
                    jenisKelamin
            ));

            updateBtn.setOnAction(event -> {
                Mahasiswa selectedMahasiswa = tabel.getSelectionModel().getSelectedItem();
                if(selectedMahasiswa != null){
                    selectedMahasiswa.setNama(fieldNama.getText());
                    selectedMahasiswa.setNim(fieldNIM.getText());
                    selectedMahasiswa.setEmail(fieldEmail.getText());
                    selectedMahasiswa.setJenisKelamin(selectedRadioButton.getText());
                }
                tabel.refresh();
                clearFields(fieldNama, fieldNIM, fieldEmail);
                radioGroup.selectToggle(null);

            });

            // Membersihkan form
            fieldNama.clear();
            fieldNIM.clear();
            fieldEmail.clear();
            radioGroup.selectToggle(null);
        });

        HBox boxBtn = new HBox(10);
        boxBtn.setAlignment(Pos.CENTER_LEFT);
        boxBtn.getChildren().addAll(addBtn, updateBtn, deleteBtn);
        // Membuat tata letak GridPane untuk form
        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.add(labelNama, 0, 0);
        gridPane.add(fieldNama, 1, 0);
        gridPane.add(labelNIM, 0, 1);
        gridPane.add(fieldNIM, 1, 1);
        gridPane.add(labelEmail, 0, 2);
        gridPane.add(fieldEmail, 1, 2 );
        gridPane.add(labelJenisKelamin, 0, 3);
        gridPane.add(genderL, 0, 4);
        gridPane.add(genderP, 0, 5);
        gridPane.add(boxBtn, 1, 6);
        gridPane.add(tabel, 0, 7);

        HBox hbox = new HBox();
        hbox.getChildren().add(tabel);

        HBox mainHBox = new HBox(10);
        mainHBox.getChildren().addAll(gridPane, hbox);

        // Membuat scene dan menampilkannya
        Scene scene = new Scene(mainHBox);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Form");
        primaryStage.show();

        data.add(new Mahasiswa("Shandy", "22108", "shandy@example", "Laki-laki"));
        tabel.setItems(data);

    }

    private void clearFields(TextField... fields){
        for(TextField field : fields){
            field.clear();
        }
    }
}
