package com.praktikum.gui;

import com.praktikum.data.*;
import com.praktikum.users.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class AdminDashboard extends VBox {
    public AdminDashboard(Stage stage, User pengguna) {
        Label selamatDatang = new Label("Halo, Administrator " + pengguna.getNamaPengguna());

        TableView<Item> tabelLaporan = new TableView<>();
        TableColumn<Item, String> kolomNama = new TableColumn<>("Nama Barang");
        kolomNama.setCellValueFactory(new PropertyValueFactory<>("namaBarang"));
        TableColumn<Item, String> kolomLokasi = new TableColumn<>("Lokasi");
        kolomLokasi.setCellValueFactory(new PropertyValueFactory<>("lokasi"));
        TableColumn<Item, String> kolomStatus = new TableColumn<>("Status");
        kolomStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        tabelLaporan.getColumns().addAll(kolomNama, kolomLokasi, kolomStatus);
        refreshTabelLaporan(tabelLaporan);

        Button tombolTandai = new Button("Tandai Diambil");
        tombolTandai.setOnAction(e -> {
            Item item = tabelLaporan.getSelectionModel().getSelectedItem();
            if (item != null) {
                item.setStatus("Diambil");
                refreshTabelLaporan(tabelLaporan);
            }
        });

        TableView<User> tabelMahasiswa = new TableView<>();
        TableColumn<User, String> kolomUser = new TableColumn<>("Nama");
        kolomUser.setCellValueFactory(new PropertyValueFactory<>("namaPengguna"));
        TableColumn<User, String> kolomNIM = new TableColumn<>("NIM");
        kolomNIM.setCellValueFactory(new PropertyValueFactory<>("kataSandi"));

        tabelMahasiswa.getColumns().addAll(kolomUser, kolomNIM);
        refreshTabelMahasiswa(tabelMahasiswa);

        TextField namaBaru = new TextField();
        TextField nimBaru = new TextField();
        Button tambah = new Button("Tambah Mahasiswa");
        Button hapus = new Button("Hapus Mahasiswa");

        tambah.setOnAction(e -> {
            String nama = namaBaru.getText();
            String nim = nimBaru.getText();
            if (!nama.isEmpty() && !nim.isEmpty()) {
                DataStore.daftarPengguna.add(new Mahasiswa(nama, nim));
                namaBaru.clear(); nimBaru.clear();
                refreshTabelMahasiswa(tabelMahasiswa);
            }
        });

        hapus.setOnAction(e -> {
            User mhs = tabelMahasiswa.getSelectionModel().getSelectedItem();
            if (mhs instanceof Mahasiswa) {
                DataStore.daftarPengguna.remove(mhs);
                refreshTabelMahasiswa(tabelMahasiswa);
            }
        });

        HBox formTambah = new HBox(10, new Label("Nama"), namaBaru, new Label("NIM"), nimBaru, tambah, hapus);
        Button tombolLogout = new Button("Logout");
        tombolLogout.setOnAction(e -> stage.setScene(new Scene(new LoginPane(stage))));

        VBox layout = new VBox(10,
                selamatDatang,
                new Label("Laporan Barang"), tabelLaporan, tombolTandai,
                new Label("Data Mahasiswa"), tabelMahasiswa, formTambah,
                tombolLogout);
        layout.setPadding(new Insets(10));
        getChildren().add(layout);
    }

    private void refreshTabelLaporan(TableView<Item> tabel) {
        ObservableList<Item> data = FXCollections.observableArrayList(DataStore.daftarLaporan);
        tabel.setItems(data);
    }

    private void refreshTabelMahasiswa(TableView<User> tabel) {
        ObservableList<User> data = FXCollections.observableArrayList();
        for (User u : DataStore.daftarPengguna) {
            if (u instanceof Mahasiswa) {
                data.add(u);
            }
        }
        tabel.setItems(data);
    }
}
