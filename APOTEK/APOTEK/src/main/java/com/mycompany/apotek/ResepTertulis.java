package com.mycompany.apotek;

import java.sql.*;

public class ResepTertulis extends MetodeResep {
    
    public ResepTertulis(String namaPasien) {
        super(namaPasien);
    }
    
    public ResepTertulis(String namaPasien, String resepId) {
        super(namaPasien);
        this.resepId = resepId;
    }

    @Override
    public void verifikasi() {
        System.out.println("Verifikasi resep tertulis untuk pasien: " + namaPasien);
        System.out.println("Cek tanda tangan dan stempel dokter...");
    }
    
    // Simpan resep tertulis ke database
    public void simpanKeDatabase(String pasienId, String dokterId, String tanggal) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO resep (id, pasien_id, dokter_id, tanggal, tipe) VALUES (?, ?, ?, ?, 'tertulis')")) {
            
            stmt.setString(1, resepId);
            stmt.setString(2, pasienId);
            stmt.setString(3, dokterId);
            stmt.setString(4, tanggal);
            
            int result = stmt.executeUpdate();
            if (result > 0) {
                System.out.println("Resep tertulis berhasil disimpan!");
            }
        } catch (SQLException e) {
            System.out.println("Error menyimpan resep: " + e.getMessage());
        }
    }
}