package com.praktikum.users;

import com.praktikum.actions.AdminActions;
import com.praktikum.data.Item;
import com.praktikum.main.LoginSystem;

import java.util.Iterator;
import java.util.Scanner;

public class Admin extends User implements AdminActions {
    private String username;
    private String password;

    // Constructor
    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getters
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean login(Scanner scanner) {
        System.out.print("Masukkan username: ");
        String inputUsername = scanner.nextLine();
        System.out.print("Masukkan password: ");
        String inputPassword = scanner.nextLine();

        return username.equals(inputUsername) && password.equals(inputPassword);
    }

    @Override
    public void displayInfo() {
        System.out.println("Admin: " + username);
    }

    @Override
    public void displayAppMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice = -1;
        boolean isLoggedIn = true;

        while (isLoggedIn) {
            System.out.println("\n==== Admin Menu ====");
            System.out.println("1. Kelola Laporan Barang");
            System.out.println("2. Kelola Data Mahasiswa");
            System.out.println("0. Logout");
            System.out.print("Pilih menu: ");

            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Clear buffer

                switch (choice) {
                    case 1:
                        manageItems();
                        break;
                    case 2:
                        manageUsers();
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
    public void manageItems() {
        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        System.out.println("\n==== Kelola Laporan Barang ====");
        System.out.println("1. Lihat Semua Laporan");
        System.out.println("2. Tandai Barang Telah Diambil");
        System.out.println("0. Kembali");
        System.out.print("Pilih menu: ");

        try {
            choice = scanner.nextInt();
            scanner.nextLine(); // Clear buffer

            switch (choice) {
                case 1:
                    if (LoginSystem.reportedItems.isEmpty()) {
                        System.out.println("Belum ada laporan barang.");
                    } else {
                        System.out.println("\n--- Daftar Laporan Barang ---");
                        for (int i = 0; i < LoginSystem.reportedItems.size(); i++) {
                            Item item = LoginSystem.reportedItems.get(i);
                            System.out.println("No: " + i);
                            System.out.println("Nama Barang: " + item.getItemName());
                            System.out.println("Deskripsi: " + item.getDescription());
                            System.out.println("Lokasi: " + item.getLocation());
                            System.out.println("Status: " + item.getStatus());
                            System.out.println("--------------------------");
                        }
                    }
                    break;
                case 2:
                    markItemAsClaimed(scanner);
                    break;
                case 0:
                    // Do nothing, just return to the previous menu
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        } catch (java.util.InputMismatchException e) {
            System.out.println("Error: Input harus berupa angka!");
            scanner.nextLine(); // Clear buffer
        }
    }

    private void markItemAsClaimed(Scanner scanner) {
        boolean anyReported = false;

        System.out.println("\n--- Barang Yang Bisa Ditandai ---");
        for (int i = 0; i < LoginSystem.reportedItems.size(); i++) {
            Item item = LoginSystem.reportedItems.get(i);
            if (item.getStatus().equals("Reported")) {
                System.out.println("No: " + i);
                System.out.println("Nama Barang: " + item.getItemName());
                System.out.println("Deskripsi: " + item.getDescription());
                System.out.println("Lokasi: " + item.getLocation());
                System.out.println("--------------------------");
                anyReported = true;
            }
        }

        if (!anyReported) {
            System.out.println("Tidak ada barang dengan status Reported.");
            return;
        }

        System.out.print("Masukkan nomor barang yang ingin ditandai diambil: ");
        try {
            int index = scanner.nextInt();
            scanner.nextLine(); // Clear buffer

            Item item = LoginSystem.reportedItems.get(index);
            if (item.getStatus().equals("Reported")) {
                item.setStatus("Claimed");
                System.out.println("Barang " + item.getItemName() + " telah ditandai sebagai Claimed.");
            } else {
                System.out.println("Barang ini sudah ditandai sebagai Claimed sebelumnya.");
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Error: Nomor barang tidak valid!");
        } catch (java.util.InputMismatchException e) {
            System.out.println("Error: Input harus berupa angka!");
            scanner.nextLine(); // Clear buffer
        }
    }

    @Override
    public void manageUsers() {
        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        System.out.println("\n==== Kelola Data Mahasiswa ====");
        System.out.println("1. Tambah Mahasiswa");
        System.out.println("2. Hapus Mahasiswa");
        System.out.println("0. Kembali");
        System.out.print("Pilih menu: ");

        try {
            choice = scanner.nextInt();
            scanner.nextLine(); // Clear buffer

            switch (choice) {
                case 1:
                    addMahasiswa(scanner);
                    break;
                case 2:
                    removeMahasiswa(scanner);
                    break;
                case 0:
                    // Do nothing, just return to the previous menu
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        } catch (java.util.InputMismatchException e) {
            System.out.println("Error: Input harus berupa angka!");
            scanner.nextLine(); // Clear buffer
        }
    }

    private void addMahasiswa(Scanner scanner) {
        System.out.print("Masukkan Nama Mahasiswa: ");
        String nama = scanner.nextLine();
        System.out.print("Masukkan NIM Mahasiswa: ");
        String nim = scanner.nextLine();

        Mahasiswa newMahasiswa = new Mahasiswa(nama, nim);
        LoginSystem.userList.add(newMahasiswa);
        System.out.println("Mahasiswa berhasil ditambahkan!");
    }

    private void removeMahasiswa(Scanner scanner) {
        System.out.print("Masukkan NIM Mahasiswa yang akan dihapus: ");
        String nim = scanner.nextLine();

        boolean found = false;
        Iterator<User> iterator = LoginSystem.userList.iterator();

        while (iterator.hasNext()) {
            User user = iterator.next();
            if (user instanceof Mahasiswa) {
                Mahasiswa mahasiswa = (Mahasiswa) user;
                if (mahasiswa.getNim().equals(nim)) {
                    iterator.remove();
                    found = true;
                    break;
                }
            }
        }

        if (found) {
            System.out.println("Mahasiswa dengan NIM " + nim + " berhasil dihapus.");
        } else {
            System.out.println("Mahasiswa dengan NIM " + nim + " tidak ditemukan.");
        }
    }
}