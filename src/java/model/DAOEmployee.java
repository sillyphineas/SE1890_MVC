/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Employee;
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
public class DAOEmployee extends DBConnection{
    public int addEmployee(Employee other) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Employees]\n"
                + "           ([LastName]\n"
                + "           ,[FirstName]\n"
                + "           ,[Title]\n"
                + "           ,[TitleOfCourtesy]\n"
                + "           ,[BirthDate]\n"
                + "           ,[HireDate]\n"
                + "           ,[Address]\n"
                + "           ,[City]\n"
                + "           ,[Region]\n"
                + "           ,[PostalCode]\n"
                + "           ,[Country]\n"
                + "           ,[HomePhone]\n"
                + "           ,[Extension]\n"
                + "           ,[Notes]\n"
                + "           ,[ReportsTo]\n"
                +"            ,[Photo]\n"
                + "           ,[PhotoPath])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement prestate = conn.prepareStatement(sql);

            prestate.setString(1, other.getLastName());
            prestate.setString(2, other.getFirstName());
            prestate.setString(3, other.getTitle());
            prestate.setString(4, other.getTitleOfCourtesy());
            prestate.setString(5, other.getBirthDate());
            prestate.setString(6, other.getHireDate());
            prestate.setString(7, other.getAddress());
            prestate.setString(8, other.getCity());
            prestate.setString(9, other.getRegion());
            prestate.setString(10, other.getPostalCode());
            prestate.setString(11, other.getCountry());
            prestate.setString(12, other.getHomePhone());
            prestate.setString(13, other.getExtension());

            prestate.setString(14, other.getNotes());
            prestate.setInt(15, other.getReportsTo());
            prestate.setString(16, other.getPhoto());
            prestate.setString(17, other.getPhotoPath());
            n = prestate.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOEmployee.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    
    public int insertEmployee(Employee other) {
    int n = 0;
    String sql = "INSERT INTO [dbo].[Employees]\n"
            + "           ([LastName]\n"
            + "           ,[FirstName]\n"
            + "           ,[Title]\n"
            + "           ,[TitleOfCourtesy]\n"
            + "           ,[BirthDate]\n"
            + "           ,[HireDate]\n"
            + "           ,[Address]\n"
            + "           ,[City]\n"
            + "           ,[Region]\n"
            + "           ,[PostalCode]\n"
            + "           ,[Country]\n"
            + "           ,[HomePhone]\n"
            + "           ,[Extension]\n"
            + "           ,[Photo]\n"
            + "           ,[Notes]\n"
            + "           ,[ReportsTo]\n"
            + "           ,[PhotoPath]\n"
            + "           ,[EmployStatus])\n"
            + "     VALUES\n"
            + "           ('" + other.getLastName() + "' ,'" + other.getFirstName() + "' ,'" + other.getTitle() + "','" + other.getTitleOfCourtesy() + "' ,'" + other.getBirthDate() + "' ,"
            + "'" + other.getHireDate() + "' ,'" + other.getAddress() + "' ,'" + other.getCity() + "' ,'" + other.getRegion() + "' ,'" + other.getPostalCode() + "' ,"
            + "'" + other.getCountry() + "','" + other.getHomePhone() + "' ,'" + other.getExtension() + "' ,'" + other.getPhoto() + "' ,"
            + "'" + other.getNotes() + "' ," + other.getReportsTo() + " ,'" + other.getPhotoPath() + "','" + other.getEmployStatus() + "')";

    try {
        Statement state = conn.createStatement();
        n = state.executeUpdate(sql);
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return n;
}

    
    public int updateEmployee(Employee e) {
        int n = 0;
        String sql = """
                     UPDATE [dbo].[Employees]
                          SET [LastName] = ?
                             ,[FirstName] = ?
                             ,[Title] = ?
                             ,[TitleOfCourtesy] = ?
                             ,[BirthDate] = ?
                             ,[HireDate] = ?
                             ,[Address] = ?
                             ,[City] = ?
                             ,[Region] = ?
                             ,[PostalCode] = ?
                             ,[Country] = ?
                             ,[HomePhone] = ?
                             ,[Extension] = ?
                             ,[Photo] = ?
                             ,[Notes] = ?
                             ,[ReportsTo] = ?
                             ,[PhotoPath] = ?
                        WHERE [EmployeeID] = ?""";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            
            pre.setString(1, e.getLastName());
            pre.setString(2, e.getFirstName());
            pre.setString(3, e.getTitle());
            pre.setString(4, e.getTitleOfCourtesy());
            pre.setString(5, e.getBirthDate());
            pre.setString(6, e.getHireDate());
            pre.setString(7, e.getAddress());
            pre.setString(8, e.getCity());
            pre.setString(9, e.getRegion());
            pre.setString(10, e.getPostalCode());
            pre.setString(11, e.getCountry());
            pre.setString(12, e.getHomePhone());
            pre.setString(13, e.getExtension());
            pre.setString(14, e.getPhoto());
            pre.setString(15, e.getNotes());
            pre.setInt(16, e.getReportsTo());
            pre.setString(17, e.getPhotoPath());
            pre.setInt(18, e.getEmployeeID());
            
            
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return n;
    }

    public void changeEmployStatus(int pid, int newValue) {
        String sql = "update Employees set EmployStatus=" + newValue + "where EmployeeID =" + pid;
        try {
            Statement state = conn.createStatement();
            state.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public int deleteEmployee(int EmployeeId) {
        int n = 0;
        //case 1: delete from foreign key --> primary key
        //case 2: select foreign key --> is exist --> disable primary key
        String sqlCheck = "select * from [dbo].[EmployeeTerritories] where EmployeeID=" + EmployeeId;
        ResultSet rs = getData(sqlCheck);
        try {
            if (rs.next()) {
                //Có khoá ngoại --> Không xoá, đổi status (Discontinued = 1)
                changeEmployStatus(EmployeeId, 0);
                return 0;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sqlCheck2 = "select * from [dbo].[Orders] where EmployeeID=" + EmployeeId;
        ResultSet rs2 = getData(sqlCheck2);
        try {
            if (rs2.next()) {
                //Có khoá ngoại --> Không xoá, đổi status (Discontinued = 1)
                changeEmployStatus(EmployeeId, 0);
                return 0;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql = "DELETE FROM [dbo].[Employees]\n" +
"      WHERE [EmployeeID]=" + EmployeeId;
        try {
            Statement state = conn.createStatement();           
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return n;
    }
    
    public Vector<Employee> getEmployee(String sql) {
        Vector<Employee> vector = new Vector<Employee>();
        try {
            //note: in case login must be used PreparedStatement để tránh tấn công SQL Injection
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            //TYPE_SCROLL_SENSITIVE (Có thread-safe): Con trỏ chuột chạy theo 2 hướng được(Scroll up và scroll down)
            //TYPE_SCROLL_INSENSITIVE (Không có thread-safe): Con trỏ chuột chạy theo 2 hướng được(Scroll up và scroll down)
            ResultSet rs = state.executeQuery(sql);
            //rs.previous()
            while(rs.next()) {            
                int EmployeeID = rs.getInt("EmployeeID");
                String LastName = rs.getString("LastName");
                String FirstName = rs.getString("FirstName");
                String Title = rs.getString("Title");
                String TitleOfCourtesy = rs.getString("TitleOfCourtesy");
                String BirthDate = rs.getString("BirthDate");
                String HireDate = rs.getString("HireDate");
                String Address = rs.getString("Address");
                String City = rs.getString("City");
                String Region = rs.getString("Region");
                String PostalCode = rs.getString("PostalCode");
                String Country = rs.getString("Country");
                String HomePhone = rs.getString("HomePhone");
                String Extension = rs.getString("Extension");
                String Photo = rs.getString("Photo");
                String Notes = rs.getString("Notes");
                int ReportsTo = rs.getInt("ReportsTo");
                String PhotoPath = rs.getString("PhotoPath");
                int EmployStatus = rs.getInt("EmployStatus");
                //Employee e = new Employee(EmployeeID, ReportsTo, LastName, FirstName, Title, TitleOfCourtesy, BirthDate, HireDate, Address, City, Region, PostalCode, Country, HomePhone, Extension, Photo, Notes, PhotoPath, EmployStatus);
                Employee e = new Employee(EmployeeID ,ReportsTo, LastName, FirstName, Title, TitleOfCourtesy, BirthDate, HireDate, Address, City, Region, PostalCode, Country, HomePhone, Extension, Photo, Notes, PhotoPath, EmployStatus);
                vector.add(e);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }
    
    public static void main(String[] args) {
        DAOEmployee e = new DAOEmployee();
//        int n = p.insertProduct(new Product("PName",1,1,"QuantityPerUnit",100.1,10,11,1,true));
//        if (n>0) {
//            System.out.println("Inserted");
//        }
        
//        int n = p.updateProduct(new Product("PName",1,1,"QuantityPerUnit",100.1,10,11,1,true));
//        if (n>0) {
//            System.out.println("Updated");
//        }
        
        Vector<Employee> vector = e.getEmployee("SELECT * FROM Employees");
        for (Employee e1 : vector) {
            System.out.println(e1);
        }
    }
}
