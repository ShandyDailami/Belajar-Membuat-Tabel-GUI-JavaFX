package com.example.belajargui;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CRUDTableViewExample extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        TableView<Mahasiswa> tabel = new TableView<>();
        ObservableList<Mahasiswa> dataMahasiswa = FXCollections.observableArrayList();

        // Mendefinisikan kolom
        TableColumn<Mahasiswa, String> kolomNama = new TableColumn<>("Nama");
        TableColumn<Mahasiswa, String> kolomNIM = new TableColumn<>("NIM");
        TableColumn<Mahasiswa, String> kolomEmail = new TableColumn<>("Email");
        TableColumn<Mahasiswa, String> kolomJenisKelamin = new TableColumn<>("Jenis Kelamin");

        kolomNama.setCellValueFactory(new PropertyValueFactory<>("nama"));
        kolomNIM.setCellValueFactory(new PropertyValueFactory<>("nim"));
        kolomEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        kolomJenisKelamin.setCellValueFactory(new PropertyValueFactory<>("jenisKelamin"));

        tabel.getColumns().addAll(kolomNama, kolomNIM, kolomEmail, kolomJenisKelamin);

        // Tombol-tombol CRUD
        Button addButton = new Button("Tambah");
        Button updateButton = new Button("Update");
        Button deleteButton = new Button("Hapus");

        // Input fields
        TextField fieldNama = new TextField();
        TextField fieldNIM = new TextField();
        TextField fieldEmail = new TextField();
        TextField fieldJenisKelamin = new TextField();

        // Tambah data baru
        addButton.setOnAction(e -> {
            dataMahasiswa.add(new Mahasiswa(
                    fieldNama.getText(),
                    fieldNIM.getText(),
                    fieldEmail.getText(),
                    fieldJenisKelamin.getText()
            ));
            clearFields(fieldNama, fieldNIM, fieldEmail, fieldJenisKelamin);
        });

        // Update data
        updateButton.setOnAction(e -> {
            Mahasiswa selectedMahasiswa = tabel.getSelectionModel().getSelectedItem();
            if (selectedMahasiswa != null) {
                selectedMahasiswa.setNama(fieldNama.getText());
                selectedMahasiswa.setNim(fieldNIM.getText());
                selectedMahasiswa.setEmail(fieldEmail.getText());
                selectedMahasiswa.setJenisKelamin(fieldJenisKelamin.getText());

                // Untuk memperbarui tampilan TableView
                tabel.refresh();
                clearFields(fieldNama, fieldNIM, fieldEmail, fieldJenisKelamin);
            }
        });

        // Hapus data
        deleteButton.setOnAction(e -> {
            Mahasiswa selectedMahasiswa = tabel.getSelectionModel().getSelectedItem();
            if (selectedMahasiswa != null) {
                dataMahasiswa.remove(selectedMahasiswa);
            }
        });
        HBox hbox = new HBox(10);
        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().addAll(addButton, updateButton, deleteButton);
        VBox root = new VBox();
        root.getChildren().addAll(
                tabel,
                new Label("Nama: "), fieldNama,
                new Label("NIM: "), fieldNIM,
                new Label("Email: "), fieldEmail,
                new Label("Jenis Kelamin: "), fieldJenisKelamin,
                hbox
        );

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("CRUD TableView Example");
        primaryStage.show();

        // Mengisi data awal untuk demonstrasi
        dataMahasiswa.add(new Mahasiswa("John", "123", "john@example.com", "Laki-laki"));
        dataMahasiswa.add(new Mahasiswa("Alice", "456", "alice@example.com", "Perempuan"));
        tabel.setItems(dataMahasiswa);
    }

    private void clearFields(TextField... fields) {
        for (TextField field : fields) {
            field.clear();
        }
    }
}
