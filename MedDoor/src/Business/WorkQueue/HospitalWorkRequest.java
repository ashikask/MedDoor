/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business.WorkQueue;

import Business.Community.Tenant;
import Business.Patient.Patient;
import java.util.Date;

/**
 *
 * @author Gursheen Kaur
 */
public class HospitalWorkRequest  extends WorkRequest{
    public Tenant patient;
    public Date assignedDate;
    
    public Tenant getPatient() {
        return patient;
    }

    public void setPatient(Tenant patient) {
        this.patient = patient;
    }
    
  public Date getAssignedDate() {
        return assignedDate;
    }

    public void setAssignedDate(Date assignedDate) {
        this.assignedDate = assignedDate;
    }

   
}
