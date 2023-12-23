package com.example.belajargui;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.ArrayList;

public class FormWithListView extends Application {

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
        Button btn = new Button("Submit");

        // Membuat ArrayList data
        ArrayList<Mahasiswa> form = new ArrayList<>();

        // Membuat ListView
        ListView<Mahasiswa> listView = new ListView<>();
        ObservableList<Mahasiswa> dataMahasiswa = FXCollections.observableArrayList();

        btn.setOnAction(e -> {
            String nama = fieldNama.getText();
            String nim = fieldNIM.getText();
            String email = fieldEmail.getText();
            RadioButton selectedRadioButton = (RadioButton) radioGroup.getSelectedToggle();
            String jenisKelamin = "";
            if(selectedRadioButton != null){
                jenisKelamin = selectedRadioButton.getText();
            }

            // Menambahkan data ke dalam ArrayList
            Mahasiswa newMahasiswa = new Mahasiswa(nama, nim, email, jenisKelamin);
            form.add(newMahasiswa);

            // Membersihkan input field
            fieldNama.clear();
            fieldNIM.clear();
            fieldEmail.clear();
            radioGroup.selectToggle(null);

            // Memperbarui data pada ListView
            dataMahasiswa.setAll(form);
            listView.setItems(dataMahasiswa);
            listView.setCellFactory(param -> new ListCell<Mahasiswa>() {
                @Override
                protected void updateItem(Mahasiswa mahasiswa, boolean empty) {
                    super.updateItem(mahasiswa, empty);

                    if (empty || mahasiswa == null) {
                        setText(null);
                    } else {
                        setText("Nama: " + mahasiswa.getNama() + ", NIM: " + mahasiswa.getNim());
                    }
                }
            });
        });

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
        gridPane.add(btn, 1, 6);

        // Membuat tata letak VBox untuk ListView
        VBox vbox = new VBox();
        vbox.getChildren().add(new Text("Data Mahasiswa"));
        vbox.getChildren().add(listView);
        // Membuat scene dan menampilkannya
        Scene scene = new Scene(new VBox(gridPane, vbox), 400, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Form dengan ListView");
        primaryStage.show();
    }
}
