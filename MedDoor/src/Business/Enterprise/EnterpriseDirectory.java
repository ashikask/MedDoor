/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business.Enterprise;

import Business.Organization.Organization;
import java.util.ArrayList;

/**
 *
 * @author ashikakalmady
 */
public class EnterpriseDirectory {
    
    private ArrayList<Enterprise> enterpriseList;

    public void setEnterpriseList(ArrayList<Enterprise> enterpriseList) {
        this.enterpriseList = enterpriseList;
    }
    
    public EnterpriseDirectory() {
        enterpriseList = new ArrayList<>();
    }

    public ArrayList<Enterprise> getEnterpriseList() {
        return enterpriseList;
    }
    
    public Enterprise createAndAddEnterprise(String name, Enterprise.EnterpriseType type){
       Enterprise enterprise=null;
        if(type==Enterprise.EnterpriseType.Hospital){
            enterprise=new HospitalEnterprise(name);
            enterprise.getOrganizationDirectory().createOrganization(Organization.Type.HospitalStaff, "HospitalStaff Organization");
            enterprise.getOrganizationDirectory().createOrganization(Organization.Type.Doctor, "Doctor Organization");
            
            enterpriseList.add(enterprise);
        }
        if(type==Enterprise.EnterpriseType.Insurance){
            enterprise=new InsuranceEnterprise(name);
            enterprise.getOrganizationDirectory().createOrganization(Organization.Type.Policy, "Policy Organization");
            enterprise.getOrganizationDirectory().createOrganization(Organization.Type.Claims, "Claims Organization");
            
            enterpriseList.add(enterprise);
        }
        if(type==Enterprise.EnterpriseType.Volunteer){
            enterprise=new VolunteerEnterprise(name);
            enterprise.getOrganizationDirectory().createOrganization(Organization.Type.HomeCareVolunteer, "HomeCareVolunteer Organization");
            enterprise.getOrganizationDirectory().createOrganization(Organization.Type.SurveyVolunteer, "SurveyVolunteer Organization");
            
            enterpriseList.add(enterprise);
        }if(type==Enterprise.EnterpriseType.Diagnostics){
            enterprise=new DiagnosticsEnterprise(name);
            enterprise.getOrganizationDirectory().createOrganization(Organization.Type.Pharmacy, "Pharmacy Org");
            enterprise.getOrganizationDirectory().createOrganization(Organization.Type.LabService, "LabService Organization");
            
            enterpriseList.add(enterprise);
        }
     
        return enterprise;
    }
}