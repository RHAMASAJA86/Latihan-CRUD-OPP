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
            ps.setString(1, this.userName);
            ps.setString(2, this.userEmail);
            ps.setString(3, this.userPassword);
            ps.setString(4, this.userFullName);
            ps.setInt(5, this.userStatus);
            ps.executeUpdate();
            ps.close();
            
            JOptionPane.showMessageDialog(null, "Data berhasil di tambahkan");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data gagal di tambahkan" + e.getMessage());
        }  
    }
    
    public void ubahUser() {
        if (this.userPassword.equals("")) {
            
            try {
                query = "UPDATE user SET userEmail = ?, userFullName = ?, userStatus = ? WHERE userName = ?";
                
                ps = koneksi.prepareStatement(query);
                ps.setString(1, this.userEmail);
                ps.setString(2, this.userFullName);
                ps.setInt(3, this.userStatus);
                ps.setString(4, this.userName);
                ps.executeUpdate();
                ps.close();

                JOptionPane.showMessageDialog(null, "Data berhasil diubah");

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "gagal : " + e.getMessage());
            }
        } else {
            
            try {
                query = "UPDATE user SET userEmail = ?, userPassword = MD5(?), userFullName = ?, userStatus = ? WHERE userName = ?";

                ps = koneksi.prepareStatement(query);
                ps.setString(1, this.userEmail);
                ps.setString(2, this.userPassword);
                ps.setString(3, this.userFullName);
                ps.setInt(4, this.userStatus);
                ps.setString(5, this.userName);
                ps.executeUpdate();
                ps.close();

                JOptionPane.showMessageDialog(null, "Data berhasil diubah");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "gagal : " + e.getMessage());
            }
        }
    }
    
    public void hapusUser() {
         try {
            query = "DELETE FROM user WHERE userName = ?";

            ps = koneksi.prepareStatement(query);
            ps.setString(1, this.userName);
            ps.executeUpdate();
            ps.close();

            JOptionPane.showMessageDialog(null, "Data berhasil dihapus");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "gagal : " + e.getMessage());
        }
    }  
    
    public ResultSet TampilUser() {
        query ="SELECT * FROM user";
        
        try {
            st = koneksi.createStatement();
            rs = st.executeQuery(query);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data gagal di tampilkan" + e.getMessage());
        }
        
        return rs;
    }
    
    public void login() {        
        try {
            query ="SELECT * FROM user WHERE userName = ? AND userPassword = MD5 (?)";
            ps = koneksi.prepareStatement(query);
            ps.setString(1, this.userName);
            ps.setString(2, this.userPassword);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                sesion.setUsername(rs.getString("userName"));
                sesion.setEmail(rs.getString("userEmail"));
                sesion.setFullname(rs.getString("userFullName"));
                sesion.setStatus("Aktif");
            } else {
                sesion.setStatus("Non Aktif");
                JOptionPane.showMessageDialog(null, "Username atau Password salah, silahkan coba lagi ");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Login gagal, silakan coba lagi " + e.getMessage());
        }
    }
    
    public void logOut() {
        sesion.setUsername("");
        sesion.setEmail("");
        sesion.setFullname("");
        sesion.setStatus("");
    } 
}