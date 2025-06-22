package com.praktikum.gui;

import com.praktikum.gui.AdminDashboard;
import com.praktikum.data.DataStore;
import com.praktikum.users.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;


public class LoginPane extends VBox {
    public LoginPane(Stage stageUtama) {
        Label judul = new Label("Login Sistem Lost & Found");
        ComboBox<String> pilihanPeran = new ComboBox<>();
        TextField namaField = new TextField();
        PasswordField sandiField = new PasswordField();
        Button tombolLogin = new Button("Login");
        Label pesan = new Label();
        Button

        pilihanPeran.getItems().addAll("Mahasiswa", "Admin");
        pilihanPeran.setValue("Mahasiswa");

        setSpacing(10);
        setPadding(new Insets(20));

        getChildren().addAll(judul, new Label("Nama"), namaField, new Label("Kata Sandi"), sandiField, new Label("Peran"), pilihanPeran, tombolLogin, pesan);

        tombolLogin.setOnAction(e -> {
            String nama = namaField.getText();
            String sandi = sandiField.getText();
            String peran = pilihanPeran.getValue();

            User pengguna = DataStore.cariPengguna(nama, sandi, peran);
            if (pengguna != null) {
                if (peran.equals("Mahasiswa")) {
                    stageUtama.setScene(new Scene(new MahasiswaDashboard(stageUtama, pengguna)));
                } else {
                    stageUtama.setScene(new Scene(new AdminDashboard(stageUtama, pengguna)));
                }
            } else {
                pesan.setText("Login gagal, periksa kembali informasi Anda!");
            }
        });
    }
}
