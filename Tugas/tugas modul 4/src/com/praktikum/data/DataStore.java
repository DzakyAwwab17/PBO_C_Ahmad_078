package com.praktikum.data;

import com.praktikum.users.User;
import com.praktikum.users.Admin;
import com.praktikum.users.Mahasiswa;
import java.util.ArrayList;
import java.util.List;

public class DataStore {
    public static List<User> daftarPengguna = new ArrayList<>();
    public static List<Item> daftarLaporan = new ArrayList<>();

    static {
        daftarPengguna.add(new Admin("admin085", "password085"));
        daftarPengguna.add(new Mahasiswa("Farah", "085"));
        daftarPengguna.add(new Mahasiswa("Faiz", "077"));
        daftarPengguna.add(new Mahasiswa("Hazza", "066"));
    }

    public static User cariPengguna(String nama, String sandi, String peran) {
        for (User u : daftarPengguna) {
            if (u.getNamaPengguna().equals(nama) && u.getKataSandi().equals(sandi) && u.getPeran().equalsIgnoreCase(peran)) {
                return u;
            }
        }

        return null;
    }
}
