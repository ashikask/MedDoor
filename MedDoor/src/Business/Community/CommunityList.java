/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business.Community;

import Business.Enterprise.Enterprise;
import Business.Enterprise.HospitalEnterprise;
import Business.Network.Network;
import Business.Organization.Organization;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author ashikakalmady
 */
public class CommunityList {
    private ArrayList<Community> communityList;

    public ArrayList<Community> getCommunityList() {
        ArrayList<String> names2 = new ArrayList<String>();
        System.out.println(names2);

        return communityList;
    }

    public void setCommunityList(ArrayList<Community> communityList) {
        this.communityList = communityList;
    }
    
    public CommunityList() {
        this.communityList = new ArrayList<Community>();
    }
    
    public void tempCommList(Network city) { 
        Community com = new Community("Allston-Suffolk", 20134, city);
//        House house = new House("123", "682 parker", com);
//        Tenant tenant = new Tenant("ash", "test", new Date(), "female", 21, "ashika@gmail.com", "478233838", house);
//        house.tenats.addNewTenant(tenant);
//        tenant = new Tenant("ash2", "test", new Date(), "female", 21, "ashika@gmail.com", "478233838", house);
//        house.tenats.addNewTenant(tenant);
//        tenant = new Tenant("ash3", "test", new Date(), "female", 21, "ashika@gmail.com", "478233838", house);
//        house.tenats.addNewTenant(tenant);
//        tenant = new Tenant("ash4", "test", new Date(), "female", 21, "ashika@gmail.com", "478233838", house);
//        house.tenats.addNewTenant(tenant);
//         
      //  com.houselist.addNewHouse(house);
        addNewCommunity(com);
        
        
        Community com2 = new Community("Allston-Brighton", 20135, city);
//        House house2 = new House("126", "68 parker", com2);
//        Tenant tenant2 = new Tenant("ash", "test", new Date(), "female", 21, "ashika@gmail.com", "478233838", house2);
//        house.tenats.addNewTenant(tenant2);
//        tenant2 = new Tenant("ash2", "test", new Date(), "female", 21, "ashika@gmail.com", "478233838", house2);
//        house.tenats.addNewTenant(tenant2);
//        tenant2 = new Tenant("ash3", "test", new Date(), "female", 21, "ashika@gmail.com", "478233838", house2);
//        house.tenats.addNewTenant(tenant2);
//        tenant2 = new Tenant("ash4", "test", new Date(), "female", 21, "ashika@gmail.com", "478233838", house2);
//        house2.tenats.addNewTenant(tenant2);
//        com2.houselist.addNewHouse(house2);
        addNewCommunity(com2);
    }   
    
    
    public Community addNewCommunity(Community community){
        communityList.add(community); 
        return community;
    }
    
    public Community addCommunity() {
        Community newCommunity = new Community();
        communityList.add(newCommunity);  
        return newCommunity;
    } 
    
    public void deleteCommunity(int index) {
        communityList.remove(index);
    } 
    
    public void updateCommunity(Community com,int index){
        communityList.set(index,com);
    }
}

