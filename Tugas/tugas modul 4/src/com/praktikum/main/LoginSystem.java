package com.praktikum.main;

import com.praktikum.data.Item;
import com.praktikum.users.Admin;
import com.praktikum.users.Mahasiswa;
import com.praktikum.users.User;

import java.util.ArrayList;
import java.util.Scanner;

public class LoginSystem {
    public static ArrayList<User> userList = new ArrayList<>();
    public static ArrayList<Item> reportedItems = new ArrayList<>();

    public static void main(String[] args) {
        // Initialize data
        initializeData();

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("===== Sistem Informasi Pelaporan Barang =====");

        while (running) {
            System.out.println("\nPilih Jenis Login:");
            System.out.println("1. Admin");
            System.out.println("2. Mahasiswa");
            System.out.println("3. Keluar");
            System.out.print("Pilihan: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Clear buffer

                switch (choice) {
                    case 1:
                        loginAdmin(scanner);
                        break;
                    case 2:
                        loginMahasiswa(scanner);
                        break;
                    case 3:
                        System.out.println("Terima kasih telah menggunakan sistem ini. Sampai jumpa!");
                        running = false;
                        break;
                    default:
                        System.out.println("Pilihan tidak valid.");
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("Error: Input harus berupa angka!");
                scanner.nextLine(); // Clear buffer
            }
        }

        scanner.close();
    }

    private static void initializeData() {
        userList.add(new Admin("Admin123", "Password123"));

        userList.add(new Mahasiswa("Ahmad Dzaky Awwab", "202410370110078"));

    }

    private static void loginAdmin(Scanner scanner) {
        System.out.println("\n===== Login Admin =====");

        User loggedInUser = null;

        for (User user : userList) {
            if (user instanceof Admin) {
                Admin admin = (Admin) user;
                if (admin.login(scanner)) {
                    System.out.println("Login Admin berhasil!");
                    loggedInUser = admin;
                    break;
                }
            }
        }

        if (loggedInUser != null) {
            loggedInUser.displayAppMenu();
        } else {
            System.out.println("Login gagal! Username atau password salah.");
        }
    }

    private static void loginMahasiswa(Scanner scanner) {
        System.out.println("\n===== Login Mahasiswa =====");

        User loggedInUser = null;

        for (User user : userList) {
            if (user instanceof Mahasiswa) {
                Mahasiswa mahasiswa = (Mahasiswa) user;
                if (mahasiswa.login(scanner)) {
                    System.out.println("Login Mahasiswa berhasil!");
                    mahasiswa.displayInfo();
                    loggedInUser = mahasiswa;
                    break;
                }
            }
        }

        if (loggedInUser != null) {
            loggedInUser.displayAppMenu();
        } else {
            System.out.println("Login gagal! Nama atau NIM salah.");
        }
    }
}