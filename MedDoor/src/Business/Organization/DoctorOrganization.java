
package Business.Organization;

import Business.Role.Role;
import java.util.ArrayList;

public class DoctorOrganization extends Organization{

    public DoctorOrganization() {
        super(Organization.Type.Doctor.getValue());
       
    }
    
    @Override
    public ArrayList<Role> getSupportedRole() {
        ArrayList<Role> roles = new ArrayList();
//        roles.add(new DoctorRole());
        return roles;
    }
  
}