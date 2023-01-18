/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business;

import Business.Employee.Employee;
import Business.Role.InsuranceAdminRole;
import Business.Role.PharmacyAdminRole;
import Business.Role.SystemAdminRole;
import Business.UserAccount.UserAccount;

/**
 *
 * @author ashikakalmady
 */
public class ConfigureASystem {
    public static EcoSystem configure(){
        EcoSystem system = EcoSystem.getInstance(); 
        Employee admin = system.getEmployeeDirectory().createEmployee("sysadmin");
       // Employee pharmacyadmin = system.getEmployeeDirectory().createEmployee("pharmacyadmin");
        UserAccount ua = system.getUserAccountDirectory().createUserAccount("sysadmin", "sysadmin", admin, new SystemAdminRole());
       // UserAccount ua1 = system.getUserAccountDirectory().createUserAccount("padmin", "padmin", pharmacyadmin, new PharmacyAdminRole());
        Employee insadmin = system.getEmployeeDirectory().createEmployee("iadmin");

        UserAccount ua1 = system.getUserAccountDirectory().createUserAccount("iadmin", "iadmin", insadmin, new InsuranceAdminRole());
        return system;
    }
}
