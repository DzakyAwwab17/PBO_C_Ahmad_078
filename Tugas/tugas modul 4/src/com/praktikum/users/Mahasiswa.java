package com.praktikum.users;

public class Mahasiswa extends User {
    public Mahasiswa(String namaPengguna, String kataSandi) {
        super(namaPengguna, kataSandi);
    }

    @Override
    public String getPeran() {
        return "Mahasiswa";
    }
}
