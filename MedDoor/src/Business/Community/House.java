/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business.Community;

import java.util.Date;
import java.util.UUID;

/**
 *
 * @author ashikakalmady
 */
public class House {
    private String houseId;
    private String houseNumer;
    private String streeAdredss;
    private Community community;
    public TenantDirectory tenats;

    public TenantDirectory getTenats() {
        return tenats;
    }

    public void setTenats(TenantDirectory tenats) {
        this.tenats = tenats;
    }
    
  
    
    public String getStreeAdredss() {
        return streeAdredss;
    }

    public void setStreeAdredss(String streeAdredss) {
        this.streeAdredss = streeAdredss;
    }
    
    public String getHouseNumer() {
        return houseNumer;
    }

    public void setHouseNumer(String houseNumer) {
        this.houseNumer = houseNumer;
    }

    
    public Community getCommunity() {
        return community;
    }

    public void setCommunity(Community community) {
        this.community = community;
    }
    
   
    public String getHouseId() {
        return houseId;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }

    public House(String houseNumer, String streetAdd, Community community) {
        String uniqueID = UUID.randomUUID().toString();
        this.houseId = uniqueID;
        this.houseNumer = houseNumer;
        this.streeAdredss = streetAdd;
        this.community = community;
        this.tenats = new TenantDirectory();
    }
    
    public House() {
        String uniqueID = UUID.randomUUID().toString();
        this.houseId = uniqueID;
        this.tenats = new TenantDirectory();
    }
    @Override
    public String toString() {
        return streeAdredss;
    }
}
