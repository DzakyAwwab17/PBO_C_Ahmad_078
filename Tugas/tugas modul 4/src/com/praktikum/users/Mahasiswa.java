package com.praktikum.users;

import com.praktikum.actions.MahasiswaActions;
import com.praktikum.data.Item;
import com.praktikum.main.LoginSystem;

import java.util.Scanner;

public class Mahasiswa extends User implements MahasiswaActions {
    private String nama;
    private String nim;

    // Constructor
    public Mahasiswa(String nama, String nim) {
        this.nama = nama;
        this.nim = nim;
    }

    // Getters
    public String getNama() {
        return nama;
    }

    public String getNim() {
        return nim;
    }

    @Override
    public boolean login(Scanner scanner) {
        System.out.print("Masukkan nama: ");
        String inputNama = scanner.nextLine();
        System.out.print("Masukkan NIM: ");
        String inputNim = scanner.nextLine();

        return nama.equals(inputNama) && nim.equals(inputNim);
    }

    @Override
    public void displayInfo() {
        System.out.println("Nama: " + nama);
        System.out.println("NIM: " + nim);
    }

    @Override
    public void displayAppMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice = -1;
        boolean isLoggedIn = true;

        while (isLoggedIn) {
            System.out.println("\n==== Mahasiswa Menu ====");
            System.out.println("1. Laporkan Barang Temuan/Hilang");
            System.out.println("2. Lihat Daftar Laporan");
            System.out.println("0. Logout");
            System.out.print("Pilih menu: ");

            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Clear buffer

                switch (choice) {
                    case 1:
                        reportItem();
                        break;
                    case 2:
                        viewReportedItems();
                        break;
                    case 0:
                        System.out.println("Logout berhasil");
                        isLoggedIn = false;
                        break;
                    default:
                        System.out.println("Pilihan tidak valid!");
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("Error: Input harus berupa angka!");
                scanner.nextLine(); // Clear buffer
            }
        }
    }

    @Override
    public void reportItem() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n==== Laporkan Barang ====");
        System.out.print("Nama Barang: ");
        String itemName = scanner.nextLine();

        System.out.print("Deskripsi Barang: ");
        String description = scanner.nextLine();

        System.out.print("Lokasi Terakhir/Ditemukan: ");
        String location = scanner.nextLine();

        // Create new item and add to the reported items list
        Item newItem = new Item(itemName, description, location);
        LoginSystem.reportedItems.add(newItem);

        System.out.println("Laporan barang berhasil disimpan!");
    }

    @Override
    public void viewReportedItems() {
        System.out.println("\n==== Daftar Laporan Barang ====");

        if (LoginSystem.reportedItems.isEmpty()) {
            System.out.println("Belum ada laporan barang.");
            return;
        }

        boolean hasReportedItems = false;

        for (Item item : LoginSystem.reportedItems) {
            if (item.getStatus().equals("Reported")) {
                System.out.println("Nama Barang: " + item.getItemName());
                System.out.println("Deskripsi: " + item.getDescription());
                System.out.println("Lokasi: " + item.getLocation());
                System.out.println("--------------------------");
                hasReportedItems = true;
            }
        }

        if (!hasReportedItems) {
            System.out.println("Tidak ada barang dengan status Reported.");
        }
    }
}