package com.mycompany.apotek;

import java.sql.*;

public class Dokter extends TenagaMedis {
    private String spesialis;

    public Dokter(String id, String nama, String spesialis) {
        super(id, nama);
        this.spesialis = spesialis;
    }
    
    public Dokter() {
        super("", "");
        this.spesialis = "";
    }
    
    public String getSpesialis() {
        return spesialis;
    }

    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        System.out.println("Spesialis: " + spesialis);
    }
    
    @Override
    public void simpanKeDatabase() {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO dokter (id, nama, spesialis) VALUES (?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE nama = ?, spesialis = ?")) {
            
            stmt.setString(1, id);
            stmt.setString(2, nama);
            stmt.setString(3, spesialis);
            stmt.setString(4, nama);
            stmt.setString(5, spesialis);
            
            int result = stmt.executeUpdate();
            if (result > 0) {
                System.out.println("Data dokter berhasil disimpan!");
            }
        } catch (SQLException e) {
            System.out.println("Error menyimpan data dokter: " + e.getMessage());
        }
    }
    
    public static Dokter getDokterById(String dokterId) {
        Dokter dokter = null;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM dokter WHERE id = ?")) {
            
            stmt.setString(1, dokterId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    dokter = new Dokter(
                        rs.getString("id"),
                        rs.getString("nama"),
                        rs.getString("spesialis")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Error mengambil data dokter: " + e.getMessage());
        }
        return dokter;
    }
}