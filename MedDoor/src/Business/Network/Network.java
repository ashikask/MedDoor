package Business.Network;

import Business.Community.CommunityList;
import Business.Enterprise.EnterpriseDirectory;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ashikakalmady
 */
public class Network {
    private String name;
    private EnterpriseDirectory enterpriseDirectory;
    private CommunityList communityDirectory;
    
    public Network() {
        enterpriseDirectory = new EnterpriseDirectory();
        this.communityDirectory = new CommunityList();
    }
    
    public CommunityList getCommunityDirectory() {
        return communityDirectory;
    }
    
    public EnterpriseDirectory getEnterpriseDirectory() {
        return enterpriseDirectory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
