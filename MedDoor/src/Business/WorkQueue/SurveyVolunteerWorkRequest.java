/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business.WorkQueue;

import Business.Community.House;
import Business.Community.HouseList;
import Business.Community.Tenant;
import Business.Voluntary.VitalSigns;
import java.util.Date;

/**
 *
 * @author ashikakalmady
 */
public class SurveyVolunteerWorkRequest extends WorkRequest {
    public House assignedHouse;
    public Date assignedDate;
    public VitalSigns vitalSign;
    public Tenant tenant;

    public Tenant getTenant() {
        return tenant;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }

    public House getAssignedHouse() {
        return assignedHouse;
    }

    public void setAssignedHouse(House assignedHouse) {
        this.assignedHouse = assignedHouse;
    }
    
    public VitalSigns getVitalSign() {
        return vitalSign;
    }

    public void setVitalSign(VitalSigns vitalSign) {
        this.vitalSign = vitalSign;
    }
    
    public Date getAssignedDate() {
        return assignedDate;
    }

    public void setAssignedDate(Date assignedDate) {
        this.assignedDate = assignedDate;
    }
    
    
}
