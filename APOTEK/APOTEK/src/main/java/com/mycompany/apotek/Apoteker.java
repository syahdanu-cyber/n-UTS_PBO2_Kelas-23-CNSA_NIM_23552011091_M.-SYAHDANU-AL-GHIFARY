package com.mycompany.apotek;

import java.sql.*;

public class Apoteker extends TenagaMedis {
    private String bidang;

    public Apoteker(String id, String nama, String bidang) {
        super(id, nama);
        this.bidang = bidang;
    }
    
    public Apoteker() {
        super("", "");
        this.bidang = "";
    }
    
    public String getBidang() {
        return bidang;
    }

    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        System.out.println("Bidang: " + bidang);
    }
    
    @Override
    public void simpanKeDatabase() {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO apoteker (id, nama, bidang) VALUES (?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE nama = ?, bidang = ?")) {
            
            stmt.setString(1, id);
            stmt.setString(2, nama);
            stmt.setString(3, bidang);
            stmt.setString(4, nama);
            stmt.setString(5, bidang);
            
            int result = stmt.executeUpdate();
            if (result > 0) {
                System.out.println("Data apoteker berhasil disimpan!");
            }
        } catch (SQLException e) {
            System.out.println("Error menyimpan data apoteker: " + e.getMessage());
        }
    }
    
    public static Apoteker getApotekerById(String apotekerId) {
        Apoteker apoteker = null;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM apoteker WHERE id = ?")) {
            
            stmt.setString(1, apotekerId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    apoteker = new Apoteker(
                        rs.getString("id"),
                        rs.getString("nama"),
                        rs.getString("bidang")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Error mengambil data apoteker: " + e.getMessage());
        }
        return apoteker;
    }
}