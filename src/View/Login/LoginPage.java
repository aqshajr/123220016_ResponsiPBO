/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View.Login;
import View.Main.MainMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


/**
 *
 * @author ACER
 */
public class LoginPage extends JFrame implements ActionListener {
    JLabel selamat = new JLabel("Selamat Datang!");
    JLabel masuk = new JLabel("Silakan masuk untuk melanjutkan.");
    JLabel usn = new JLabel("Username");
    JTextField tusn = new JTextField(50);
    JLabel pw = new JLabel("Password");
    JTextField tpw = new JTextField(50);
    JButton login = new JButton("Login");
    
    public LoginPage(){
        setVisible(true);
        setSize(480, 470);
        setTitle("Login Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);

        add(selamat);
        selamat.setBounds(20, 15, 300, 30);
        selamat.setFont (selamat.getFont ().deriveFont (22.0f));
        
        add(masuk);
        masuk.setBounds(20, 40, 300, 30);
        masuk.setFont (masuk.getFont ().deriveFont (14.0f));
        
        add(usn);
        usn.setBounds(20, 75, 300, 30);
        usn.setFont (usn.getFont ().deriveFont (14.0f));
        add(tusn);
        tusn.setBounds(20, 105, 425, 25);
        
        add(pw);
        pw.setBounds(20, 140, 300, 30);
        pw.setFont (pw.getFont ().deriveFont (14.0f));
        add(tpw);
        tpw.setBounds(20, 170, 425, 25);
        
        add(login);
        login.setBounds(20, 280, 425, 25);
        login.setFont (login.getFont ().deriveFont (14.0f));
        login.addActionListener(this);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String username = tusn.getText();
            String password = tpw.getText();
            
            if (username.isEmpty())
            {
                throw new Exception("Username tidak boleh kosong");
            }
            
            if (password.isEmpty())
            {
                throw new Exception("Password tidak boleh kosong");
            }
            
            new MainMenu(username);
            this.dispose();
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.getMessage());
        }
        

    }
}
