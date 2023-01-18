/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business.WorkQueue;

import Business.UserAccount.UserAccount;
import java.math.BigInteger;
import java.util.UUID;

/**
 *
 * @author Dell
 */
//String uniqueID = UUID.randomUUID().toString();
//        this.communityId = uniqueID;
public class ClaimsWorkRequest extends WorkRequest {
     private String claimsInfo;
    private BigInteger insuranceProfile;
    private String policyID;
    private float amtapproved;
    private int billamount;
//    private String emailid ;
    private UserAccount claimbeneficiary;
    private String claimstatus;

    public int getBillamount() {
        return billamount;
    }

    public void setBillamount(int billamount) {
        this.billamount = billamount;
    }

    

    public String getClaimsInfo() {
        return claimsInfo;
    }

    public void setClaimsInfo(String claimsInfo) {
        this.claimsInfo = claimsInfo;
    }

    public BigInteger getInsuranceProfile() {
        return insuranceProfile;
    }

    public void setInsuranceProfile(BigInteger insuranceProfile) {
        this.insuranceProfile = insuranceProfile;
    }

    public String getPolicyID() {
        return policyID;
    }

    public void setPolicyID(String policyID) {
        this.policyID = policyID;
    }

    public UserAccount getClaimbeneficiary() {
        return claimbeneficiary;
    }

    public void setClaimbeneficiary(UserAccount claimbeneficiary) {
        this.claimbeneficiary = claimbeneficiary;
    }

    public String getClaimstatus() {
        return claimstatus;
    }

    public void setClaimstatus(String claimstatus) {
        this.claimstatus = claimstatus;
    }
   
}
