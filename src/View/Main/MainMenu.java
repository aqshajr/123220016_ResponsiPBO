package View.Main;

import View.Dosen.ViewDataDosen;
import View.Login.LoginPage;
import View.Mahasiswa.ViewDataMahasiswa;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class MainMenu extends JFrame {
    JLabel header = new JLabel("Program Pendataan Mahasiswa dan Dosen");
    JButton tombolMahasiswa = new JButton("Data Mahasiswa");
    JButton tombolDosen = new JButton("Data Dosen");
    JLabel welcome = new JLabel();
    JButton tombolLogout = new JButton("Logout"); // Add logout button

    
    public MainMenu(String username){

        setTitle("Program Pendataan");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(552, 540);
        setLocationRelativeTo(null);
        setVisible(true);
        setLayout(null);
        
        add(header);
        add(tombolMahasiswa);
        add(tombolDosen);
        add(tombolLogout);

        add(welcome);
        welcome.setText("Selamat Datang!" + username);
        welcome.setBounds(20, 15, 310, 30);
        welcome.setFont (welcome.getFont ().deriveFont (20.0f));
        
        header.setBounds(45, 80, 440, 24);
        tombolMahasiswa.setBounds(15, 230, 512, 40);
        tombolDosen.setBounds(15, 290, 512, 40);
        tombolLogout.setBounds(15, 350, 512, 40); // Set bounds for logout button

        
        header.setFont (header.getFont ().deriveFont (20.0f));
        header.setHorizontalAlignment(SwingConstants.CENTER);


        tombolMahasiswa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Ketika tombol tambah diklik, maka program akan berpindah ke halaman InputData()
                dispose();
                new ViewDataMahasiswa(username);
            }
        });
                        
        tombolDosen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Ketika tombol tambah diklik, maka program akan berpindah ke halaman InputData()
                dispose();
                new ViewDataDosen(username);
            }
        });

         tombolLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new LoginPage();
            }
        });

    }
       


}
