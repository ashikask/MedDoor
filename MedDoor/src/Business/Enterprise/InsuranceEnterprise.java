/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business.Enterprise;

import Business.Role.Role;
import java.util.ArrayList;

/**
 *
 * @author ashikakalmady
 */
public class InsuranceEnterprise extends Enterprise{
    public InsuranceEnterprise(String name) {
        super(name, EnterpriseType.Insurance);
    }
    @Override
    public ArrayList<Role> getSupportedRole() {
        return null;
    }
}
