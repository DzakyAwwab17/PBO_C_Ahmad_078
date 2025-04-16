import java.util.ArrayList;
import java.util.Scanner;

class Admin {
    private String username = "semicolon";
    private String password = "semicolon17";

    public boolean login(String inputUsername, String inputPassword) {
        return this.username.equals(inputUsername) && this.password.equals(inputPassword);
    }
}

class Mahasiswa {
    private String nama;
    private String nim;

    public Mahasiswa(String nama, String nim) {
        this.nama = nama;
        this.nim = nim;
    }

    public boolean login(String inputNama, String inputNim) {
        return this.nama.equals(inputNama) && this.nim.equals(inputNim);
    }

    public void displayInfo() {
        System.out.println("Nama: " + this.nama);
        System.out.println("NIM: " + this.nim);
    }
}

public class LoginSystem {
    private ArrayList<Mahasiswa> dataMahasiswa;

    public LoginSystem() {
        dataMahasiswa = new ArrayList<>();
        // Adding student data
        dataMahasiswa.add(new Mahasiswa("Faiz Hidayat", "202410370110077"));
        dataMahasiswa.add(new Mahasiswa("Ahmad Dzaky Awwab", "202410370110078"));
        dataMahasiswa.add(new Mahasiswa("Ani Setiawan", "202410370110079"));
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        Admin admin = new Admin();

        while (true) {
            // Prompts for login
            System.out.println("\nPilih login:");
            System.out.println("1. Admin");
            System.out.println("2. Mahasiswa");
            System.out.println("3. Keluar");
            System.out.print("Pilihan Anda: ");

            int pilihan;
            try {
                pilihan = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Masukkan angka yang valid!");
                scanner.nextLine(); // Clear input
                continue;
            }

            scanner.nextLine(); // Consume newline

            // Admin login process
            if (pilihan == 1) {
                System.out.println("\nLogin sebagai Admin");
                System.out.print("Masukkan username: ");
                String username = scanner.nextLine();
                System.out.print("Masukkan password: ");
                String password = scanner.nextLine();

                if (admin.login(username, password)) {
                    System.out.println("Login berhasil sebagai Admin");
                } else {
                    System.out.println("Username atau password Admin salah.");
                }
            }
            // Mahasiswa login process
            else if (pilihan == 2) {
                System.out.println("\nLogin sebagai Mahasiswa");
                System.out.print("Masukkan nama: ");
                String nama = scanner.nextLine();
                System.out.print("Masukkan NIM: ");
                String nim = scanner.nextLine();

                boolean found = false;
                for (Mahasiswa mhs : dataMahasiswa) {
                    if (mhs.login(nama, nim)) {
                        System.out.println("Login berhasil sebagai Mahasiswa");
                        mhs.displayInfo();
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    System.out.println("Nama atau NIM Mahasiswa salah.");
                }
            }
            // Exit option
            else if (pilihan == 3) {
                System.out.println("Terima kasih telah menggunakan sistem ini!");
                break;
            } else {
                System.out.println("Pilihan tidak valid, coba lagi.");
            }
        }

        scanner.close();
    }

    public static void main(String[] args) {
        LoginSystem loginSystem = new LoginSystem();
        loginSystem.start();
  }
}