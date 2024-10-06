/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author HP
 */
public class CustomerCustomerDemo {
    private String CustomerID;
    private String CustomerTypeID;

    public CustomerCustomerDemo() {
    }

    public CustomerCustomerDemo(String CustomerID, String CustomerTypeID) {
        this.CustomerID = CustomerID;
        this.CustomerTypeID = CustomerTypeID;
    }

    public String getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(String CustomerID) {
        this.CustomerID = CustomerID;
    }

    public String getCustomerTypeID() {
        return CustomerTypeID;
    }

    public void setCustomerTypeID(String CustomerTypeID) {
        this.CustomerTypeID = CustomerTypeID;
    }
    
    
}
