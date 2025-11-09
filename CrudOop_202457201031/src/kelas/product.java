package kelas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class product extends Koneksi {
    private String productName, productDescription;
    private int productId, productCategory, productPrice; 
    
    private final Connection Koneksi;
    private  PreparedStatement ps;
    private Statement st;
    private ResultSet rs;
    private String query;
    
    public product() {
        Koneksi = super.configDB();
    }
    
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(int productCategory) {
        this.productCategory = productCategory;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }
    
    public ResultSet TampilProduk() {
        try {
            query = "SELECT product.productId, product.productName, category.categoryName, product.productDescription, product.productPrice FROM product JOIN category ON product.productCategory = category.categoryId";
            st = Koneksi.createStatement();
            rs = st.executeQuery(query);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "gagal : " + e.getMessage());
        }
        return rs;
    }
    
    public void TambahData() {
        try {
            query = "INSERT INTO product (productId, productName, productCategory, productDescription, productPrice) VALUES (?, ?, ?, ?, ?)";


            ps = Koneksi.prepareStatement(query);
            ps.setInt(1, this.productId);
            ps.setString(2, this.productName);
            ps.setInt(3, this.productCategory);
            ps.setString(4, this.productDescription);
            ps.setInt(5, this.productPrice);
            ps.executeUpdate();
            ps.close();

            JOptionPane.showMessageDialog(null, "Data berhasil disimpan");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "gagal : " + e.getMessage());
        }
    }
    
    public void UbahData() {
        try {
            query = "UPDATE product SET productName = ?, productCategory = ?, productDescription = ?, productPrice = ? WHERE productId = ?";

            ps = Koneksi.prepareStatement(query);
            ps.setString(1, this.productName);
            ps.setInt(2, this.productCategory);
            ps.setString(3, this.productDescription);
            ps.setInt(4, this.productPrice);
            ps.setInt(5, this.productId);
            ps.executeUpdate();
            ps.close();

            JOptionPane.showMessageDialog(null, "Data berhasil diubah");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "gagal : " + e.getMessage());
        }
    } 
    
    public void HapusData() {
        try {
            query = "DELETE FROM product WHERE productId = ?";

            ps = Koneksi.prepareStatement(query);
            ps.setInt(1, this.productId);
            ps.executeUpdate();
            ps.close();

            JOptionPane.showMessageDialog(null, "Data berhasil dihapus");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "gagal : " + e.getMessage());
        }
    }
    
    public ResultSet autoId() {
        try {
            query = "SELECT productId AS ID FROM product ORDER BY productId DESC LIMIT 1";

            st = Koneksi.createStatement();
            rs = st.executeQuery(query);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "gagal : " + e.getMessage());
        }
        return rs;
    }
    
    public ResultSet TampilProduct(){
        try {
            query = "SELECT product.productId, product.productName, category.categoryName, "
                    + "product.productDescription, product.productPrice "
                    + "FROM product JOIN category ON product.productCategory = category.categoryId";

            st = Koneksi.createStatement();
            rs = st.executeQuery(query);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "gagal : " + e.getMessage());
        }
        return rs;
    }
    
}