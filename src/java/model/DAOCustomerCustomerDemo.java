/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.CustomerCustomerDemo;
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
public class DAOCustomerCustomerDemo extends DBConnection{
    public int addCustomerCustomerDemo(CustomerCustomerDemo cd) {
        int n = 0;
        String sql = """
                     INSERT INTO [dbo].[CustomerCustomerDemo]
                                           ([CustomerID]
                                           ,[CustomerTypeID])
                                     VALUES
                                           (?
                                           ,?)""";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            
            pre.setString(1, cd.getCustomerID());
            pre.setString(2, cd.getCustomerTypeID());
            
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return n;
    }
    
    public int updateCustomerCustomerDemo(CustomerCustomerDemo cd) {
        int n = 0;
        String sql = """
                     UPDATE [dbo].[CustomerCustomerDemo]
                        SET [CustomerID] = ?
                           ,[CustomerTypeID] = ?
                      WHERE [CustomerID] = ?""";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            
            pre.setString(1, cd.getCustomerID());
            pre.setString(2, cd.getCustomerTypeID());
            pre.setString(3, "");
            
            
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return n;
    }

    
    public int deleteCustomerCustomerDemo(String cusId, String cusTypeId) {
        int n = 0;
        //case 1: delete from foreign key --> primary key
        //case 2: select foreign key --> is exist --> disable primary key
        String sql = "DELETE FROM [dbo].[CustomerCustomerDemo]\n" +
"      WHERE [CategoryID]=" + cusId + "AND [CustomerTypeID]=" + cusTypeId;
        try {
            Statement state = conn.createStatement();           
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return n;
    }
    
    public Vector<CustomerCustomerDemo> getCategories(String sql) {
        Vector<CustomerCustomerDemo> vector = new Vector<CustomerCustomerDemo>();
        try {
            //note: in case login must be used PreparedStatement để tránh tấn công SQL Injection
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            //TYPE_SCROLL_SENSITIVE (Có thread-safe): Con trỏ chuột chạy theo 2 hướng được(Scroll up và scroll down)
            //TYPE_SCROLL_INSENSITIVE (Không có thread-safe): Con trỏ chuột chạy theo 2 hướng được(Scroll up và scroll down)
            ResultSet rs = state.executeQuery(sql);
            //rs.previous()
            while(rs.next()) {
                String CustomerID = rs.getString("CustomerID");
                String CustomerTypeID = rs.getString("CustomerTypeID"); 
                CustomerCustomerDemo cd = new CustomerCustomerDemo(CustomerID, CustomerTypeID);
                vector.add(cd);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }
    
    public static void main(String[] args) {
        DAOCustomerCustomerDemo c = new DAOCustomerCustomerDemo();
//        int n = p.insertProduct(new Product("PName",1,1,"QuantityPerUnit",100.1,10,11,1,true));
//        if (n>0) {
//            System.out.println("Inserted");
//        }
        
//        int n = p.updateProduct(new Product("PName",1,1,"QuantityPerUnit",100.1,10,11,1,true));
//        if (n>0) {
//            System.out.println("Updated");
//        }
        
        Vector<CustomerCustomerDemo> vector = c.getCategories("SELECT * FROM Categories");
        for (CustomerCustomerDemo cd : vector) {
            System.out.println(cd);
        }
    }
}
