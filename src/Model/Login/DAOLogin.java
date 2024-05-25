/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Login;

import Model.Connector;
import View.Main.MainMenu;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ACER
 */
public class DAOLogin implements InterfaceDAOLogin {

@Override
public void login(ModelLogin login) {
    try {
        // Perintah query disimpan ke dalam variabel "query"
        String query = "SELECT * FROM login WHERE username = ? AND password = ?";
        
        // Memasukkan username dan password dari input user ke dalam query
        PreparedStatement statement = Connector.Connect().prepareStatement(query);
        statement.setString(1, login.getUsername()); // ? ke-1
        statement.setString(2, login.getPassword()); // ? ke-2
        
        // Menjalankan query untuk memeriksa kredensial pengguna
        ResultSet resultSet = statement.executeQuery();
        
        // Memeriksa apakah ada hasil dari query
        if (resultSet.next()) {
            System.out.println("Login sukses! Welcome, " + login.getUsername());
            new MainMenu(login.getUsername());
        } else {
            System.out.println("Username atau password salah");
        }
        
        // Menutup resultSet dan statement untuk menghemat penggunaan memory.
        resultSet.close();
        statement.close();
    } catch (SQLException e) {
        // Menampilkan pesan error ketika gagal memproses query.
        System.out.println("Login Failed: " + e.getLocalizedMessage());
    }
}

}