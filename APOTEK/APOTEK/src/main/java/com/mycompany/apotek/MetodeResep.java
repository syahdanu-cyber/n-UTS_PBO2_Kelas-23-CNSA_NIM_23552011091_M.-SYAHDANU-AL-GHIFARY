package com.mycompany.apotek;

public abstract class MetodeResep {
    protected String namaPasien;
    protected String resepId;

    public MetodeResep(String namaPasien) {
        this.namaPasien = namaPasien;
    }
    
    public String getNamaPasien() {
        return namaPasien;
    }
    
    public String getResepId() {
        return resepId;
    }
    
    public void setResepId(String resepId) {
        this.resepId = resepId;
    }

    public abstract void verifikasi();
    
    // Method untuk memproses resep
    public void prosesResep() {
        System.out.println("Memproses resep untuk pasien: " + namaPasien);
        verifikasi();
        System.out.println("Resep telah diproses!");
    }
}