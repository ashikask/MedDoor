/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business.Pharmacy;

import java.util.ArrayList;

/**
 *
 * @author Dell
 */
public class PharmacyDirectory {
     private ArrayList<Pharmacy> pharmacyList;
     public PharmacyDirectory() {
        pharmacyList = new ArrayList();
    }

    public ArrayList<Pharmacy> getPharmacyList() {
        return pharmacyList;
    }
    
    public void addPharmacy(Pharmacy pharmacy){
        pharmacyList.add(pharmacy);
    }
    public void removePharmacy(Pharmacy pharmacy){
        pharmacyList.remove(pharmacy);
    }
}