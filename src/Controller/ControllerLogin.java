/* MAAF BELUM SELESAI :(



 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template

package Controller;

import View.Login.LoginPage;

public class ControllerLogin {
    LoginPage halamanLogin; //import dari View

    InterfaceDAOLogin daoLogin; //import dari Model
    
    private String username;
    

    public ControllerMahasiswa(LoginPage halamanLogin, String username) { //konstruktor overloading karena berkali kali, digunakan utk membangun objek
        this.halamanLogin = halamanLogin;
        this.daoLogin = new DAOLogin();
        this.username = username;
    }

    // metode utk read(showAllMahasiswa):halamanTable, create(insertMahasiswa):halamanInput,update(editMahasiswa):halamanEdit, delete(deleteMahasiswa)
    public void daoLogin() { //alurnya tuh : buat wadah -> ambil data dr hal input + cek kosong/tidak -> kumpul ke satu wadah -> isi ke db pakai dao -> popup berhasil -> tutup+beralih hal
        try {

            String username = halamanLogin.getUsername();
            String password = halamanLogin.getPassword();

            login.setUsername(username);
            login.setPassword(password);
            
            // Memasukkan "mahasiswa baru" ke dalam database.
            daoLogin.insert(login);
            
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

            ModelMahasiswa mahasiswaYangMauDiedit = new ModelMahasiswa();
                        

            String nama = halamanEdit.getInputNama();
            String nim = halamanEdit.getInputNIM();


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

            daoMahasiswa.delete(id);
            
            // Menampilkan pop-up jika berhasil menghapus.
            JOptionPane.showMessageDialog(null, "Berhasil menghapus data.");

            // Memanggil method "showAllMahasiswa()" untuk merefresh table.
            showAllMahasiswa();
        }
    }
}

*/
