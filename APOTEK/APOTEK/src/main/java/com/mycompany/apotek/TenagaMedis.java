package com.mycompany.apotek;

import java.sql.*;

public class TenagaMedis {
    protected String id;
    protected String nama;

    public TenagaMedis(String id, String nama) {
        this.id = id;
        this.nama = nama;
    }

    public String getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public void tampilkanInfo() {
        System.out.println("ID: " + id);
        System.out.println("Nama: " + nama);
    }
    
    // Method ini bisa dioverride oleh subclass
    public void simpanKeDatabase() {
        System.out.println("Data Tenaga Medis tersimpan");
    }
}