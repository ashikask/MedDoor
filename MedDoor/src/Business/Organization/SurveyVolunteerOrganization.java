
package Business.Organization;

import Business.Role.Role;
import java.util.ArrayList;

/**
 *
 * @author Dell
 */
public class SurveyVolunteerOrganization extends Organization {
    
        public SurveyVolunteerOrganization() {
        super(Organization.Type.SurveyVolunteer.getValue());
       
    }
    
    @Override
    public ArrayList<Role> getSupportedRole() {
        ArrayList<Role> roles = new ArrayList();
//        roles.add(new DoctorRole());
        return roles;
    }
}
