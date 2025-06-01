package com.praktikum.users;

import java.util.Scanner;

public abstract class User {
    public abstract boolean login(Scanner scanner);
    public abstract void displayAppMenu();

    public abstract void displayInfo();
}