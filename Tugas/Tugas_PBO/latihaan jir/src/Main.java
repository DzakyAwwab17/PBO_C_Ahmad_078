class manusia
{

    int umur;

    String nama;

    public manusia(int umur, String nama) {
        this.umur = umur;
        this.nama = nama;
    }
}
class mahasiswa extends manusia
{

    int nim;

    public mahasiswa(int umur, String nama, int nim) {
        super(umur, nama);
        this.nim = nim;
    }

    public void print() {}
}