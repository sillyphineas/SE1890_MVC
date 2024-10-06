/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Shipper;
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
public class DAOShipper extends DBConnection{
    public int addShipper(Shipper s) {
        int n = 0;
        String sql = """
                     INSERT INTO [dbo].[Shippers]
                                           ([CompanyName]
                                           ,[Phone],[ShipperStatus])
                                     VALUES
                                           (?
                                           ,?,?)""";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            
            pre.setString(1, s.getCompanyName());
            pre.setString(2, s.getPhone());
            pre.setInt(3, s.getShipperStatus());
            
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return n;
    }
    
    public int updateShipper(Shipper s) {
        int n = 0;
        String sql = """
                     UPDATE [dbo].[Shippers]
                        SET [CompanyName] = ?
                           ,[Phone] = ?
                      WHERE [ShipperID] = ?""";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            
            pre.setString(1, s.getCompanyName());
            pre.setString(2, s.getPhone());
            pre.setInt(3, 1);
            
            
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return n;
    }

    
    public void changeStatus(int shipperID, int newValue ){
        
        String sql= "update [dbo].[Shippers] set ShipperStatus = "+ newValue +" Where ShipperID = "+shipperID;
        
        try {
            Statement state = conn.createStatement();
            state.execute(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOShipper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public int deleteShippers(int shipperID) {
        int n = 0;
        String sqlCheck ="Select * FROM [Orders] Where ShipVia = "+shipperID;
        ResultSet rs = getData(sqlCheck);
        
        try {
            if (rs.next()) {
                changeStatus(shipperID, 0);
                return 0;
                
            }
            String sql = "Delete from Shippers where ShipperID = " + shipperID;
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOShipper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    
    public Vector<Shipper> getShipper(String sql) {
        Vector<Shipper> vector = new Vector<Shipper>();
        try {
            //note: in case login must be used PreparedStatement để tránh tấn công SQL Injection
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            //TYPE_SCROLL_SENSITIVE (Có thread-safe): Con trỏ chuột chạy theo 2 hướng được(Scroll up và scroll down)
            //TYPE_SCROLL_INSENSITIVE (Không có thread-safe): Con trỏ chuột chạy theo 2 hướng được(Scroll up và scroll down)
            ResultSet rs = state.executeQuery(sql);
            //rs.previous()
            while(rs.next()) {
                
                int ShipperID = rs.getInt("ShipperID");
                String CompanyName = rs.getString("CompanyName");
                String Phone = rs.getString("Phone");
                int ShipperStatus = rs.getInt("ShipperStatus");
                Shipper s = new Shipper(ShipperID, CompanyName, Phone, ShipperStatus);
                vector.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }
    
    public static void main(String[] args) {
        DAOShipper sh = new DAOShipper();
//        int n = p.insertProduct(new Product("PName",1,1,"QuantityPerUnit",100.1,10,11,1,true));
//        if (n>0) {
//            System.out.println("Inserted");
//        }
        
//        int n = p.updateProduct(new Product("PName",1,1,"QuantityPerUnit",100.1,10,11,1,true));
//        if (n>0) {
//            System.out.println("Updated");
//        }
        
        Vector<Shipper> vector = sh.getShipper("SELECT * FROM Shipper");
        for (Shipper s : vector) {
            System.out.println(s);
        }
    }
}
