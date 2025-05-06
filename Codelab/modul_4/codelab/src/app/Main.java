package app;

import perpustakaan.*;

public class Main {
    public static void main(String[] args) {
        Buku NonFiksi = new NonFiksi("Filosofi Teras", "Jordan Bullet", "Buku Motivasi");
        Buku Fiksi = new Fiksi("Sejarah Land Of Dawn", "Moonton", "Sejarah");

        NonFiksi.DisplayInfo();
        Fiksi.DisplayInfo();
        System.out.println();

        Anggota anggota1 = new Anggota("Damar Ghulamul Abror", "C060");
        Anggota anggota2 = new Anggota("Muhammad Ivan Fadholi", "C069");

        anggota1.DisplayInfo();
        anggota2.DisplayInfo();
        System.out.println();

        anggota1.PinjamBuku("Filosofi Teras");
        anggota2.PinjamBuku("Sejarah Land Of Dawn", 14);
        System.out.println();

        anggota1.KembalikanBuku("Filosofi Teras");
        anggota2.KembalikanBuku("Sejarah Land Of Dawn");
    }
}