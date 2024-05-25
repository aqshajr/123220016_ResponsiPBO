package Controller;

import Model.Dosen.DAODosen;
import Model.Dosen.InterfaceDAODosen;
import Model.Dosen.ModelDosen;
import Model.Dosen.ModelTableDosen;
import View.Dosen.EditDataDosen;
import View.Dosen.InputDataDosen;
import View.Dosen.ViewDataDosen;
import java.util.List;
import javax.swing.JOptionPane;

public class ControllerDosen {
    ViewDataDosen halamanTable;
    InputDataDosen halamanInput;
    EditDataDosen halamanEdit;

    InterfaceDAODosen daoDosen;

    List<ModelDosen> daftarDosen;
    private String username;

    
    public ControllerDosen(ViewDataDosen halamanTable, String username) {
        this.halamanTable = halamanTable;
        this.daoDosen = new DAODosen();
        this.username = username;
    }
    
    public ControllerDosen(InputDataDosen halamanInput, String username) {
        this.halamanInput = halamanInput;
        this.daoDosen = new DAODosen();
        this.username = username;

    }
    
    public ControllerDosen(EditDataDosen halamanEdit, String username) {
        this.halamanEdit = halamanEdit;
        this.daoDosen = new DAODosen();
        this.username = username;
    }

    public void showAllDosen() {
        daftarDosen = daoDosen.getAll();

        ModelTableDosen table = new ModelTableDosen(daftarDosen);

        halamanTable.getTableDosen().setModel(table);
    }

    public void insertDosen() {
        try {
            ModelDosen dosenBaru = new ModelDosen();
            
            String nama = halamanInput.getInputNama();
            String nidn = halamanInput.getInputNIDN();

            if ("".equals(nama) || "".equals(nidn)) {
                throw new Exception("Nama atau NIDN tidak boleh kosong!");
            }
            
            dosenBaru.setNama(nama);
            dosenBaru.setNidn(nidn);
            
            daoDosen.insert(dosenBaru);
            
            JOptionPane.showMessageDialog(null, "Dosen baru berhasil ditambahkan.");
            
            halamanInput.dispose();
            new ViewDataDosen(username);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
    
    public void editDosen(int id) {
        try {
            ModelDosen dosenYangMauDiedit = new ModelDosen();
                        
            String nama = halamanEdit.getInputNama();
            String nidn = halamanEdit.getInputNIDN();

            if ("".equals(nama) || "".equals(nidn)) {
                throw new Exception("Nama atau NIDN tidak boleh kosong!");
            }
            
            dosenYangMauDiedit.setId(id);
            dosenYangMauDiedit.setNama(nama);
            dosenYangMauDiedit.setNidn(nidn);
            
            daoDosen.update(dosenYangMauDiedit);

            JOptionPane.showMessageDialog(null, "Data dosen berhasil diubah.");

            halamanEdit.dispose();
            new ViewDataDosen(username);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    public void deleteDosen(Integer baris) {
        Integer id = (int) halamanTable.getTableDosen().getValueAt(baris, 0);
        String nama = halamanTable.getTableDosen().getValueAt(baris, 1).toString();

        int input = JOptionPane.showConfirmDialog(
                null,
                "Hapus " + nama + "?",
                "Hapus Dosen",
                JOptionPane.YES_NO_OPTION
        );

        if (input == 0) {
            daoDosen.delete(id);
            
            JOptionPane.showMessageDialog(null, "Berhasil menghapus data.");

            showAllDosen();
        }
    }
}
