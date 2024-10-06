/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Customer;
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
public class DAOCustomer extends DBConnection{
    public int addCCustomer(Customer cus) {
        int n = 0;
        String sql = """
                     INSERT INTO [dbo].[Customers]
                                ([CustomerID]
                                ,[CompanyName]
                                ,[ContactName]
                                ,[ContactTitle]
                                ,[Address]
                                ,[City]
                                ,[Region]
                                ,[PostalCode]
                                ,[Country]
                                ,[Phone]
                                ,[Fax])
                          VALUES
                                (?
                                ,?
                                ,?
                                ,?
                                ,?
                                ,?
                                ,?
                                ,?
                                ,?
                                ,?
                                ,?)""";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            
            pre.setString(1, cus.getCustomerID());
            pre.setString(2, cus.getCompanyName());
            pre.setString(3, cus.getContactName());
            pre.setString(4, cus.getContactTitle());
            pre.setString(5, cus.getAddress());
            pre.setString(6, cus.getCity());
            pre.setString(7, cus.getRegion());
            pre.setString(8, cus.getPostalCode());
            pre.setString(9, cus.getCountry());
            pre.setString(10, cus.getPhone());
            pre.setString(11, cus.getFax());
            
            
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return n;
    }
    
    public int updateCustomer(Customer cus) {
        int n = 0;
        String sql = """
                     UPDATE [dbo].[Customers]
                         SET [CustomerID] = ?
                            ,[CompanyName] = ?
                            ,[ContactName] = ?
                            ,[ContactTitle] = ?
                            ,[Address] = ?
                            ,[City] = ?
                            ,[Region] = ?
                            ,[PostalCode] = ?
                            ,[Country] = ?
                            ,[Phone] = ?
                            ,[Fax] = ?
                       WHERE [CustomerID] = ?""";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            
            pre.setString(1, cus.getCustomerID());
            pre.setString(2, cus.getCompanyName());
            pre.setString(3, cus.getContactName());
            pre.setString(4, cus.getContactTitle());
            pre.setString(5, cus.getAddress());
            pre.setString(6, cus.getCity());
            pre.setString(7, cus.getRegion());
            pre.setString(8, cus.getPostalCode());
            pre.setString(9, cus.getCountry());
            pre.setString(10, cus.getPhone());
            pre.setString(11, cus.getFax());
            
            pre.setString(12, "ANATR");
            
            
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return n;
    }

    
    public int deleteCustomer(int cusId) {
        int n = 0;
        //case 1: delete from foreign key --> primary key
        //case 2: select foreign key --> is exist --> disable primary key
        String sql = "DELETE FROM [dbo].[Customers]\n" +
"      WHERE [CustomerID]=" + cusId;
        try {
            Statement state = conn.createStatement();           
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return n;
    }
    
    public Vector<Customer> getCustomers(String sql) {
        Vector<Customer> vector = new Vector<Customer>();
        try {
            //note: in case login must be used PreparedStatement để tránh tấn công SQL Injection
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            //TYPE_SCROLL_SENSITIVE (Có thread-safe): Con trỏ chuột chạy theo 2 hướng được(Scroll up và scroll down)
            //TYPE_SCROLL_INSENSITIVE (Không có thread-safe): Con trỏ chuột chạy theo 2 hướng được(Scroll up và scroll down)
            ResultSet rs = state.executeQuery(sql);
            //rs.previous()
            while(rs.next()) {
                String CustomerID = rs.getString("CustomerID");
                String CompanyName = rs.getString("CompanyName");
                String ContactName = rs.getString("ContactName");
                String ContactTitle = rs.getString("ContactTitle");
                String Address = rs.getString("Address");
                String City = rs.getString("City");
                String Region = rs.getString("Region");
                String PostalCode = rs.getString("PostalCode");
                String Country = rs.getString("Country");
                String Phone = rs.getString("Phone");
                String Fax = rs.getString("Fax");
                Customer cus = new Customer(CustomerID, CompanyName, ContactName, ContactTitle, Address, City, Region, PostalCode, Country, Phone, Fax);
                
                vector.add(cus);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }
    
    public static void main(String[] args) {
        DAOCustomer c = new DAOCustomer();
//        int n = p.insertProduct(new Product("PName",1,1,"QuantityPerUnit",100.1,10,11,1,true));
//        if (n>0) {
//            System.out.println("Inserted");
//        }
        
//        int n = p.updateProduct(new Product("PName",1,1,"QuantityPerUnit",100.1,10,11,1,true));
//        if (n>0) {
////            System.out.println("Updated");
//        }
        
        Vector<Customer> vector = c.getCustomers("SELECT * FROM Customers");
        for (Customer cus : vector) {
            System.out.println(cus);
        }
    }
}
