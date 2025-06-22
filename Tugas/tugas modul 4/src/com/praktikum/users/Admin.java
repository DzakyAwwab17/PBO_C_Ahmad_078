package com.praktikum.users;

public class Admin extends User{
    public Admin(String namaPengguna, String kataSandi) {

        super(namaPengguna, kataSandi);
    }

    @Override
    public String getPeran(){
        return "Admin";
    }
}
