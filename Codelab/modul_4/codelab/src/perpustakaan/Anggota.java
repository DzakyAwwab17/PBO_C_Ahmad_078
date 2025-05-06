package perpustakaan;

public class Anggota implements Peminjaman {
    private String nama;
    private String idAnggota;

    public Anggota(String nama, String idAnggota) {
        this.nama = nama;
        this.idAnggota = idAnggota;
    }

    public void PinjamBuku(String judul) {
        System.out.println(nama + " meminjam buku berikut: " + judul);
    }

    public void PinjamBuku(String judul, int durasi) {
        System.out.println(nama + " meminjam buku \"" + judul + "\" selama " + durasi + " hari.");
    }

    @Override
    public void KembalikanBuku(String judul) {
        System.out.println(nama + " mengembalikan buku berikut: " + judul);
    }

    public void DisplayInfo() {
        System.out.println("Anggota: " + nama + " (ID: " + idAnggota + ")");
    }
}