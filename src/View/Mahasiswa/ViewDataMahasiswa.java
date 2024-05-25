package View.Mahasiswa;//view itu isinya desain jframe/halaman = halaman data

import Controller.ControllerMahasiswa;
import Model.Mahasiswa.ModelMahasiswa;
import View.Main.MainMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ViewDataMahasiswa extends JFrame { //kalo tampilan mewarisi JFrame

    /* 
      Membuat variabel "baris" untuk menyimpan baris ke berapa yang dipilih saat 
      user memilih salah satu data yang ada di tabel 
     */
    Integer baris;

    // Membuat sebuah instance bernama controller dari class "ControllerMahasiswa".
    ControllerMahasiswa controller;
    private String username;


    // Menginisiasi komponen
    JLabel header = new JLabel("Selamat Datang!");
    JButton tombolTambah = new JButton("Tambah Mahasiswa");
    JButton tombolEdit = new JButton("Edit Mahasiswa");
    JButton tombolHapus = new JButton("Hapus Mahasiswa");
    JButton tombolKembali = new JButton("Kembali"); 
    JTextField cari = new JTextField(30);
    JButton tombolCari = new JButton("Cari");


    /*
      Untuk membuat Tabel, kita memerlukan 3 komponen, yaitu:
      1. JTable sebagai komponen tabelnya
      2. DefaultTableModel untuk model atau isinya
      3. JScrollPane supaya tabel dapat di-scroll saat datanya melebihi ukuran layar.
     */
    JTable table;
    DefaultTableModel tableModel;
    JScrollPane scrollPane;

    /*
      Nama kolom tabelnya disimpan ke dalam variabel "namaKolom" yang memiliki 
      tipe data Array String.
     */
    String namaKolom[] = {"ID", "Nama", "NIM"};

    public ViewDataMahasiswa(String username) {
        this.username = username; 
        tableModel = new DefaultTableModel(namaKolom, 0); //0 nih jumlah baris awal
        table = new JTable(tableModel);
        scrollPane = new JScrollPane(table);

        setTitle("Daftar Mahasiswa");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(552, 540);
        setLocationRelativeTo(null);
        setLayout(null);

        add(header);
        add(scrollPane);
        add(tombolTambah);
        add(tombolEdit);
        add(tombolHapus);
        add(tombolKembali);
        add(cari);
        add(tombolCari);

        header.setBounds(20, 8, 440, 24);
        scrollPane.setBounds(20, 36, 512, 320);
        tombolTambah.setBounds(20, 370, 512, 40);
        tombolEdit.setBounds(20, 414, 512, 40);
        tombolHapus.setBounds(20, 456, 512, 40);
        tombolKembali.setBounds(430, 8, 100,24);
        cari.setBounds(150, 8, 200,24);
        tombolCari.setBounds(350, 8, 70,24);
        
        /*
          Memanggil method showData() dari controller untuk
          mengisi tabel dengan data yang diambil dari DB
         */
        controller = new ControllerMahasiswa(this,username);
        controller.showAllMahasiswa();

        // Menambahkan event handling ketika salah satu baris di tabel dipilih
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                // Mengambil baris ke-n dari tabel
                baris = table.getSelectedRow();
            }
        });

        // Memberikan event handling ketika tombol "Tambah Mahasiswa" diklik
        tombolTambah.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Ketika tombol tambah diklik, maka program akan berpindah ke halaman InputData()
                dispose();
                new InputDataMahasiswa(username);
            }
        });

        // Memberikan event handling ketika tombol "Edit Mahasiswa" diklik
        tombolEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { //-> cek pilihan baris ->buat wadah -> ambildata -> kumpulkan di wadah -> teruskan
                // Mengecek apakah ada baris di dalam tabel yang dipilih atau tidak
                if (baris != null) {
                    /*
                      Membuat instance "mahasiswa" yang digunakan untuk menyimpan 
                      informasi mahasiswa yang diklik di table.
                    */
                    ModelMahasiswa mahasiswaTerpilih = new ModelMahasiswa();
                    
                    // Mengambil id dan nama berdasarkan baris yang dipilih
                    Integer id = (int) table.getValueAt(baris, 0); //0, 1, 2 adalah indeks kolom
                    String nama = table.getValueAt(baris, 1).toString();
                    String nim = table.getValueAt(baris, 2).toString();
                    
                    // Menyimpan informasi id, nama, dan nim ke objek "mahasiswaTerpilih".
                    mahasiswaTerpilih.setId(id);
                    mahasiswaTerpilih.setNama(nama);
                    mahasiswaTerpilih.setNim(nim);

                    /* 
                      Ketika tombol edit diklik, maka program akan berpindah ke 
                      halaman EditData() dengan membawa id, nama, dan nim untuk
                      diberikan ke halaman EditData()
                     */
                    dispose();
                    new EditDataMahasiswa(mahasiswaTerpilih, username);
                } else {
                    JOptionPane.showMessageDialog(null, "Data belum dipilih.");
                }
            }
        });

        // Memberikan event handling ketika tombol "Hapus Mahasiswa" diklik
        tombolHapus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Mengecek apakah ada baris di dalam tabel yang dipilih atau tidak
                if (baris != null) {
                    controller.deleteMahasiswa(baris);
                    
                    /*
                      Mengembalikan nilai dari variabel baris ke null. Kenapa?
                      Karena halaman tidak dimuat ulang, hanya tabel yang direfresh.
                      Sehingga variabel baris harus dikembalikan ke value awalnya.
                      
                      Jika tidak, maka setelah user menghapus mahasiswa,
                      lalu langsung menekan tombol "Edit" atau "Hapus", maka akan 
                      terjadi error karena variabel baris masih menyimpan nilai lama,
                      sedangkan baris yang lama sudah terhapus.
                    */
                    baris = null;
                } else {
                    JOptionPane.showMessageDialog(null, "Data belum dipilih.");
                }
            }
        });
        
        tombolKembali.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new MainMenu(username);
            }
        });
    }
    
    //ini event handling tombolCari tapi belum selesai
        /*tombolCari.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
            try {
            String find = cari.getText();
            
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.getMessage());
        }
        

    }
    }*/
    

    public JTable getTableMahasiswa() {
        return table;
    }
}