/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author HP
 */
public class Region {
    private int RegionID;
    private String RegionDescription;
    private int RegionStatus;

    public Region() {
    }

    public Region(int RegionID, String RegionDescription, int RegionStatus) {
        this.RegionID = RegionID;
        this.RegionDescription = RegionDescription;
        this.RegionStatus = RegionStatus;
    }

    public int getRegionID() {
        return RegionID;
    }

    public void setRegionID(int RegionID) {
        this.RegionID = RegionID;
    }

    public String getRegionDescription() {
        return RegionDescription;
    }

    public void setRegionDescription(String RegionDescription) {
        this.RegionDescription = RegionDescription;
    }

    public int getRegionStatus() {
        return RegionStatus;
    }

    public void setRegionStatus(int RegionStatus) {
        this.RegionStatus = RegionStatus;
    }
    
    
}
