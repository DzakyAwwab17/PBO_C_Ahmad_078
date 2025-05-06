package perpustakaan;

public interface Peminjaman {

    void PinjamBuku(String judul, int durasi);
    void KembalikanBuku(String judul);
}
