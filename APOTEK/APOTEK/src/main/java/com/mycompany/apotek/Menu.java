package com.mycompany.apotek;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private static Scanner input = new Scanner(System.in);
    
    public static void showMenu() {
        int pilihan;

        do {
            System.out.println("\n===== APOTEK MANAGEMENT SYSTEM =====");
            System.out.println("1. Data Dokter");
            System.out.println("2. Data Apoteker");
            System.out.println("3. Data Pasien");
            System.out.println("4. Data Obat");
            System.out.println("5. Pembelian Obat");
            System.out.println("6. Verifikasi Resep");
            System.out.println("0. Keluar");
            System.out.print("Pilih menu: ");
            pilihan = input.nextInt();
            input.nextLine(); // buang newline

            switch (pilihan) {
                case 1:
                    menuDokter();
                    break;
                case 2:
                    menuApoteker();
                    break;
                case 3:
                    menuPasien();
                    break;
                case 4:
                    menuObat();
                    break;
                case 5:
                    menuPembelian();
                    break;
                case 6:
                    menuResep();
                    break;
                case 0:
                    System.out.println("Terima kasih telah menggunakan sistem Apotek!");
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        } while (pilihan != 0);
    }
    
    private static void menuDokter() {
        System.out.println("\n===== MENU DOKTER =====");
        System.out.println("1. Tampilkan Data Dokter");
        System.out.println("2. Tambah Dokter");
        System.out.print("Pilih: ");
        int pilihan = input.nextInt();
        input.nextLine();
        
        switch (pilihan) {
            case 1:
                Dokter dokter = new Dokter("D001", "dr. Rani", "Spesialis Anak");
                dokter.tampilkanInfo();
                break;
            case 2:
                System.out.print("ID Dokter: ");
                String id = input.nextLine();
                System.out.print("Nama Dokter: ");
                String nama = input.nextLine();
                System.out.print("Spesialis: ");
                String spesialis = input.nextLine();
                
                Dokter newDokter = new Dokter(id, nama, spesialis);
                newDokter.simpanKeDatabase();
                break;
            default:
                System.out.println("Pilihan tidak valid!");
        }
    }
    
    private static void menuApoteker() {
        System.out.println("\n===== MENU APOTEKER =====");
        System.out.println("1. Tampilkan Data Apoteker");
        System.out.println("2. Tambah Apoteker");
        System.out.print("Pilih: ");
        int pilihan = input.nextInt();
        input.nextLine();
        
        switch (pilihan) {
            case 1:
                Apoteker apoteker = new Apoteker("A001", "Sdr. Budi", "Farmasi Klinis");
                apoteker.tampilkanInfo();
                break;
            case 2:
                System.out.print("ID Apoteker: ");
                String id = input.nextLine();
                System.out.print("Nama Apoteker: ");
                String nama = input.nextLine();
                System.out.print("Bidang: ");
                String bidang = input.nextLine();
                
                Apoteker newApoteker = new Apoteker(id, nama, bidang);
                newApoteker.simpanKeDatabase();
                break;
            default:
                System.out.println("Pilihan tidak valid!");
        }
    }
    
    private static void menuPasien() {
        System.out.println("\n===== MENU PASIEN =====");
        System.out.println("1. Tampilkan Semua Pasien");
        System.out.println("2. Tambah Pasien");
        System.out.println("3. Cari Pasien");
        System.out.print("Pilih: ");
        int pilihan = input.nextInt();
        input.nextLine();
        
        switch (pilihan) {
            case 1:
                List<Pasien> pasienList = Pasien.getAllPasien();
                if (pasienList.isEmpty()) {
                    System.out.println("Tidak ada data pasien.");
                } else {
                    System.out.println("\n===== DAFTAR PASIEN =====");
                    for (Pasien p : pasienList) {
                        p.tampilkanInfo();
                        System.out.println("------------------------");
                    }
                }
                break;
            case 2:
                System.out.print("ID Pasien: ");
                String id = input.nextLine();
                System.out.print("Nama Pasien: ");
                String nama = input.nextLine();
                System.out.print("Umur: ");
                int umur = input.nextInt();
                input.nextLine();
                
                Pasien newPasien = new Pasien(id, nama, umur);
                newPasien.simpanKeDatabase();
                break;
            case 3:
                System.out.print("Masukkan ID Pasien: ");
                String searchId = input.nextLine();
                Pasien pasien = Pasien.getPasienById(searchId);
                if (pasien != null) {
                    System.out.println("\n===== DATA PASIEN =====");
                    pasien.tampilkanInfo();
                } else {
                    System.out.println("Pasien tidak ditemukan.");
                }
                break;
            default:
                System.out.println("Pilihan tidak valid!");
        }
    }
    
    private static void menuObat() {
        System.out.println("\n===== MENU OBAT =====");
        System.out.println("1. Tampilkan Semua Obat");
        System.out.println("2. Tambah Obat");
        System.out.println("3. Cari Obat");
        System.out.print("Pilih: ");
        int pilihan = input.nextInt();
        input.nextLine();
        
        switch (pilihan) {
            case 1:
                List<Obat> obatList = Obat.getAllObat();
                if (obatList.isEmpty()) {
                    System.out.println("Tidak ada data obat.");
                } else {
                    System.out.println("\n===== DAFTAR OBAT =====");
                    for (Obat o : obatList) {
                        o.tampilkanInfo();
                        System.out.println("------------------------");
                    }
                }
                break;
            case 2:
                System.out.print("ID Obat: ");
                String id = input.nextLine();
                System.out.print("Nama Obat: ");
                String nama = input.nextLine();
                System.out.print("Harga: ");
                double harga = input.nextDouble();
                System.out.print("Stok: ");
                int stok = input.nextInt();
                input.nextLine();
                
                Obat newObat = new Obat(id, nama, harga, stok);
                newObat.simpanKeDatabase();
                break;
            case 3:
                System.out.print("Masukkan ID Obat: ");
                String searchId = input.nextLine();
                Obat obat = Obat.getObatById(searchId);
                if (obat != null) {
                    System.out.println("\n===== DATA OBAT =====");
                    obat.tampilkanInfo();
                } else {
                    System.out.println("Obat tidak ditemukan.");
                }
                break;
            default:
                System.out.println("Pilihan tidak valid!");
        }
    }
    
    private static void menuPembelian() {
        System.out.println("\n===== MENU PEMBELIAN OBAT =====");
        System.out.print("ID Transaksi: ");
        String id = input.nextLine();
        System.out.print("ID Pasien: ");
        String pasienId = input.nextLine();
        
        // Cek apakah pasien ada
        Pasien pasien = Pasien.getPasienById(pasienId);
        if (pasien == null) {
            System.out.println("Pasien tidak ditemukan!");
            return;
        }
        
        // Tanggal hari ini
        String tanggal = LocalDate.now().format(DateTimeFormatter.ISO_DATE);
        
        // Buat objek pembelian
        Pembelian pembelian = new Pembelian(id, pasienId, tanggal);
        
        // Tambahkan obat ke pembelian
        boolean lanjut = true;
        while (lanjut) {
            System.out.print("\nMasukkan ID Obat (0 untuk selesai): ");
            String obatId = input.nextLine();
            
            if (obatId.equals("0")) {
                lanjut = false;
                continue;
            }
            
            // Cek apakah obat ada
            Obat obat = Obat.getObatById(obatId);
            if (obat == null) {
                System.out.println("Obat tidak ditemukan!");
                continue;
            }
            
            System.out.print("Jumlah: ");
            int jumlah = input.nextInt();
            input.nextLine();
            
            // Cek stok
            if (jumlah > obat.getStok()) {
                System.out.println("Stok tidak mencukupi! Stok tersedia: " + obat.getStok());
                continue;
            }
            
            pembelian.tambahItem(obatId, jumlah);
        }
        
        // Tampilkan dan simpan pembelian
        pembelian.tampilkanInfo();
        System.out.print("\nSimpan transaksi? (y/n): ");
        String konfirmasi = input.nextLine();
        
        if (konfirmasi.equalsIgnoreCase("y")) {
            pembelian.simpanKeDatabase();
        }
    }
    
    private static void menuResep() {
        System.out.println("\n===== POLYMORPHISM: Verifikasi Resep =====");
        System.out.println("1. Resep Elektronik");
        System.out.println("2. Resep Tertulis");
        System.out.print("Pilih: ");
        int pilihan = input.nextInt();
        input.nextLine();
        
        System.out.print("Nama Pasien: ");
        String namaPasien = input.nextLine();
        
        MetodeResep resep;
        
        switch (pilihan) {
            case 1:
                resep = new ResepElektronik(namaPasien);
                break;
            case 2:
                resep = new ResepTertulis(namaPasien);
                break;
            default:
                System.out.println("Pilihan tidak valid!");
                return;
        }
        
        resep.prosesResep();
    }
}