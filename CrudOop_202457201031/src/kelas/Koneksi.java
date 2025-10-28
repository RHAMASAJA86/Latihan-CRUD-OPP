package kelas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Koneksi {
    
    private static Connection mysqlconfig;
    
    public static Connection configDB() {
        
        try {
            //url database
            String url = "jdbc:mysql://localhost:3306/crudopp_202457201031";
            
            //Username database
            String user = "root";
            
            //password database
            String pass = "";
            
            mysqlconfig = DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            //Menampilkan pesan error jika koneksi gagal
            System.err.println(e.getMessage());
        }
        
        //Mengembalikan objek koneksi (bisa null jika gagal)
        return mysqlconfig;    
    }   
}