/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Categories;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HP
 */
public class DAOCategories extends DBConnection{
    public int addCategories(Categories cat) {
        int n = 0;
        String sql = """
                     INSERT INTO [dbo].[Categories]
                                ([CategoryName]
                                ,[Description]
                                ,[Picture])
                          VALUES
                                (?,?,?)""";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            
            pre.setString(1, cat.getCategoryName());
            pre.setString(2, cat.getDescription());
            pre.setString(3, cat.getPicture());
            
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return n;
    }
    
    public int updateCategories(Categories cat) {
        int n = 0;
        String sql = """
                     UPDATE [dbo].[Categories]
                        SET [CategoryName] = ?
                           ,[Description] = ?
                           ,[Picture] = ?
                      WHERE [CategoryID] = ?""";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            
            pre.setString(1, cat.getCategoryName());
            pre.setString(2, cat.getDescription());
            pre.setString(3, cat.getPicture());
            pre.setInt(4, 1);
            
            
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return n;
    }

    
    public int deleteCategories(int catId) {
        int n = 0;
        //case 1: delete from foreign key --> primary key
        //case 2: select foreign key --> is exist --> disable primary key
        String sql = "DELETE FROM [dbo].[Categories]\n" +
"      WHERE [CategoryID]=" + catId;
        try {
            Statement state = conn.createStatement();           
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return n;
    }
    
    public Vector<Categories> getCategories(String sql) {
        Vector<Categories> vector = new Vector<Categories>();
        try {
            //note: in case login must be used PreparedStatement để tránh tấn công SQL Injection
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            //TYPE_SCROLL_SENSITIVE (Có thread-safe): Con trỏ chuột chạy theo 2 hướng được(Scroll up và scroll down)
            //TYPE_SCROLL_INSENSITIVE (Không có thread-safe): Con trỏ chuột chạy theo 2 hướng được(Scroll up và scroll down)
            ResultSet rs = state.executeQuery(sql);
            //rs.previous()
            while(rs.next()) {
                int CategoryID = rs.getInt("CategoryID");
                String CategoryName = rs.getString("CategoryName");
                String Description = rs.getString("Description");
                String Picture = rs.getString("Picture");
                Categories cat = new Categories(CategoryID,CategoryName, Description, Picture);
                vector.add(cat);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }
    
    public static void main(String[] args) {
        DAOCategories c = new DAOCategories();
//        int n = p.insertProduct(new Product("PName",1,1,"QuantityPerUnit",100.1,10,11,1,true));
//        if (n>0) {
//            System.out.println("Inserted");
//        }
        
//        int n = p.updateProduct(new Product("PName",1,1,"QuantityPerUnit",100.1,10,11,1,true));
//        if (n>0) {
//            System.out.println("Updated");
//        }
        
        Vector<Categories> vector = c.getCategories("SELECT * FROM Categories");
        for (Categories cat : vector) {
            System.out.println(cat);
        }
    }
}
