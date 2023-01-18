/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business.Community;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ashikakalmady
 */

public class TenantDirectory {
    ArrayList<Tenant> tenants;

    public TenantDirectory() {
        this.tenants = new ArrayList<>();
    }
    
    public void tempTentList() { 
//         SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy");
//          try{
//                addNewTenant(new Tenant("Ramesh", "hadson", DateFor.parse("08/07/2019"), "Male", new House()));
//                addNewTenant(new Tenant("Suresh", "hadson3", DateFor.parse("08/07/2019"), "Male", new House()));        
//          
//          } catch (ParseException e) {e.printStackTrace();}
    }
      public Tenant createAndAddTenant(String fName, String lName, Date dob, String gender,Integer age,String email, String phone,House house)
        {
         Tenant tenant = new Tenant(fName, lName, dob, gender, age, email,phone, house);
         this.addNewTenant(tenant);
         return tenant;
    }
    
    public List<Tenant> getTenants() {
        return tenants;
    }

    public void setTenant(ArrayList<Tenant> tenants) {
        this.tenants = tenants;
    }
    
     public Tenant addNewTenant(Tenant patient){
        tenants.add(patient); 
        return patient;
    }
    
 
    
    public void deleteTenant(int index) {
        tenants.remove(index);
    } 
    
    public void updateTenant(Tenant tenant,int index){
        tenants.set(index,tenant);
    }
}
