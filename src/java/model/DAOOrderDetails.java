/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.OrderDetails;
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
public class DAOOrderDetails extends DBConnection{
    public int addOrderDetails(OrderDetails od) {
        int n = 0;
        String sql = """
                     INSERT INTO [dbo].[Order Details]
                                ([OrderID]
                                ,[ProductID]
                                ,[UnitPrice]
                                ,[Quantity]
                                ,[Discount]
                                ,[OrderDetailStatus])
                          VALUES
                                (?
                                ,?
                                ,?
                                ,?
                                ,?
                                ,?)""";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            
            pre.setInt(1, od.getOrderID());
            pre.setInt(2, od.getProductID());
            pre.setDouble(3, od.getUnitPrice());
            pre.setInt(4, od.getQuantity());
            pre.setDouble(5, od.getDiscount());
            pre.setInt(6, od.getOrderDetailStatus());
            
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return n;
    }
    
    public int updateOrderDetails(OrderDetails od) {
        int n = 0;
        String sql = """
                     UPDATE [dbo].[Order Details]
                        SET [OrderID] = ?
                           ,[ProductID] = ?
                           ,[UnitPrice] = ?
                           ,[Quantity] = ?
                           ,[Discount] = ?
                           ,[OrderDetailStatus] = ?
                      WHERE [OrderID] = ?""";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            
            pre.setInt(1, od.getOrderID());
            pre.setInt(2, od.getProductID());
            pre.setDouble(3, od.getUnitPrice());
            pre.setInt(4, od.getQuantity());
            pre.setDouble(5, od.getDiscount());
            pre.setInt(6, 10261);
            
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return n;
    }

    public void changeOrderDetailStatus(int oid, int pid, int newValue) {
        String sql = "update [Order Details] set OrderDetailStatus=" + newValue + "where OrderID =" + oid + "AND ProductID = " + pid;
        try {
            Statement state = conn.createStatement();
            state.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public int deleteOrderDetails(int OrderID, int ProductID) {
        int n = 0;
        //case 1: delete from foreign key --> primary key
        //case 2: select foreign key --> is exist --> disable primary key
        String sql = "DELETE FROM [dbo].[Order Details]\n" +
"      WHERE [OrderID] = " + OrderID + "AND ProductID = " + ProductID;
        try {
            Statement state = conn.createStatement();           
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return n;
    }
    
    public Vector<OrderDetails> getOrderDetails(String sql) {
        Vector<OrderDetails> vector = new Vector<>();
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int OrderID = rs.getInt("OrderID");
                int ProductID = rs.getInt("ProductID");
                double UnitPrice = rs.getDouble("UnitPrice");
                int Quantity = rs.getInt("Quantity");
                double Discount = rs.getDouble("Discount");
                int OrderDetailStatus = rs.getInt("OrderDetailStatus");
                OrderDetails newOrderDetail = new OrderDetails(OrderID, ProductID, UnitPrice, Quantity, Discount, OrderDetailStatus);
                vector.add(newOrderDetail);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrderDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    
    public static void main(String[] args) {
        DAOOrderDetails od = new DAOOrderDetails();
//        int n = p.insertProduct(new Product("PName",1,1,"QuantityPerUnit",100.1,10,11,1,true));
//        if (n>0) {
//            System.out.println("Inserted");
//        }
        
//        int n = p.updateProduct(new Product("PName",1,1,"QuantityPerUnit",100.1,10,11,1,true));
//        if (n>0) {
//            System.out.println("Updated");
//        }
        
        Vector<OrderDetails> vector = od.getOrderDetails("SELECT * FROM [Order Details]");
        for (OrderDetails o : vector) {
            System.out.println(o);
        }
    }
}
