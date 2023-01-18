/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business;

import Business.Enterprise.Enterprise;
import Business.Network.Network;
import Business.Organization.Organization;
import Business.Role.PharmacyAdminRole;
import Business.Role.Role;
import Business.UserAccount.UserAccount;
import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 * @author ashikakalmady
 */
public class EcoSystem extends Organization {
    private static EcoSystem business;
    private ArrayList<Network> networkList;
    
    public static EcoSystem getInstance() {
        if (business == null) {
            business = new EcoSystem();
        }
        return business;
    }
    
    private EcoSystem() {
        super(null);
        networkList = new ArrayList<>();
        Network network = createAndAddNetwork();
        System.out.print(network);
    }
    
     public ArrayList<Network> getNetworkList() {
        return networkList;
    }
     public Network createAndAddNetwork() {
        Network network = new Network();
        network.setName("Boston");
        network.getCommunityDirectory().tempCommList(network);
        networkList.add(network);
        return network;
    }
      @Override
    public ArrayList<Role> getSupportedRole() {
        ArrayList<Role> roleList=new ArrayList<Role>();
        roleList.add(new PharmacyAdminRole());
        return roleList;
    }
        public static boolean isUserUnique(String username) {

      //  if (!this.getUserAccountDirectory().checkIfUsernameIsUnique(username)) {
             for (Network network : business.getNetworkList()) {
                 
                for (Enterprise enterprise : network.getEnterpriseDirectory().getEnterpriseList()) {
                    for (UserAccount ua : enterprise.getUserAccountDirectory().getUserAccountList()) {
                        if(ua.getUsername().equals(username)){
                            return false;
                        }
                    }
                    
                        for (Organization organization : enterprise.getOrganizationDirectory().getOrganizationList()) {
                            for (UserAccount ua : organization.getUserAccountDirectory().getUserAccountList()) {
                                 if(ua.getUsername().equals(username)){
                            return false;
                        }
                    }
                            }
                        }
                    }
            
      //  }

       

        return true;
    }
}
