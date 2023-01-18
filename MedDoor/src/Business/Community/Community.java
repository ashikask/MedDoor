/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business.Community;

import Business.Network.Network;
import Business.Pharmacy.PharmacyDirectory;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author ashikakalmady
 */
public class Community {
    private String communityId;
    private String communityName; 
    private Integer zipCode; 
    private Network city;
    public HouseList houselist;
    public PharmacyDirectory pd;

    public HouseList getHouselist() {
        return houselist;
    }

    public void setHouselist(HouseList houselist) {
        this.houselist = houselist;
    }

    public PharmacyDirectory getPd() {
        return pd;
    }

    public void setPd(PharmacyDirectory pd) {
        this.pd = pd;
    }

    public HouseList getHouse() {
        return houselist;
    }

    public void setHouse(HouseList house) {
        this.houselist = house;
    }
    
   
    public Community() {
        this.houselist= new HouseList();
    }
    
    public Community(String communityName, Integer zipCode, Network city) {
        String uniqueID = UUID.randomUUID().toString();
        this.communityId = uniqueID;
        this.communityName = communityName;
        this.zipCode = zipCode;
        this.city = city;
        this.houselist= new HouseList();
        this.pd=new PharmacyDirectory();
         System.out.println("houselist 50"+ this.houselist);
    }

    public Integer getZipCode() {
        return zipCode;
    }

    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }

    public Network getCity() {
        return city;
    }

    public void setCity(Network city) {
        this.city = city;
    }
    
    public String getCommunityId() {
        return communityId;
    }

    public void setCommunityId(String communityId) {
        this.communityId = communityId;
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String name) {
        this.communityName = name;
    }
    
    @Override
    public String toString() {
        return communityName;
    }
}

