package com.praktikum.gui;

import com.praktikum.data.DataStore;
import com.praktikum.data.Item;
import com.praktikum.users.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class MahasiswaDashboard extends VBox {
    public MahasiswaDashboard(Stage stage, User pengguna) {
        Label selamatDatang = new Label("Selamat datang, " + pengguna.getNamaPengguna());
        TextField namaField = new TextField();
        TextField deskripsiField = new TextField();
        TextField lokasiField = new TextField();
        Button tombolLapor = new Button("Laporkan");
        TableView<Item> tabelLaporan = new TableView<>();
        Button tombolLogout = new Button("Logout");

        GridPane form = new GridPane();
        form.setPadding(new Insets(10));
        form.setHgap(10);
        form.setVgap(10);
        form.add(new Label("Nama Barang"), 0, 0);
        form.add(namaField, 1, 0);
        form.add(new Label("Deskripsi"), 0, 1);
        form.add(deskripsiField, 1, 1);
        form.add(new Label("Lokasi"), 0, 2);
        form.add(lokasiField, 1, 2);
        form.add(tombolLapor, 1, 3);

        TableColumn<Item, String> kolomNama = new TableColumn<>("Nama");
        kolomNama.setCellValueFactory(new PropertyValueFactory<>("namaBarang"));
        TableColumn<Item, String> kolomDeskripsi = new TableColumn<>("Deskripsi");
        kolomDeskripsi.setCellValueFactory(new PropertyValueFactory<>("deskripsi"));
        TableColumn<Item, String> kolomLokasi = new TableColumn<>("Lokasi");
        kolomLokasi.setCellValueFactory(new PropertyValueFactory<>("lokasi"));

        tabelLaporan.getColumns().addAll(kolomNama, kolomDeskripsi, kolomLokasi);
        refreshTabel(tabelLaporan);

        tombolLapor.setOnAction(e -> {
            String nama = namaField.getText();
            String deskripsi = deskripsiField.getText();
            String lokasi = lokasiField.getText();
            if (!nama.isEmpty() && !lokasi.isEmpty()) {
                Item item = new Item(nama, deskripsi, lokasi, "Dilaporkan");
                DataStore.daftarLaporan.add(item);
                namaField.clear();
                deskripsiField.clear();
                lokasiField.clear();
                refreshTabel(tabelLaporan);
            }
        });

        tombolLogout.setOnAction(e -> stage.setScene(new Scene(new LoginPane(stage))));

        VBox layout = new VBox(10, selamatDatang, form, new Label("Daftar Laporan Anda:"), tabelLaporan, tombolLogout);
        layout.setPadding(new Insets(10));
        getChildren().add(layout);
    }

    private void refreshTabel(TableView<Item> tabel) {
        ObservableList<Item> data = FXCollections.observableArrayList();
        for (Item i : DataStore.daftarLaporan) {
            if (i.getStatus().equalsIgnoreCase("Dilaporkan")) {
                data.add(i);
            }
        }
        tabel.setItems(data);
    }
}