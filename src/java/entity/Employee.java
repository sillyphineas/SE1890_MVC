/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author HP
 */
public class Employee {
    private int EmployeeID,ReportsTo,EmployStatus;
    private String LastName, FirstName, Title, TitleOfCourtesy, BirthDate, HireDate, Address,City,Region,PostalCode,Country,HomePhone,Extension,Photo,Notes,PhotoPath;

    public Employee() {
    }

    public Employee(int EmployeeID, int ReportsTo, String LastName, String FirstName, String Title, String TitleOfCourtesy, String BirthDate, String HireDate, String Address, String City, String Region, String PostalCode, String Country, String HomePhone, String Extension, String Photo, String Notes, String PhotoPath, int EmployStatus) {
        this.EmployeeID = EmployeeID;
        this.ReportsTo = ReportsTo;
        this.LastName = LastName;
        this.FirstName = FirstName;
        this.Title = Title;
        this.TitleOfCourtesy = TitleOfCourtesy;
        this.BirthDate = BirthDate;
        this.HireDate = HireDate;
        this.Address = Address;
        this.City = City;
        this.Region = Region;
        this.PostalCode = PostalCode;
        this.Country = Country;
        this.HomePhone = HomePhone;
        this.Extension = Extension;
        this.Photo = Photo;
        this.Notes = Notes;
        this.PhotoPath = PhotoPath;
        this.EmployStatus = EmployStatus;
    }
    
    
    public Employee(int ReportsTo, String LastName, String FirstName, String Title, String TitleOfCourtesy, String BirthDate, String HireDate, String Address, String City, String Region, String PostalCode, String Country, String HomePhone, String Extension, String Photo, String Notes, String PhotoPath, int EmployStatus) {
        this.ReportsTo = ReportsTo;
        this.LastName = LastName;
        this.FirstName = FirstName;
        this.Title = Title;
        this.TitleOfCourtesy = TitleOfCourtesy;
        this.BirthDate = BirthDate;
        this.HireDate = HireDate;
        this.Address = Address;
        this.City = City;
        this.Region = Region;
        this.PostalCode = PostalCode;
        this.Country = Country;
        this.HomePhone = HomePhone;
        this.Extension = Extension;
        this.Photo = Photo;
        this.Notes = Notes;
        this.PhotoPath = PhotoPath;
        this.EmployStatus = EmployStatus;
    }

    public int getEmployeeID() {
        return EmployeeID;
    }

    public void setEmployeeID(int EmployeeID) {
        this.EmployeeID = EmployeeID;
    }

    public int getReportsTo() {
        return ReportsTo;
    }

    public void setReportsTo(int ReportsTo) {
        this.ReportsTo = ReportsTo;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getTitleOfCourtesy() {
        return TitleOfCourtesy;
    }

    public void setTitleOfCourtesy(String TitleOfCourtesy) {
        this.TitleOfCourtesy = TitleOfCourtesy;
    }

    public String getBirthDate() {
        return BirthDate;
    }

    public void setBirthDate(String BirthDate) {
        this.BirthDate = BirthDate;
    }

    public String getHireDate() {
        return HireDate;
    }

    public void setHireDate(String HireDate) {
        this.HireDate = HireDate;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String City) {
        this.City = City;
    }

    public String getRegion() {
        return Region;
    }

    public void setRegion(String Region) {
        this.Region = Region;
    }

    public String getPostalCode() {
        return PostalCode;
    }

    public void setPostalCode(String PostalCode) {
        this.PostalCode = PostalCode;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String Country) {
        this.Country = Country;
    }

    public String getHomePhone() {
        return HomePhone;
    }

    public void setHomePhone(String HomePhone) {
        this.HomePhone = HomePhone;
    }

    public String getExtension() {
        return Extension;
    }

    public void setExtension(String Extension) {
        this.Extension = Extension;
    }

    public String getPhoto() {
        return Photo;
    }

    public void setPhoto(String Photo) {
        this.Photo = Photo;
    }

    public String getNotes() {
        return Notes;
    }

    public void setNotes(String Notes) {
        this.Notes = Notes;
    }

    public String getPhotoPath() {
        return PhotoPath;
    }

    public void setPhotoPath(String PhotoPath) {
        this.PhotoPath = PhotoPath;
    }

    public int getEmployStatus() {
        return EmployStatus;
    }

    public void setEmployStatus(int EmployStatus) {
        this.EmployStatus = EmployStatus;
    }
    
}
