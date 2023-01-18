/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business.Hospital;

import java.util.ArrayList;

/**
 *
 * @author Gursheen Kaur
 */
public class HospitalDirectory {
      private ArrayList<Hospital> hospitalDirectory;
      public HospitalDirectory() {
   this.hospitalDirectory = new ArrayList<Hospital>();
    
    }
    public ArrayList<Hospital> getHospitalDirectory() {
        return hospitalDirectory;
    }
    
    public void setHospitalDirectory(ArrayList<Hospital> HospitalDirectory) {
        this.hospitalDirectory = HospitalDirectory;
    }
      public Hospital addHospital()
    {
    Hospital hospital = new Hospital();
    hospitalDirectory.add(hospital);
    return hospital;
    }
    
    public void deleteHospital(Hospital doctor){
    hospitalDirectory.remove(doctor);
    }
}
