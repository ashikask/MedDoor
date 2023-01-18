/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business.Role;

import Business.EcoSystem;
import Business.Enterprise.Enterprise;
import Business.Organization.Organization;
import Business.UserAccount.UserAccount;
import UI.PharmacyAdmin.PharmacyAdmin;
import UI.HospitalAdmin.HospitalAdmin;
import javax.swing.JFrame;

/**
 *
 * @author Dell
 */
public class HospitalAdminRole extends Role {
    @Override
    public void createWorkArea(JFrame parentFrame,UserAccount account, 
            Organization organization, 
            Enterprise enterprise, 
            EcoSystem business) {
        new HospitalAdmin(account,organization, enterprise, business, parentFrame).setVisible(true);
    }
     @Override
    public String toString(){
        return RoleType.HospitalAdminRole.getValue();
    }
}
