package com.library.management;


import java.util.*;

class BookNotFoundException extends Exception {
    public BookNotFoundException(String message) {
        super(message);
    }
}

class EmptyTitleException extends Exception {
    public EmptyTitleException(String message) {
        super(message);
    }
}

public class LibraryManager {
    private ArrayList<String> books;

    public LibraryManager() {
        this.books = new ArrayList<>();
    }

    public void addBook(String title) throws EmptyTitleException {
        if (title == null || title.trim().isEmpty()) {
            throw new EmptyTitleException("Judul buku tidak boleh kosong!");
        }
        books.add(title.trim());
        System.out.println("Buku \"" + title.trim() + "\" berhasil ditambahkan ke perpustakaan.");
    }

    public void displayAllBooks() {
        if (books.isEmpty()) {
            System.out.println("Perpustakaan kosong. Tidak ada buku yang tersedia.");
            return;
        }

        System.out.println("\n=== DAFTAR BUKU DI PERPUSTAKAAN ===");
        Iterator<String> iterator = books.iterator();
        int index = 1;

        while (iterator.hasNext()) {
            System.out.println(index + ". " + iterator.next());
            index++;
        }
        System.out.println("Total buku: " + books.size());
    }

    public void searchBook(String title) throws BookNotFoundException {
        if (title == null || title.trim().isEmpty()) {
            throw new BookNotFoundException("Judul pencarian tidak boleh kosong!");
        }

        String searchTitle = title.trim();
        boolean found = false;

        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).equalsIgnoreCase(searchTitle)) {
                System.out.println("Buku ditemukan: \"" + books.get(i) + "\" pada posisi " + (i + 1));
                found = true;
                break;
            }
        }

        if (!found) {
            throw new BookNotFoundException("Buku dengan judul \"" + searchTitle + "\" tidak ditemukan di perpustakaan.");
        }
    }

    public void removeBook(String title) throws BookNotFoundException {
        if (title == null || title.trim().isEmpty()) {
            throw new BookNotFoundException("Judul buku yang akan dihapus tidak boleh kosong!");
        }

        String removeTitle = title.trim();
        boolean removed = false;

        Iterator<String> iterator = books.iterator();
        while (iterator.hasNext()) {
            String book = iterator.next();
            if (book.equalsIgnoreCase(removeTitle)) {
                iterator.remove();
                System.out.println("Buku \"" + book + "\" berhasil dihapus dari perpustakaan.");
                removed = true;
                break;
            }
        }

        if (!removed) {
            throw new BookNotFoundException("Buku dengan judul \"" + removeTitle + "\" tidak ditemukan untuk dihapus.");
        }
    }

    public int getBookCount() {
        return books.size();
    }

    public static void main(String[] args) {
        LibraryManager library = new LibraryManager();
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== SISTEM MANAJEMEN PERPUSTAKAAN ===");

        while (true) {
            try {
                System.out.println("\nPilih menu:");
                System.out.println("1. Tambah Buku");
                System.out.println("2. Tampilkan Semua Buku");
                System.out.println("3. Cari Buku");
                System.out.println("4. Hapus Buku");
                System.out.println("5. Keluar");
                System.out.print("Masukkan pilihan (1-5): ");

                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        System.out.print("Masukkan judul buku: ");
                        String newTitle = scanner.nextLine();
                        try {
                            library.addBook(newTitle);
                        } catch (EmptyTitleException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;

                    case 2:
                        library.displayAllBooks();
                        break;

                    case 3:
                        System.out.print("Masukkan judul buku yang dicari: ");
                        String searchTitle = scanner.nextLine();
                        try {
                            library.searchBook(searchTitle);
                        } catch (BookNotFoundException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;

                    case 4:
                        System.out.print("Masukkan judul buku yang akan dihapus: ");
                        String removeTitle = scanner.nextLine();
                        try {
                            library.removeBook(removeTitle);
                        } catch (BookNotFoundException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;

                    case 5:
                        System.out.println("Terima kasih telah menggunakan Sistem Manajemen Perpustakaan!");
                        scanner.close();
                        return;

                    default:
                        System.out.println("Pilihan tidak valid! Silakan pilih 1-5.");
                }

            } catch (InputMismatchException e) {
                System.out.println("Error: Masukkan angka yang valid!");
                scanner.nextLine(); // Clear invalid input
            } catch (Exception e) {
                System.out.println("Error tak terduga: " + e.getMessage());
            }
             }
        }
}