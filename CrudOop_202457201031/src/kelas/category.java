package kelas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class category extends Koneksi {
    private int categoryId;
    private String categoryName;
    
    private final Connection Koneksi;
    private PreparedStatement ps;
    private Statement st;
    private ResultSet rs;
    private String query;
    
    public category() {
        Koneksi = super.configDB();
    }

    public String getCategroyName() {
        return categoryName;
    }

    public void setCategroyName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
    
    public ResultSet TampilCategory() {
        try {
            query = "SELECT * FROM category";
            
            st = Koneksi.createStatement();
            rs = st.executeQuery(query);
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error : " + e.getMessage());
        }
        return rs;
    }
    
    public void TambahData() {
        try {
            query = "INSERT INTO category VALUES (?, ?)";
            
            ps = Koneksi.prepareStatement(query);
            ps.setInt(1, categoryId);
            ps.setString(2, categoryName);
            ps.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Data berhasil ditambahkan");
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error : " + e.getMessage());
        }
    }
    
    public void UbahData() {
        try {
            query = "UPDATE category SET categoryName = ? WHERE categoryId = ?";
            
            ps = Koneksi.prepareStatement(query);
            ps.setString(1, categoryName);
            ps.setInt(2, categoryId);
            ps.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Data berhasil diubah");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error : " + e.getMessage());
        }
    }
    
    public void HapusData() {
        try {
            query = "DELETE FROM category WHERE categoryId = ?";
            
            ps = Koneksi.prepareStatement(query);
            ps.setInt(1, categoryId);
            ps.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Data berhasil dihapus"); 
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error : " + e.getMessage());
        }
    }
    
    public ResultSet konversi(){
        try {
            query = "SELECT categoryId FROM category WHERE categoryName = ?";
            
            ps = Koneksi.prepareStatement(query);
            ps.setString(1, categoryName);
            
            rs = ps.executeQuery();
        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, "Error : " + sQLException.getMessage());
        }
        return rs;
    }
    
}