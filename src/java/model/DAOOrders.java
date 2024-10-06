/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Orders;
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
public class DAOOrders extends DBConnection{
    public int addOrders(Orders o) {
        int n = 0;
        String sql = """
                     INSERT INTO [dbo].[Orders]
                                ([CustomerID]
                                ,[EmployeeID]
                                ,[OrderDate]
                                ,[RequiredDate]
                                ,[ShippedDate]
                                ,[ShipVia]
                                ,[Freight]
                                ,[ShipName]
                                ,[ShipAddress]
                                ,[ShipCity]
                                ,[ShipRegion]
                                ,[ShipPostalCode]
                                ,[ShipCountry])
                          VALUES(
                                ?
                                ,?
                                ,?
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
            
            pre.setString(1, o.getCustomerID());
            pre.setInt(2, o.getEmployeeID());
            pre.setString(3, o.getOrderDate());
            pre.setString(4, o.getRequiredDate());
            pre.setString(5, o.getShippedDate());
            pre.setInt(6, o.getShipVia());
            pre.setDouble(7, o.getFreight());
            pre.setString(8, o.getShipName());
            pre.setString(9, o.getShipAddress());
            pre.setString(10, o.getShipCity());
            pre.setString(11, o.getShipRegion());
            pre.setString(12, o.getShipPostalCode());
            pre.setString(13, o.getShipCountry());
            
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return n;
    }
    
    public int insertOrders(Orders other) {
    int n = 0;
    String sql = "INSERT INTO [dbo].[Orders] "
               + "([CustomerID], [EmployeeID], [OrderDate], [RequiredDate], [ShippedDate], "
               + "[ShipVia], [Freight], [ShipName], [ShipAddress], [ShipCity], [ShipRegion], [ShipPostalCode], [ShipCountry]) "
               + "VALUES ('" 
               + other.getCustomerID() + "', " 
               + other.getEmployeeID() + ", '" 
               + other.getOrderDate() + "', '" 
               + other.getRequiredDate() + "', '" 
               + other.getShippedDate() + "', "
               + other.getShipVia() + ", "
               + other.getFreight() + ", '" 
               + other.getShipName() + "', '" 
               + other.getShipAddress() + "', '" 
               + other.getShipCity() + "', '" 
               + other.getShipRegion() + "', '" 
               + other.getShipPostalCode() + "', '" 
               + other.getShipCountry() + "')";

    try {
        // In câu lệnh SQL ra để kiểm tra cú pháp
        System.out.println(sql);

        Statement state = conn.createStatement();
        n = state.executeUpdate(sql);
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return n;
}

    
    public static void main(String[] args) {
        DAOOrders d = new DAOOrders();
        int n = d.insertOrders(new Orders("1",3,"2000-01-01","2000-01-01","2000-01-01",2,2.0,"1","1","1","1","1","1"));
        System.out.println(n);
    }
    
    public int updateOrders(Orders o) {
        int n = 0;
        String sql = """
                     UPDATE [dbo].[Orders]
                        SET [CustomerID] = ?
                           ,[EmployeeID] = ?
                           ,[OrderDate] = ?
                           ,[RequiredDate] = ?
                           ,[ShippedDate] = ?
                           ,[ShipVia] = ?
                           ,[Freight] = ?
                           ,[ShipName] = ?
                           ,[ShipAddress] = ?
                           ,[ShipCity] = ?
                           ,[ShipRegion] = ?
                           ,[ShipPostalCode] = ?
                           ,[ShipCountry] = ?
                      WHERE [OrderID] = ?""";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            
            pre.setString(1, o.getCustomerID());
            pre.setInt(2, o.getEmployeeID());
            pre.setString(3, o.getOrderDate());
            pre.setString(4, o.getRequiredDate());
            pre.setString(5, o.getShippedDate());
            pre.setInt(6, o.getShipVia());
            pre.setDouble(7, o.getFreight());
            pre.setString(8, o.getShipName());
            pre.setString(9, o.getShipAddress());
            pre.setString(10, o.getShipCity());
            pre.setString(11, o.getShipRegion());
            pre.setString(12, o.getShipPostalCode());
            pre.setString(13, o.getShipCountry());
            pre.setInt(14, o.getOrderID());
            
            
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return n;
    }

    
    public int deleteOrders(int OrderId) {
        int n = 0;
        //case 1: delete from foreign key --> primary key
        //case 2: select foreign key --> is exist --> disable primary key
        String sql = "DELETE FROM [dbo].[Orders]\n" +
"      WHERE [OrderID]=" + OrderId;
        try {
            Statement state = conn.createStatement();           
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return n;
    }
    
    public Vector<Orders> getOrders(String sql) {
        Vector<Orders> vector = new Vector<Orders>();
        try {
            //note: in case login must be used PreparedStatement để tránh tấn công SQL Injection
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            //TYPE_SCROLL_SENSITIVE (Có thread-safe): Con trỏ chuột chạy theo 2 hướng được(Scroll up và scroll down)
            //TYPE_SCROLL_INSENSITIVE (Không có thread-safe): Con trỏ chuột chạy theo 2 hướng được(Scroll up và scroll down)
            ResultSet rs = state.executeQuery(sql);
            //rs.previous()
            while(rs.next()) {
                int OrderID = rs.getInt("OrderID");
                String CustomerID = rs.getString("CustomerID");
                int EmployeeID = rs.getInt("EmployeeID");
                String OrderDate = rs.getString("OrderDate");
                String RequiredDate = rs.getString("RequiredDate");
                String ShippedDate = rs.getString("ShippedDate");
                int ShipVia = rs.getInt("ShipVia");
                double Freight = rs.getDouble("Freight");
                String ShipName = rs.getString("ShipName");
                String ShipAddress = rs.getString("ShipAddress");
                String ShipCity = rs.getString("ShipCity");
                String ShipRegion = rs.getString("ShipRegion");
                String ShipPostalCode = rs.getString("ShipPostalCode");
                String ShipCountry = rs.getString("ShipCountry");

                Orders o = new Orders(OrderID, CustomerID, EmployeeID, OrderDate, RequiredDate, ShippedDate, ShipVia, Freight, ShipName, ShipAddress, ShipCity, ShipRegion, ShipPostalCode, ShipCountry);
                vector.add(o);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }
    
//    public static void main(String[] args) {
//        DAOOrders o = new DAOOrders();
////        int n = p.insertProduct(new Product("PName",1,1,"QuantityPerUnit",100.1,10,11,1,true));
////        if (n>0) {
////            System.out.println("Inserted");
////        }
//        
////        int n = p.updateProduct(new Product("PName",1,1,"QuantityPerUnit",100.1,10,11,1,true));
////        if (n>0) {
////            System.out.println("Updated");
////        }
//        
//        Vector<Orders> vector = o.getOrders("SELECT * FROM Orders");
//        for (Orders od : vector) {
//            System.out.println(od);
//        }
//    }
}
