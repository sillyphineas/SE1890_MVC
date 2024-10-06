/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.EmployeeTerritories;
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
public class DAOEmployeeTerritories extends DBConnection{
    public int addEmployeeTerritories(EmployeeTerritories et) {
        int n = 0;
        String sql = """
                     INSERT INTO [dbo].[EmployeeTerritories]
                                ([EmployeeID]
                                ,[TerritoryID])
                          VALUES
                                ?
                                ,?)""";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            
            pre.setInt(1, et.getEmployeeID());
            pre.setString(2, et.getTerritoryID());
            
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return n;
    }
    
    public int updateEmployeeTerritories(EmployeeTerritories et) {
        int n = 0;
        String sql = """
                     UPDATE [dbo].[EmployeeTerritories]
                        SET [EmployeeID] = ?
                           ,[TerritoryID] = ?
                      WHERE [EmployeeID] = ?""";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            
            pre.setInt(1, et.getEmployeeID());
            pre.setString(2, et.getTerritoryID());
            pre.setInt(3, 10);
            
            
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return n;
    }

    
    public int deleteEmployeeTerritories(int EmployeeID) {
        int n = 0;
        //case 1: delete from foreign key --> primary key
        //case 2: select foreign key --> is exist --> disable primary key
        String sql = "DELETE FROM [dbo].[EmployeeTerritories]\n" +
"      WHERE [EmployeeID]=" + EmployeeID;
        try {
            Statement state = conn.createStatement();           
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return n;
    }
    
    public Vector<EmployeeTerritories> getEmployeeTerritories(String sql) {
        Vector<EmployeeTerritories> vector = new Vector<EmployeeTerritories>();
        try {
            //note: in case login must be used PreparedStatement để tránh tấn công SQL Injection
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            //TYPE_SCROLL_SENSITIVE (Có thread-safe): Con trỏ chuột chạy theo 2 hướng được(Scroll up và scroll down)
            //TYPE_SCROLL_INSENSITIVE (Không có thread-safe): Con trỏ chuột chạy theo 2 hướng được(Scroll up và scroll down)
            ResultSet rs = state.executeQuery(sql);
            //rs.previous()
            while(rs.next()) {
                int EmployeeID = rs.getInt("EmployeeID");
                String TerritoryID = rs.getString("TerritoryID");
                
                
                
                EmployeeTerritories et = new EmployeeTerritories(EmployeeID, TerritoryID);
                vector.add(et);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }
    
    public static void main(String[] args) {
        DAOEmployeeTerritories et = new DAOEmployeeTerritories();
//        int n = p.insertProduct(new Product("PName",1,1,"QuantityPerUnit",100.1,10,11,1,true));
//        if (n>0) {
//            System.out.println("Inserted");
//        }
        
//        int n = p.updateProduct(new Product("PName",1,1,"QuantityPerUnit",100.1,10,11,1,true));
//        if (n>0) {
//            System.out.println("Updated");
//        }
        
        Vector<EmployeeTerritories> vector = et.getEmployeeTerritories("SELECT * FROM EmployeeTerritories");
        for (EmployeeTerritories e : vector) {
            System.out.println(e);
        }
    }
}
