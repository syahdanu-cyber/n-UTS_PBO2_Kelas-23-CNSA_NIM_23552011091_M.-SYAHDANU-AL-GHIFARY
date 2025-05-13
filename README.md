# UTS Pemrograman Berorientasi Obyek 2
<ul>
  <li>Mata Kuliah: Pemrograman Berorientasi Obyek 2</li>
  <li>Dosen Pengampu: <a href="https://github.com/Muhammad-Ikhwan-Fathulloh">Muhammad Ikhwan Fathulloh</a></li>
</ul>

## Profil
<ul>
  <li>Nama: M. SYAHDANU AL GHIFARY</li>
  <li>NIM: 23552011091</li>
  <li>Studi Kasus: Kasir Apotek</li>
</ul>

## Judul Studi Kasus
<p>Kasir Apotek.</p>

## Penjelasan Studi Kasus
<p>1. Tenaga Medis
Kelas yang menjadi dasar untuk Dokter dan Apoteker. Menyimpan informasi dasar seperti ID dan nama tenaga medis.
2. Dokter dan Apoteker
Kelas yang mewarisi dari Tenaga Medis. Menyimpan informasi spesialisasi dan bidang masing-masing. Memiliki metode untuk menampilkan informasi dan menyimpan data ke database.
3. Pasien
Kelas yang menyimpan informasi pasien, seperti ID, nama, dan umur. Memiliki metode untuk menampilkan informasi dan menyimpan data pasien ke database.
4. Obat
Kelas yang menyimpan informasi tentang obat, termasuk ID, nama, harga, dan stok. Memiliki metode untuk menampilkan informasi, menyimpan data obat, dan mengupdate stok.
5. Pembelian
Kelas yang mengelola transaksi pembelian obat. Menyimpan detail pembelian, menghitung total harga, dan menyimpan transaksi ke database.
6. Resep
Terdiri dari dua jenis: ResepTertulis dan ResepElektronik, yang mewarisi dari kelas MetodeResep. Mengelola verifikasi resep dan penyimpanan data resep ke database.
7. Menu
Kelas yang menyediakan antarmuka pengguna untuk mengakses fitur sistem, seperti manajemen dokter, apoteker, pasien, obat, dan pembelian.
8. DBConnection
Kelas yang mengelola koneksi ke database MySQL untuk menyimpan dan mengambil data.
9. APOTEK
Kelas utama yang menjalankan aplikasi, memeriksa koneksi database, dan memanggil menu.</p>

## Penjelasan 4 Pilar OOP dalam Studi Kasus

### 1. Inheritance
<p>Inheritance (Pewarisan)
•	Contoh: Kelas Dokter dan Apoteker mewarisi dari kelas TenagaMedis.
•	Penjelasan: Kelas Dokter dan Apoteker mengambil atribut dan metode dari TenagaMedis, seperti id dan nama, sehingga tidak perlu mendefinisikannya ulang. Ini memungkinkan kode menjadi lebih terorganisir dan mudah dikelola
.</p>

### 2. Encapsulation
<p>. Encapsulation (Enkapsulasi)
•	Contoh: Kelas Pasien, Obat, dan Pembelian menggunakan modifier akses private untuk atribut mereka.
•	Penjelasan: Dengan menjadikan atribut private, akses ke data dibatasi hanya melalui metode publik (getter dan setter). Ini melindungi data dari modifikasi langsung dan memastikan bahwa data hanya dapat diubah melalui metode yang telah ditentukan.
.</p>

### 3. Polymorphism
<p>Polymorphism (Polimorfisme)
•	Contoh: Kelas MetodeResep memiliki metode abstrak verifikasi(), yang diimplementasikan di kelas ResepTertulis dan ResepElektronik.
•	Penjelasan: Polimorfisme memungkinkan kita untuk menggunakan objek dari kelas yang berbeda (seperti ResepTertulis dan ResepElektronik) melalui referensi kelas induk (MetodeResep). Metode verifikasi() dapat dipanggil tanpa mengetahui tipe spesifik objek yang digunakan.
.</p>

### 4. Abstract
<p>Abstraction (Abstraksi)
•	Contoh: Kelas MetodeResep adalah kelas abstrak yang mendefinisikan metode verifikasi() tanpa implementasi.
•	Penjelasan: Abstraksi menyembunyikan detail implementasi dan hanya menampilkan fungsionalitas penting. Pengguna dapat menggunakan metode prosesResep() tanpa harus tahu bagaimana verifikasi() diimplementasikan di setiap subkelas
.</p>

## Demo Proyek
<ul>
  <li>Github: <a href="https://github.com/syahdanu-cyber/n-UTS_PBO2_Kelas-23-CNSA_NIM_23552011091_M.-SYAHDANU-AL-GHIFARY/tree/main">Github</a></li>
  <li>Youtube: <a href="https://youtu.be/KnGXdXDPDR4">Youtube</a></li>
</ul>
