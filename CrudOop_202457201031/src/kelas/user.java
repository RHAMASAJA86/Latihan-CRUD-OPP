package kelas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class user extends Koneksi {
    private String userName, userEmail, userPassword, userFullName;
    private int userStatus;
    private final Connection koneksi;
    private  PreparedStatement ps;
    private Statement st;
    private ResultSet rs;
    private String query;
    
    public user() {
        koneksi = super.configDB();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public int getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(int userStatus) {
        this.userStatus = userStatus;
    } 
    
    public void tambahUser() {
        query = "INSERT INTO user VALUES (?,?,MD5(?),?,?)";
        
        try {
            ps = koneksi.prepareStatement(query);
            ps.setString(1, userName);
            ps.setString(2, userEmail);
            ps.setString(3, userPassword);
            ps.setString(4, userFullName);
            ps.setInt(5, userStatus);
            ps.executeUpdate();
            ps.close();
            
            JOptionPane.showMessageDialog(null, "Data berhasil di tampilkan");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data gagal di tambahkan");
        }  
    }
    
    public void ubahUSer() {
        query = "UPDATE user SET userEmail = ?, userPassword = MD5(?), userFullName = ?, userStatus = ? WHERE userName = ?";
        
        try {
            ps = koneksi.prepareStatement(query);
            ps.setString(1, userName);
            ps.setString(2, userEmail);
            ps.setString(3, userPassword);
            ps.setString(4, userFullName);
            ps.setInt(5, userStatus);
            ps.executeUpdate();
            ps.close();
            
            JOptionPane.showMessageDialog(null, "Data berhasil di ubah");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data gagal di ubah");
        }  
    }
    
    public void hapusUser() {
        query = "UPDATE INTO user WHERE userName = ?";
        
        try {
            ps = koneksi.prepareStatement(query);
            ps.setString(1, userName);
            ps.executeUpdate();
            ps.close();
            
            JOptionPane.showMessageDialog(null, "Data berhasil di hapus");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data gagal di hapus");
        }  
    }  
    
    public ResultSet TampilUser() {
        query ="SELECT * FROM user";
        
        try {
            st = koneksi.createStatement();
            rs = st.executeQuery(query);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data gagal di tampilkan");
        }
        
        return rs;
    }
}