
package Business.Pharmacy;

import Business.Community.Community;
import Business.Role.SystemAdminRole;
import Business.UserAccount.UserAccount;

/**
 *
 * @author Dell
 */
public class Pharmacy {
   
    private String pharmacyName;
    private Community community;
    private String address;
    private String contact;
    public MedicineInventory medicineList;

    public MedicineInventory getMedicineList() {
        return medicineList;
    }

    public void setMedicineList(MedicineInventory medicineList) {
        this.medicineList = medicineList;
    }
    
    public Community getCommunity() {
        return community;
    }

    public void setCommunity(Community community) {
        this.community = community;
    }

    public Pharmacy(String phName,Community community, String phAdd, String phCont) {
      
        this.pharmacyName=phName;
        this.address=phAdd;
        this.contact=phCont;
        this.community = community;
        this.medicineList = new MedicineInventory();
    }

    public Pharmacy() {
    
        this.medicineList = new MedicineInventory();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
 

 
    public String getPharmacyName() {
        return pharmacyName;
    }

    public void setPharmacyName(String pharmacyName) {
        this.pharmacyName = pharmacyName;
    }

    @Override
    public String toString() {
        return pharmacyName;
    }
}

