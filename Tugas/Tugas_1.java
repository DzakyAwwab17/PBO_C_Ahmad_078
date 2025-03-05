import java.util.Scanner;

public class SimpleLogin {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Data Mahasiswa (Ubah sesuai kebutuhan)
        String namaMahasiswa = "Ahmad Dzaky";
        String nimMahasiswa = "202410370110078";

        // Data Admin (3 digit terakhir dari NIM mahasiswa)
        String lastThreeDigitsNIM = nimMahasiswa.substring(nimMahasiswa.length() - 3);
        String validAdminUsername = "Admin" + lastThreeDigitsNIM;
        String validAdminPassword = "Password" + lastThreeDigitsNIM;

        // Menampilkan pilihan login
        System.out.println("Pilih login:");
        System.out.println("1. Admin");
        System.out.println("2. Mahasiswa");
        System.out.print("Masukkan pilihan: ");
        int pilihan = scanner.nextInt();
        scanner.nextLine(); // Mengonsumsi newline

        // Percabangan berdasarkan pilihan login
        if (pilihan == 1) {
            // Login sebagai Admin
            System.out.print("Masukkan username: ");
            String username = scanner.nextLine();
            System.out.print("Masukkan password: ");
            String password = scanner.nextLine();

            if (username.equals(validAdminUsername) && password.equals(validAdminPassword)) {
                System.out.println("Login Admin berhasil!");
            } else {
                System.out.println("Login gagal! Username atau password salah.");
            }

        } else if (pilihan == 2) {
            // Login sebagai Mahasiswa
            System.out.print("Masukkan Nama: ");
            String nama = scanner.nextLine();
            System.out.print("Masukkan NIM: ");
            String nim = scanner.nextLine();

            if (nama.equals(namaMahasiswa) && nim.equals(nimMahasiswa)) {
                System.out.println("Login Mahasiswa berhasil!");
                System.out.println("Nama: " + nama);
                System.out.println("NIM: " + nim);
            } else {
                System.out.println("Login gagal! Nama atau NIM salah.");
            }

        } else {
            // Pilihan tidak valid
            System.out.println("Pilihan tidak valid.");
        }

        scanner.close();
    }
}