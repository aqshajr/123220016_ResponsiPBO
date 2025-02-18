package Controller; //digunakan utk menghubungan view dengan model
//dalam controller tuh ada metode CRUD yang menghubungkan keduanya

import Model.Mahasiswa.*;
import View.Mahasiswa.*;
import java.util.List;
import javax.swing.JOptionPane;

public class ControllerMahasiswa {

    ViewDataMahasiswa halamanTable; //import dari View
    InputDataMahasiswa halamanInput;
    EditDataMahasiswa halamanEdit;

    InterfaceDAOMahasiswa daoMahasiswa; //import dari Model

    // Membuat variabel "daftarMahasiswa" untuk menyimpan data mahasiswa yg diambil dari DB.
    List<ModelMahasiswa> daftarMahasiswa;
    
    private String username;
    
    
    /*
      Kalo temen-temen liat di sini, ada 3 fungsi contructor yang masing-masing memiliki
      parameter yang berbeda. Nah, hal ini disebut dengan "function overloading",
      yaitu sebuah fungsi yang memiliki nama sama tetapi memiliki parameter yang berbeda.
      
      Mengapa kita butuh "function overloading"?
      Karena dalam hal ini, controller mahasiswa akan digunakan pada 3 halaman atau 3 view yang berbeda, 
      yaitu Halaman Table, Halaman Input, dan Halaman Edit.
    */
    public ControllerMahasiswa(ViewDataMahasiswa halamanTable, String username) { //konstruktor overloading karena berkali kali, digunakan utk membangun objek
        this.halamanTable = halamanTable;
        this.daoMahasiswa = new DAOMahasiswa();
        this.username = username;
    }
    
    public ControllerMahasiswa(InputDataMahasiswa halamanInput, String username) {
        this.halamanInput = halamanInput;
        this.daoMahasiswa = new DAOMahasiswa();
        this.username = username;

    }
    
    public ControllerMahasiswa(EditDataMahasiswa halamanEdit, String username) {
        this.halamanEdit = halamanEdit;
        this.daoMahasiswa = new DAOMahasiswa();
        this.username = username;

    }

    // metode utk read(showAllMahasiswa):halamanTable, create(insertMahasiswa):halamanInput,update(editMahasiswa):halamanEdit, delete(deleteMahasiswa)
    public void showAllMahasiswa() {
        /*
          Mengambil daftar mahasiswa dari database, 
          kemudian disimpan ke dalam variabel bernama list.
         */
        daftarMahasiswa = daoMahasiswa.getAll();

        /* LIST TO ARRAY
          Supaya daftarMahasiswa dapat dimasukkan ke dalam tabel, kita perlu 
          mengubah daftarMahasiswa yang memiliki tipe data List menjadi 
          tipe data Array Object. Jika pada pertemuan kemarin kita melakukannya
          secara manual menggunakan looping, kali ini kita tidak perlu melakukan hal tersebut,
          karena class ModelTabel sudah otomatis mengubahnya menjadi tipe data yang sesuai.
          
          Caranya kita hanya perlu membuat sebuah instance dari class ModelTable,
          kemudian kita masukkan variabel daftarMahasiswa sebagai parameter constructor
          supaya dapat diubah menjadi sebuah isi table yang dapat dimasukkan ke dalam tabel.
         */
        ModelTableMahasiswa table = new ModelTableMahasiswa(daftarMahasiswa);

        // Mengisi tabel yang berada pada halaman Table Mahasiswa
        halamanTable.getTableMahasiswa().setModel(table);
    }

    public void insertMahasiswa() { //alurnya tuh : buat wadah -> ambil data dr hal input + cek kosong/tidak -> kumpul ke satu wadah -> isi ke db pakai dao -> popup berhasil -> tutup+beralih hal
        try {
            // Membuat "mahasiswa baru" yang isinya masih kosong
            ModelMahasiswa mahasiswaBaru = new ModelMahasiswa();
            
            /*
              Mengambil input nama dan nim menggunakan getter yang telah dibuat di view
              Nilai dari input kemudian disimpan ke dalam variabel "nama" dan "nim".
            */
            String nama = halamanInput.getInputNama();
            String nim = halamanInput.getInputNIM();

            /*
              Mengecek apakah input dari nama atau nim kosong/tidak.
              Jika kosong, maka buatlah sebuah exception.
             */
            if ("".equals(nama) || "".equals(nim)) {
                throw new Exception("Nama atau NIM tidak boleh kosong!");
            }
            
            // Mengisi nama dan nim dari "mahasiswa baru" yang dibuat tadi.
            mahasiswaBaru.setNama(nama);
            mahasiswaBaru.setNim(nim);
            
            // Memasukkan "mahasiswa baru" ke dalam database.
            daoMahasiswa.insert(mahasiswaBaru);
            
            // Menampilkan pop-up ketika berhasil mengedit data
            JOptionPane.showMessageDialog(null, "Mahasiswa baru berhasil ditambahkan.");
            
            // Terakhir, program akan pindah ke halaman Table Mahasiswa()
            halamanInput.dispose(); //menutup
            new ViewDataMahasiswa(username); //beralih ke halaman view data
        } catch (Exception e) {
            // Menampilkan pop-up ketika terjadi error
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
    
    public void editMahasiswa(int id) { //dapet kiriman id yg mau diedit
        try {
            /*
              Membuat instance "mahasiswa yang mau diedit" buat 
              menyimpan informasi mahasiswa yang mau diedit.
            */
            ModelMahasiswa mahasiswaYangMauDiedit = new ModelMahasiswa();
                        
            /*
              Mengambil input nama dan nim menggunakan getter yang telah dibuat di view
              Nilai dari input kemudian disimpan ke dalam variabel "nama" dan "nim".
            */
            String nama = halamanEdit.getInputNama();
            String nim = halamanEdit.getInputNIM();

            /*
              Mengecek apakah input dari nama atau nim kosong/tidak.
              Jika kosong, maka buatlah sebuah exception.
             */
            if ("".equals(nama) || "".equals(nim)) {
                throw new Exception("Nama atau NIM tidak boleh kosong!");
            }
            
            // Mengisi id, nama dan nim dari "mahasiswa baru" yang dibuat tadi.
            mahasiswaYangMauDiedit.setId(id);
            mahasiswaYangMauDiedit.setNama(nama);
            mahasiswaYangMauDiedit.setNim(nim);
            
            // Memasukkan "mahasiswa baru" ke dalam database.
            daoMahasiswa.update(mahasiswaYangMauDiedit);

            // Menampilkan pop-up ketika berhasil mengedit data
            JOptionPane.showMessageDialog(null, "Data mahasiswa berhasil diubah.");

            // Terakhir, program akan pindah ke halaman Table Mahasiswa()
            halamanEdit.dispose();
            new ViewDataMahasiswa(username);
        } catch (Exception e) {
            // Menampilkan pop-up ketika terjadi error
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    public void deleteMahasiswa(Integer baris) { //dapet kiriman baris yg mau dihapus
        // Mengambil id dan nama berdasarkan baris yang dipilih
        Integer id = (int) halamanTable.getTableMahasiswa().getValueAt(baris, 0);
        String nama = halamanTable.getTableMahasiswa().getValueAt(baris, 1).toString();

        // Membuat Pop-Up untuk mengonfirmasi apakah ingin menghapus data
        int input = JOptionPane.showConfirmDialog(
                null,
                "Hapus " + nama + "?",
                "Hapus Mahasiswa",
                JOptionPane.YES_NO_OPTION
        );

        // Jika user memilih opsi "yes", maka hapus data.
        if (input == 0) {
            /* 
              Memanggil method delete() untuk menghaous data dari DB
              berdasarkan id yang dipilih.
            */
            daoMahasiswa.delete(id);
            
            // Menampilkan pop-up jika berhasil menghapus.
            JOptionPane.showMessageDialog(null, "Berhasil menghapus data.");

            // Memanggil method "showAllMahasiswa()" untuk merefresh table.
            showAllMahasiswa();
        }
    }
}