package com.mycompany.apotek;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Pasien {
    private String id;
    private String nama;
    private int umur;
    
    public Pasien(String id, String nama, int umur) {
        this.id = id;
        this.nama = nama;
        this.umur = umur;
    }
    
    public Pasien() {
        this.id = "";
        this.nama = "";
        this.umur = 0;
    }
    
    public String getId() {
        return id;
    }
    
    public String getNama() {
        return nama;
    }
    
    public int getUmur() {
        return umur;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public void setNama(String nama) {
        this.nama = nama;
    }
    
    public void setUmur(int umur) {
        this.umur = umur;
    }
    
    public void tampilkanInfo() {
        System.out.println("ID Pasien: " + id);
        System.out.println("Nama Pasien: " + nama);
        System.out.println("Umur: " + umur + " tahun");
    }
    
    public void simpanKeDatabase() {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO pasien (id, nama, umur) VALUES (?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE nama = ?, umur = ?")) {
            
            stmt.setString(1, id);
            stmt.setString(2, nama);
            stmt.setInt(3, umur);
            stmt.setString(4, nama);
            stmt.setInt(5, umur);
            
            int result = stmt.executeUpdate();
            if (result > 0) {
                System.out.println("Data pasien berhasil disimpan!");
            }
        } catch (SQLException e) {
            System.out.println("Error menyimpan data pasien: " + e.getMessage());
        }
    }
    
    public static Pasien getPasienById(String pasienId) {
        Pasien pasien = null;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM pasien WHERE id = ?")) {
            
            stmt.setString(1, pasienId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    pasien = new Pasien(
                        rs.getString("id"),
                        rs.getString("nama"),
                        rs.getInt("umur")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Error mengambil data pasien: " + e.getMessage());
        }
        return pasien;
    }
    
    public static List<Pasien> getAllPasien() {
        List<Pasien> pasienList = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM pasien")) {
            
            while (rs.next()) {
                pasienList.add(new Pasien(
                    rs.getString("id"),
                    rs.getString("nama"),
                    rs.getInt("umur")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error mengambil data pasien: " + e.getMessage());
        }
        return pasienList;
    }
}