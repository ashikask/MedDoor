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


public class StaffWorkRequest extends WorkRequest {
   public Tenant patient;
   public String doctorAssigned;
 
    
    public Tenant getPatient() {
        return patient;
    }

    public void setPatient(Tenant patient) {
        this.patient = patient;
    }

    public String getDoctorAssigned() {
        return doctorAssigned;
    }

    public void setDoctorAssigned(String doctorAssigned) {
        this.doctorAssigned = doctorAssigned;
    }
    

    
}
