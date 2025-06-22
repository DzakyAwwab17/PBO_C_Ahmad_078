package com.praktikum.users;

public abstract class User {
    protected String namaPengguna;
    protected String kataSandi;

    public User(String namaPengguna, String kataSandi) {
        this.namaPengguna = namaPengguna;
        this.kataSandi = kataSandi;
    }

    public String getNamaPengguna() {

        return namaPengguna;

    }

    public String getKataSandi() {

        return kataSandi;

    }

    public abstract String getPeran();
}
