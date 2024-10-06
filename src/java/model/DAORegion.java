/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Region;
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
public class DAORegion extends DBConnection {
    public int addRegion(Region r) {
        int n = 0;
        String sql = """
                     INSERT INTO [dbo].[Region]
                                ([RegionID]
                                ,[RegionDescription])
                          VALUES
                                (?
                                ,?)""";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            
            pre.setInt(1, r.getRegionID());
            pre.setString(2, r.getRegionDescription());
            
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return n;
    }
    
    public int updateRegion(Region r) {
        int n = 0;
        String sql = """
                     UPDATE [dbo].[Region]
                        SET [RegionID] = ?
                           ,[RegionDescription] = ?
                      WHERE [RegionID] = ?""";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            
            pre.setInt(1, r.getRegionID());
            pre.setString(2, r.getRegionDescription());
            pre.setInt(3, 1);
            
            
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return n;
    }

    
    public void changeRegionStatus(int regionID , int newValue){
        String sql = "UPDATE Region SET RegionStatus = " + newValue + " WHERE RegionID = " + regionID;
        try {
            Statement state = conn.createStatement();
            state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAORegion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int deleteRegion(int regionID) {
        int n = 1;
        String sqlCheck = "SELECT * FROM Territories WHERE RegionID = "+regionID;
        ResultSet rs = getData(sqlCheck);
        try {
            if(rs.next()){
                changeRegionStatus(regionID, 0);
                return 0;
            }
            
            String sql = "Delete from Region where RegionID = " + regionID;
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAORegion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    
    public Vector<Region> getRegion(String sql) {
        Vector<Region> vector = new Vector<Region>();
        try {
            //note: in case login must be used PreparedStatement để tránh tấn công SQL Injection
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            //TYPE_SCROLL_SENSITIVE (Có thread-safe): Con trỏ chuột chạy theo 2 hướng được(Scroll up và scroll down)
            //TYPE_SCROLL_INSENSITIVE (Không có thread-safe): Con trỏ chuột chạy theo 2 hướng được(Scroll up và scroll down)
            ResultSet rs = state.executeQuery(sql);
            //rs.previous()
            while(rs.next()) {
                int RegionID = rs.getInt("RegionID");
                String RegionDescription = rs.getString("RegionDescription");
                int RegionStatus = rs.getInt("RegionStatus");
                
                Region r = new Region(RegionID, RegionDescription, RegionStatus);
                vector.add(r);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }
    
    public static void main(String[] args) {
        DAORegion r = new DAORegion();
//        int n = p.insertProduct(new Product("PName",1,1,"QuantityPerUnit",100.1,10,11,1,true));
//        if (n>0) {
//            System.out.println("Inserted");
//        }
        
//        int n = p.updateProduct(new Product("PName",1,1,"QuantityPerUnit",100.1,10,11,1,true));
//        if (n>0) {
//            System.out.println("Updated");
//        }
        
//        Vector<Region> vector = r.getRegion("SELECT * FROM Region");
//        for (Region re : vector) {
//            System.out.println(re);
//        }
          int n = r.deleteRegion(2);
          System.out.println(n);
    }
}
