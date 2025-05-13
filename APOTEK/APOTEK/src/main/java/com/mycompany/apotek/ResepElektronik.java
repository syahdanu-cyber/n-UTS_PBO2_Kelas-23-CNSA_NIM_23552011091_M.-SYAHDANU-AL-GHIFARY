package com.mycompany.apotek;

import java.sql.*;

public class ResepElektronik extends MetodeResep {
    
    public ResepElektronik(String namaPasien) {
        super(namaPasien);
    }
    
    public ResepElektronik(String namaPasien, String resepId) {
        super(namaPasien);
        this.resepId = resepId;
    }

    @Override
    public void verifikasi() {
        System.out.println("Verifikasi resep elektronik untuk pasien: " + namaPasien);
        System.out.println("Cek QR code dan database rumah sakit...");
    }
    
    // Simpan resep elektronik ke database
    public void simpanKeDatabase(String pasienId, String dokterId, String tanggal) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO resep (id, pasien_id, dokter_id, tanggal, tipe) VALUES (?, ?, ?, ?, 'elektronik')")) {
            
            stmt.setString(1, resepId);
            stmt.setString(2, pasienId);
            stmt.setString(3, dokterId);
            stmt.setString(4, tanggal);
            
            int result = stmt.executeUpdate();
            if (result > 0) {
                System.out.println("Resep elektronik berhasil disimpan!");
            }
        } catch (SQLException e) {
            System.out.println("Error menyimpan resep: " + e.getMessage());
        }
    }
}