package com.praktikum.gui;

import com.praktikum.gui.LoginPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Aplikasi Kehilangan dan Menemukan Barang di Kampus");
        LoginPane login = new LoginPane(primaryStage);
        Scene scene = new Scene(login, 400, 450);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
