package com.mycompany.apotek;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Obat {
    private String id;
    private String nama;
    private double harga;
    private int stok;
    
    public Obat(String id, String nama, double harga, int stok) {
        this.id = id;
        this.nama = nama;
        this.harga = harga;
        this.stok = stok;
    }
    
    public String getId() {
        return id;
    }
    
    public String getNama() {
        return nama;
    }
    
    public double getHarga() {
        return harga;
    }
    
    public int getStok() {
        return stok;
    }
    
    public void setStok(int stok) {
        this.stok = stok;
    }
    
    public void tampilkanInfo() {
        System.out.println("ID Obat: " + id);
        System.out.println("Nama Obat: " + nama);
        System.out.println("Harga: Rp" + harga);
        System.out.println("Stok: " + stok);
    }
    
    public static Obat getObatById(String obatId) {
        Obat obat = null;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM obat WHERE id = ?")) {
            
            stmt.setString(1, obatId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    obat = new Obat(
                        rs.getString("id"),
                        rs.getString("nama"),
                        rs.getDouble("harga"),
                        rs.getInt("stok")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Error mengambil data obat: " + e.getMessage());
        }
        return obat;
    }
    
    public static List<Obat> getAllObat() {
        List<Obat> obatList = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM obat")) {
            
            while (rs.next()) {
                obatList.add(new Obat(
                    rs.getString("id"),
                    rs.getString("nama"),
                    rs.getDouble("harga"),
                    rs.getInt("stok")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error mengambil data obat: " + e.getMessage());
        }
        return obatList;
    }
    
    public void simpanKeDatabase() {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO obat (id, nama, harga, stok) VALUES (?, ?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE nama = ?, harga = ?, stok = ?")) {
            
            stmt.setString(1, id);
            stmt.setString(2, nama);
            stmt.setDouble(3, harga);
            stmt.setInt(4, stok);
            stmt.setString(5, nama);
            stmt.setDouble(6, harga);
            stmt.setInt(7, stok);
            
            int result = stmt.executeUpdate();
            if (result > 0) {
                System.out.println("Data obat berhasil disimpan!");
            }
        } catch (SQLException e) {
            System.out.println("Error menyimpan data obat: " + e.getMessage());
        }
    }
    
    public void updateStok(int jumlah) {
        stok -= jumlah;
        if (stok < 0) stok = 0;
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                "UPDATE obat SET stok = ? WHERE id = ?")) {
            
            stmt.setInt(1, stok);
            stmt.setString(2, id);
            
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error mengupdate stok obat: " + e.getMessage());
        }
    }
}