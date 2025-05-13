package com.mycompany.apotek;

public class APOTEK {
    public static void main(String[] args) {
        try {
            // Cek koneksi database saat aplikasi dimulai
            DBConnection.getConnection();
            System.out.println("Database terhubung!");
        } catch (Exception e) {
            System.out.println("Error koneksi database: " + e.getMessage());
            return;
        }
        
        Menu.showMenu();
    }
}