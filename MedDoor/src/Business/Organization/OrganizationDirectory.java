/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business.Organization;

import Business.Organization.Organization.Type;
import java.util.ArrayList;

/**
 *
 * @author ashikakalmady
 */
public class OrganizationDirectory {
    private ArrayList<Organization> organizationList;
    
    public OrganizationDirectory() {
        organizationList = new ArrayList<>();
    }
    
    public ArrayList<Organization> getOrganizationList() {
        return organizationList;
    }
    
    public ArrayList<Organization> getOrganizationList(Type type) {
        return organizationList;
    }
    
     public Organization createOrganization(Type type, String name){
        Organization organization = null;
        if (type.getValue().equals(Type.LabService.getValue())){
            organization = new LabServiceOrganization();
            organization.setName(name);
            organizationList.add(organization);
        }
        else if (type.getValue().equals(Type.Admin.getValue())){
            organization = new AdminOrganization();
            organization.setName(name);
            organizationList.add(organization);
        }
           
        else if (type.getValue().equals(Type.Pharmacy.getValue())){
            organization = new PharmacyOrganization();
            organization.setName(name);
            organizationList.add(organization);
        }
        else if (type.getValue().equals(Type.Doctor.getValue())){
            organization = new DoctorOrganization();
            organization.setName(name);
            organizationList.add(organization);
        }
         else if (type.getValue().equals(Type.HospitalStaff.getValue())){
            organization = new HospitalStaffOrganization();
            organization.setName(name);
            organizationList.add(organization);
        }
        else if (type.getValue().equals(Type.SurveyVolunteer.getValue())){
            organization = new SurveyVolunteerOrganization();
            organization.setName(name);
            organizationList.add(organization);
        }
          else if (type.getValue().equals(Type.HomeCareVolunteer.getValue())){
            organization = new HomeCareVolunteerOrganization();
            organization.setName(name);
            organizationList.add(organization);
        }
           else if (type.getValue().equals(Type.Policy.getValue())){
            organization = new PolicyOrganization();
            organization.setName(name);
            organizationList.add(organization);
        }
         else if (type.getValue().equals(Type.Claims.getValue())){
            organization = new ClaimsOrganization();
            organization.setName(name);
            organizationList.add(organization);
        }
        
        
        return organization;  
     }
    
}
