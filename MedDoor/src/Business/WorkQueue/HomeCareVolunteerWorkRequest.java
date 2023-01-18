/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business.WorkQueue;

import Business.Community.House;
import Business.Community.Tenant;

/**
 *
 * @author ashikakalmady
 */
public class HomeCareVolunteerWorkRequest extends WorkRequest {
    public House assignedHouse;
    public Tenant patient;

    public House getAssignedHouse() {
        return assignedHouse;
    }

    public void setAssignedHouse(House assignedHouse) {
        this.assignedHouse = assignedHouse;
    }

    public Tenant getPatient() {
        return patient;
    }

    public void setPatient(Tenant patient) {
        this.patient = patient;
    }
    
}
