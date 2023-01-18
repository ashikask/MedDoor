/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business.Organization;

import Business.Pharmacy.PharmacyDirectory;
import Business.Role.Role;
import java.util.ArrayList;

/**
 *
 * @author Dell
 */
public class PharmacyOrganization extends Organization {
    public PharmacyDirectory pharDir;

    public PharmacyDirectory getPd() {
        return pharDir;
    }

    public void setPd(PharmacyDirectory pd) {
        this.pharDir = pd;
    }

    public PharmacyOrganization() {
        
        super(Organization.Type.Pharmacy.getValue());
        pharDir = new PharmacyDirectory();
       
    }
    
    @Override
    public ArrayList<Role> getSupportedRole() {
        ArrayList<Role> roles = new ArrayList();
//        roles.add(new DoctorRole());
        return roles;
    }
}
