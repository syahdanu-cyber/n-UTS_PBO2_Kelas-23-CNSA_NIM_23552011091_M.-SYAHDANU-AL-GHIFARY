package com.mycompany.apotek;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Pembelian {
    private String id;
    private String pasienId;
    private double total;
    private String tanggal;
    private List<DetailPembelian> items;
    
    public Pembelian(String id, String pasienId, String tanggal) {
        this.id = id;
        this.pasienId = pasienId;
        this.tanggal = tanggal;
        this.total = 0.0;
        this.items = new ArrayList<>();
    }
    
    public String getId() {
        return id;
    }
    
    public String getPasienId() {
        return pasienId;
    }
    
    public double getTotal() {
        return total;
    }
    
    public String getTanggal() {
        return tanggal;
    }
    
    public void tambahItem(String obatId, int jumlah) {
        Obat obat = Obat.getObatById(obatId);
        if (obat != null) {
            DetailPembelian item = new DetailPembelian(obatId, jumlah, obat.getHarga());
            items.add(item);
            total += (obat.getHarga() * jumlah);
            System.out.println("Obat " + obat.getNama() + " ditambahkan ke pembelian.");
        } else {
            System.out.println("Obat dengan ID " + obatId + " tidak ditemukan.");
        }
    }
    
    public void tampilkanInfo() {
        System.out.println("===== DETAIL PEMBELIAN =====");
        System.out.println("ID Transaksi: " + id);
        
        Pasien pasien = Pasien.getPasienById(pasienId);
        if (pasien != null) {
            System.out.println("Pasien: " + pasien.getNama());
        }
        
        System.out.println("Tanggal: " + tanggal);
        System.out.println("Item pembelian:");
        
        for (DetailPembelian item : items) {
            Obat obat = Obat.getObatById(item.getObatId());
            if (obat != null) {
                System.out.printf("- %s (%d) @ Rp%.2f = Rp%.2f\n", 
                    obat.getNama(), 
                    item.getJumlah(), 
                    item.getHarga(), 
                    (item.getJumlah() * item.getHarga())
                );
            }
        }
        
        System.out.printf("Total: Rp%.2f\n", total);
    }
    
    public void simpanKeDatabase() {
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false);
            
            // Simpan transaksi
            try (PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO transaksi (id, pasien_id, total, tanggal) VALUES (?, ?, ?, ?)")) {
                
                stmt.setString(1, id);
                stmt.setString(2, pasienId);
                stmt.setDouble(3, total);
                stmt.setString(4, tanggal);
                
                stmt.executeUpdate();
            }
            
            // Update stok obat dan simpan detail pembelian
            for (DetailPembelian item : items) {
                Obat obat = Obat.getObatById(item.getObatId());
                if (obat != null) {
                    // Update stok
                    obat.updateStok(item.getJumlah());
                }
            }
            
            conn.commit();
            System.out.println("Transaksi berhasil disimpan!");
            
        } catch (SQLException e) {
            try {
                if (conn != null) conn.rollback();
            } catch (SQLException ex) {
                System.out.println("Error rollback: " + ex.getMessage());
            }
            System.out.println("Error menyimpan transaksi: " + e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.setAutoCommit(true);
                    conn.close();
                }
            } catch (SQLException e) {
                System.out.println("Error closing connection: " + e.getMessage());
            }
        }
    }
    
    // Inner class untuk detail pembelian
    private class DetailPembelian {
        private String obatId;
        private int jumlah;
        private double harga;
        
        public DetailPembelian(String obatId, int jumlah, double harga) {
            this.obatId = obatId;
            this.jumlah = jumlah;
            this.harga = harga;
        }
        
        public String getObatId() {
            return obatId;
        }
        
        public int getJumlah() {
            return jumlah;
        }
        
        public double getHarga() {
            return harga;
        }
    }
}